package fr.esiee.easytrainfx;

import fr.esiee.dao.EasyTrainDAO;
import fr.esiee.modele.Utilisateur;
import fr.esiee.modele.Role;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AjoutUtilisateurController {

    @FXML private TextField loginField;
    @FXML private TextField mdpField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private DatePicker dateEmbauchePicker;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private Label messageLabel;

    private final EasyTrainDAO dao = new EasyTrainDAO();

    @FXML
    public void initialize() {
        roleComboBox.getItems().addAll("ADMIN", "EMPLOYE");
    }

    @FXML
    private void onResetButtonClick() {
        loginField.clear();
        mdpField.clear();
        nomField.clear();
        prenomField.clear();
        dateEmbauchePicker.setValue(null);
        roleComboBox.setValue(null);
        messageLabel.setText("Les champs ont été réinitialisés.");
        messageLabel.setStyle("-fx-text-fill: blue;");
    }

    @FXML
    private void onAddButtonClick() {
        // Récupérer les valeurs des champs
        String login = loginField.getText();
        String mdp = mdpField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        LocalDate dateEmbauche = dateEmbauchePicker.getValue();
        String roleString = roleComboBox.getValue();

        if (login == null || login.isEmpty() || mdp == null || mdp.isEmpty() ||
                nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() ||
                dateEmbauche == null || roleString == null) {

            messageLabel.setText("Erreur : Tous les champs doivent être remplis !");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            Role role;
            try {
                role = Role.valueOf(roleString);
            } catch (IllegalArgumentException e) {
                messageLabel.setText("Erreur : Rôle invalide.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            Utilisateur utilisateur = new Utilisateur(0, login, mdp, nom, prenom, dateEmbauche, role);

            // Appel de la méthode DAO pour insérer l'utilisateur
            boolean success = dao.ajouterUtilisateur(utilisateur);

            if (success) {
                messageLabel.setText("Utilisateur ajouté avec succès : " + utilisateur);
                messageLabel.setStyle("-fx-text-fill: green;");
            } else {
                messageLabel.setText("Erreur : Impossible d'ajouter l'utilisateur.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (Exception e) {
            messageLabel.setText("Erreur : " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}
