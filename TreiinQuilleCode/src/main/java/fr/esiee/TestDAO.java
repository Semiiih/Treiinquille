package fr.esiee;
import fr.esiee.dao.EasyTrainDAO;
import fr.esiee.modele.Utilisateur;
import java.util.List;

public class TestDAO {

    public static void main(String[] args) {
        EasyTrainDAO dao = new EasyTrainDAO();

        /////////// Test 1 : Récupérer la liste de tous les utilisateurs ///////////
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


    }
}
