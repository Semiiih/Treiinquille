package fr.esiee.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.esiee.modele.Utilisateur;
import fr.esiee.modele.Arret;
import fr.esiee.modele.Role;
import fr.esiee.modele.Trajet;

public class EasyTrainDAO {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/easyTrain";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private Connection getConnexion() throws SQLException {
        try {
            // Charger explicitement le pilote MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Pilote MariaDB non trouvé !", e);
        }

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }


    //Etape 2 ////////////////////////////////////////////////////////////////////////////////////////

    /////////// pour récupérer un utilisateur par son identifiant unique /////////
    public Utilisateur getUtilisateurById(int id) {
        Utilisateur utilisateur = null;
        String query = "SELECT id, login, mdp, nom, prenom, dateEmbauche, role FROM `Utilisateur` WHERE id = ?";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("mdp"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateEmbauche").toLocalDate(),
                        Role.valueOf(rs.getString("role"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }


    /////////// pour récupérer la liste de tous les utilisateurs ////////////////
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT id, login, mdp, nom, prenom, dateEmbauche, role FROM `Utilisateur`";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("mdp"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateEmbauche").toLocalDate(),
                        Role.valueOf(rs.getString("role"))
                );
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }


    /////////// pour ajouter un utilisateur et en profiter pour récupérer son id généré //////////
    public boolean ajouterUtilisateur(Utilisateur u) {
        String query = "INSERT INTO Utilisateur (login, mdp, nom, prenom, dateEmbauche, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getMdp());
            stmt.setString(3, u.getNom());
            stmt.setString(4, u.getPrenom());
            stmt.setDate(5, Date.valueOf(u.getDateEmbauche()));
            stmt.setString(6, u.getRole().name());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    u.setId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Etape 3 ////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////// pour ajouter un arrêt et en profiter pour récupérer son id généré ///////////
    public boolean ajouterArret(Arret a) {
        String query = "INSERT INTO Arret (nom, typeArret) VALUES (?, ?)";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getNom());
            stmt.setString(2, a.getTypeArret().name());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    a.setId(generatedKeys.getInt(1));  // Récupération de l'ID généré
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean verifierArretExistant(String nomArret) {
        String query = "SELECT COUNT(*) FROM Arret WHERE nom = ?";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nomArret);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /////////// pour récupérer un arrêt par son identifiant unique //////////////
    public Arret getArretById(int id) {
        Arret arret = null;
        String query = "SELECT id, nom, typeArret FROM Arret WHERE id = ?";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                arret = new Arret(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        Arret.TypeArret.valueOf(rs.getString("typeArret"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arret;
    }

    /////////// pour récupérer la liste de tous les arrêts ///////////
    public List<Arret> getAllArrets() {
        List<Arret> arrets = new ArrayList<>();
        String query = "SELECT id, nom FROM Arret";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Arret arret = new Arret(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        Arret.TypeArret.valueOf(rs.getString("typeArret"))
                );
                arrets.add(arret);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrets;
    }


    // Etape 4 ////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////// pour récupérer un trajet par son identifiant unique (attention de ne pas oublier les arrêtes du trajets) ///////////
    public Trajet getTrajetById(String code) throws SQLException {
        String sql = "SELECT t.code, t.tempsDepart, t.tempsArrivee, t.arretDepart_id, t.arretArrivee_id "
                + "FROM Trajet t WHERE t.code = ?";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Arret arretDepart = getArretById(rs.getInt("arretDepart_id"));
                Arret arretArrivee = getArretById(rs.getInt("arretArrivee_id"));
                return new Trajet(
                        rs.getString("code"),
                        rs.getTimestamp("tempsDepart").toLocalDateTime(),
                        rs.getTimestamp("tempsArrivee").toLocalDateTime(),
                        arretDepart,
                        arretArrivee
                );
            }
        }
        return null;
    }


    public List<Trajet> getAllTrajets() {
        List<Trajet> trajets = new ArrayList<>();
        String query = "SELECT t.code, t.tempsDepart, t.tempsArrivee, " +
                "ad.id AS idDepart, ad.nom AS nomDepart, " +
                "aa.id AS idArrivee, aa.nom AS nomArrivee " +
                "FROM Trajet t " +
                "JOIN Arret ad ON t.arretDepart_id = ad.id " +
                "JOIN Arret aa ON t.arretArrivee_id = aa.id";

        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Arret arretDepart = new Arret(
                        rs.getInt("idDepart"),
                        rs.getString("nomDepart"),
                        Arret.TypeArret.valueOf(rs.getString("typeArretDepart"))
                );
                Arret arretArrivee = new Arret(
                        rs.getInt("idArrivee"),
                        rs.getString("nomArrivee"),
                        Arret.TypeArret.valueOf(rs.getString("typeArretArrivee"))
                );

                Trajet trajet = new Trajet(
                        rs.getString("code"),
                        rs.getTimestamp("tempsDepart").toLocalDateTime(),
                        rs.getTimestamp("tempsArrivee").toLocalDateTime(),
                        arretDepart,
                        arretArrivee
                );

                trajets.add(trajet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajets;
    }


    public boolean ajouterTrajet(Trajet t) {
        String query = "INSERT INTO Trajet (code, tempsDepart, tempsArrivee, arretDepart_id, arretArrivee_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, t.getCode());
            stmt.setTimestamp(2, Timestamp.valueOf(t.getTempsDepart()));
            stmt.setTimestamp(3, Timestamp.valueOf(t.getTempsArrivee()));
            stmt.setInt(4, t.getArretDepart().getId());
            stmt.setInt(5, t.getArretArrivee().getId());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
