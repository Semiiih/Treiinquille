package fr.esiee.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.esiee.modele.Utilisateur;
import fr.esiee.modele.Role;

public class EasyTrainDAO {

    private static final String DB_URL = "jdbc:mariadb://mysql-emirsen.alwaysdata.net:3306/emirsen_treiinquille";
    private static final String DB_USER = "emirsen";
    private static final String DB_PASSWORD = "Jesuistoutennoir95";


    private Connection getConnexion() throws SQLException {
        try {
            // Charger explicitement le pilote MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Pilote MariaDB non trouvé !", e);
        }

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /////////// pour récupérer la liste de tous les utilisateurs ////////////////
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT id, nom, prenom, email, password, role FROM `Utilisateur`";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                );
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    /////////// pour récupérer un utilisateur par son identifiant unique /////////
    public Utilisateur getUtilisateurById(int id) {
        Utilisateur utilisateur = null;
        String query = "SELECT id, nom, prenom, email, password, role FROM `Utilisateur` WHERE id = ?";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }


}
