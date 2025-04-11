package fr.esiee;

import fr.esiee.modele.Arret;
import fr.esiee.modele.Role;
import fr.esiee.modele.Trajet;
import fr.esiee.modele.Utilisateur;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        /////////////etape1/////////////////////////////////////////////////

        ////test avec le construteur par defaut ////////
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setId(1);
        utilisateur1.setPassword("mdp123");
        utilisateur1.setNom("CKC");
        utilisateur1.setPrenom("Semih");
        utilisateur1.setRole(Role.CONDUCTEUR);

        ////test avec le constructeur à 7 arguments//////
        Utilisateur utilisateur2 = new Utilisateur(2, "Admin", "Admin", "Admin","email", Role.MANAGER);

        /////////// Mise à jour ////////
        utilisateur1.setNom("Toto");
        utilisateur2.setPrenom("Titi");

        utilisateur1.afficherUtilisateur();
        utilisateur2.afficherUtilisateur();


        //////////etape2///////////////////////////////////////////
        Arret arret1 = new Arret(1, "Paris");
        Arret arret2 = new Arret(2, "Avignon");
        Arret arret3 = new Arret(3, "Marseille");
        Arret arret4 = new Arret(4, "Nice");

        Trajet trajet1 = new Trajet("001", LocalDateTime.of(2024, 9, 27, 9, 0),
                LocalDateTime.of(2024, 9, 28, 12, 0), arret1, arret2);

        Trajet trajet2 = new Trajet("002", LocalDateTime.of(2024, 9, 28, 10, 0),
                LocalDateTime.of(2024, 9, 28, 17, 0), arret3, arret4);

        ///// Mise à jour //////////
        trajet1.setCode("033");
        arret1.setLocalisation("Paris Gare de Lyon");


        trajet1.afficherTrajet();
        trajet2.afficherTrajet();
    }
}
