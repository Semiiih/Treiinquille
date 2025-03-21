package fr.esiee;

import fr.esiee.dao.EasyTrainDAO;
import fr.esiee.modele.Arret;
import fr.esiee.modele.Role;
import fr.esiee.modele.Trajet;
import fr.esiee.modele.Utilisateur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestDAO {

    public static void main(String[] args) {
        EasyTrainDAO dao = new EasyTrainDAO();

        //Etape 2 /////////////////////////////////////////////////////////////////////////////////////

        /////////// Test 1 : Ajouter un utilisateur ///////////////
        Utilisateur nouvelUtilisateur = new Utilisateur(
                0,
                "TestDAO",
                "password123",
                "Doe",
                "John",
                LocalDate.of(2022, 5, 10),
                Role.EMPLOYE
        );
        boolean utilisateurAjoute = dao.ajouterUtilisateur(nouvelUtilisateur);
        System.out.println("=======================");
        System.out.println("Test Ajouter Utilisateur : " + (utilisateurAjoute ? "Succès" : "Échec"));
        if (utilisateurAjoute) {
            System.out.println("ID de l'utilisateur ajouté : " + nouvelUtilisateur.getId());
        }

        //////////// Test 2 : Récupérer un utilisateur par ID //////////////
        int idRechercheUser = 1;
        Utilisateur utilisateurRecupere = dao.getUtilisateurById(idRechercheUser);

        if (utilisateurRecupere != null) {
            System.out.println("Test Récupérer Utilisateur par ID (" + idRechercheUser + ") : Succès");
            System.out.println("=======================");
            System.out.println(utilisateurRecupere);
        } else {
            System.out.println("Test Récupérer Utilisateur par ID (" + idRechercheUser + ") : Échec");
        }

        /////////// Test 3 : Récupérer la liste de tous les utilisateurs ///////////
        List<Utilisateur> utilisateurs = dao.getAllUtilisateurs();
        if (utilisateurs != null && !utilisateurs.isEmpty()) {
            System.out.println("=======================");
            System.out.println("Test Récupérer Tous les Utilisateurs : Succès");
            System.out.println("Liste des utilisateurs :");
            for (Utilisateur u : utilisateurs) {
                System.out.println(u);   
            }
            System.out.println("=======================");
        } else {
            System.out.println("Test Récupérer Tous les Utilisateurs : Échec");
        }

        // Etape 3 /////////////////////////////////////////////////////////////////////////////////////

        /////////// Test 1 : Ajouter un arrêt ///////////
        Arret nouvelArret = new Arret(0, "Gare d'Avignon", Arret.TypeArret.INTERMEDIAIRE);
        boolean arretAjoute = dao.ajouterArret(nouvelArret);
        System.out.println("Test Ajouter Arret : " + (arretAjoute ? "Succès" : "Échec"));
        if (arretAjoute) {
            System.out.println("ID de l'arrêt ajouté : " + nouvelArret.getId());
            System.out.println("-----------------------");

        }

        /////////// Test 2 : Récupérer un arrêt par ID ///////////
        int idRechercheArret = 1; 
        Arret arretRecupere = dao.getArretById(idRechercheArret);
        if (arretRecupere != null) {
            System.out.println("Test Récupérer Arret par ID (" + idRechercheArret + ") : Succès");
            System.out.println("Arret récupéré: " + arretRecupere.getNom());
            System.out.println("-----------------------");
        } else {
            System.out.println("Test Récupérer Arret par ID (" + idRechercheArret + ") : Échec");
        }

        /////////// Test 3 : Récupérer tous les arrêts ///////////
        List<Arret> arrets = dao.getAllArrets();
        if (arrets != null && !arrets.isEmpty()) {
            System.out.println("Test Récupérer Tous les Arrets : Succès");
            for (Arret a : arrets) {
                System.out.println(a.getId() + " - " + a.getNom());
            }
            System.out.println("-----------------------");
        } else {
            System.out.println("Test Récupérer Tous les Arrets : Échec");
        }


        // Etape 4 /////////////////////////////////////////////////////////////////////////////////////

        /////////// Test 1 : Ajouter un trajet ///////////
        int idArretDepart = 1; 
        int idArretArrivee = 2; 

        Arret depart = dao.getArretById(idArretDepart);
        Arret arrivee = dao.getArretById(idArretArrivee);

        if (depart != null && arrivee != null) {
            Trajet nouveauTrajet = new Trajet(
                    "110", 
                    LocalDateTime.of(2024, 11, 24, 10, 30), 
                    LocalDateTime.of(2024, 11, 24, 12, 45), 
                    depart, 
                    arrivee 
            );

            boolean trajetAjoute = dao.ajouterTrajet(nouveauTrajet);
            System.out.println("Test Ajouter Trajet : " + (trajetAjoute ? "Succès" : "Échec"));
        } else {
            System.out.println("Impossible de tester l'ajout d'un trajet : arrêt de départ ou d'arrivée introuvable.");
        }

        /////////// Test 2 : Récupérer un trajet par son ID ///////////
        String codeRechercheTrajet = "110";
        Trajet trajetRecupere = null;
        try {
            trajetRecupere = dao.getTrajetById(codeRechercheTrajet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (trajetRecupere != null) {
            System.out.println("************************");
            System.out.println("Test Récupérer Trajet par Code (" + codeRechercheTrajet + ") : Succès");
            System.out.println(trajetRecupere);
            System.out.println("************************");
        } else {
            System.out.println("Test Récupérer Trajet par Code (" + codeRechercheTrajet + ") : Échec");
        }

        /////////// Test 3 : Récupérer tous les trajets ///////////
        List<Trajet> trajets = dao.getAllTrajets();
        if (trajets != null && !trajets.isEmpty()) {
            System.out.println("Test Récupérer Tous les Trajets : Succès");
            System.out.println("Liste des trajets :");
            for (Trajet t : trajets) {
                System.out.println(t);
            }
            System.out.println("************************");
        } else {
            System.out.println("Test Récupérer Tous les Trajets : Échec");
        }
       
    }
}
