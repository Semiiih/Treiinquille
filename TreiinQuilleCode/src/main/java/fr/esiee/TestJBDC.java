package fr.esiee;

import java.sql.*;

public class TestJBDC {
    public static void main(String[] args) {

        /////////// BDD avec Docker ////////////
        String urlLocal = "jdbc:mariadb://localhost:3306/easyTrain";
        String userLocal = "root";
        String pwdLocal = "root";

        //////////// BDD avec MAMP ////////////
        String urlLocal2 = "jdbc:mariadb://localhost:8888/easyTrain";
        String userLocal2 = "root";
        String pwdLocal2 = "root";

        //////////// BDD avec Alwaysdata
        String urlDistant = "jdbc:mariadb://mysql-cakicisemih.alwaysdata.net:3306/cakicisemih_easytrain";
        String userDistant = "356140";
        String pwdDistant = "PasswordBDD+";

        try {
            Connection connection = DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
            System.out.println("Connexion (avec Docker) OK");

            Statement statement = connection.createStatement();

            // Étape 2 - Requête INSERT
            String insertQuery = "INSERT INTO Utilisateur (login, mdp, nom, prenom, dateEmbauche, role) " +
                    "VALUES ('johndoe', 'motdepasse_hashé', 'Doe', 'John', '2024-10-24', 'EMPLOYE')";
            int rowsAffected = statement.executeUpdate(insertQuery);
            if (rowsAffected > 0) {
                System.out.println("Utilisateur inséré avec succès ! Nombre de lignes affectées : " + rowsAffected);
            } else {
                System.out.println("Aucune ligne affectée par l'insertion.");
            }

            // Étape 3 - Requête SELECT by ID
            int utilisateurId = 2;
            String selectQuery = "SELECT * FROM Utilisateur WHERE id = " + utilisateurId;
            ResultSet resultSet = statement.executeQuery(selectQuery);


            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String dateEmbauche = resultSet.getString("dateEmbauche");
                String role = resultSet.getString("role");

                System.out.println("----------------------------------------");
                System.out.println("Etape 3 : ");
                System.out.println("Utilisateur trouvé :");
                System.out.println("Login : " + login);
                System.out.println("Nom : " + nom);
                System.out.println("Prénom : " + prenom);
                System.out.println("Date d'embauche : " + dateEmbauche);
                System.out.println("Rôle : " + role);
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }


            // Étape 4 - requête de récupération de plusieurs éléments(SELECT)
            String selectAllQuery = "SELECT * FROM Utilisateur";
            ResultSet resultSetAll = statement.executeQuery(selectAllQuery);

            System.out.println("Liste des utilisateurs :");
            while (resultSetAll.next()) {
                String login = resultSetAll.getString("login");
                String nom = resultSetAll.getString("nom");
                String prenom = resultSetAll.getString("prenom");
                String dateEmbauche = resultSetAll.getString("dateEmbauche");
                String role = resultSetAll.getString("role");

                System.out.println("=============================================");
                System.out.println("Etape 4 : ");
                System.out.println("Login : " + login);
                System.out.println("Nom : " + nom);
                System.out.println("Prénom : " + prenom);
                System.out.println("Date d'embauche : " + dateEmbauche);
                System.out.println("Rôle : " + role);
            }

            resultSetAll.close();
            statement.close();
            connection.close();
            System.out.println("Connexion fermée.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion ou de l'exécution de la requête SQL.");
            e.printStackTrace();
        }

        System.out.println("Fin du programme.");
    }
}