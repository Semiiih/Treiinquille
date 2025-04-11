package fr.esiee.easytrainfx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class SignupController {

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signupButton;

    @FXML
    private void onSignupButtonClick() {
        String username = identifiantField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Vérification des champs
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Erreur d'inscription", "Veuillez remplir tous les champs.");
            return;
        }

        // Vérification de la correspondance des mots de passe
        if (!password.equals(confirmPassword)) {
            showAlert("Erreur d'inscription", "Les mots de passe ne correspondent pas.");
            return;
        }

        // Vérification de la longueur du mot de passe
        if (password.length() < 6) {
            showAlert("Erreur d'inscription", "Le mot de passe doit contenir au moins 6 caractères.");
            return;
        }

        // Ici, vous devriez enregistrer l'utilisateur dans votre système
        // Pour l'exemple, nous allons simplement afficher un message de succès
        showAlert("Inscription réussie", "Votre compte a été créé avec succès !");

        // Rediriger vers la page de connexion
        try {
            MainApplication.showLoginScreen();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page de connexion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onLoginLinkClick() {
        try {
            MainApplication.showLoginScreen();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page de connexion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}