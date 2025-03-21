package fr.esiee.easytrainfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignupController {

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    // Simuler une base de données avec une HashMap
    private static Map<String, String> usersDatabase = new HashMap<>();

    @FXML
    private void onSignupButtonClick(ActionEvent event) {
        String identifiant = identifiantField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (identifiant.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs !");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Erreur", "Les mots de passe ne correspondent pas !");
            return;
        }

        if (usersDatabase.containsKey(identifiant)) {
            showAlert("Erreur", "Cet identifiant est déjà utilisé !");
            return;
        }

        // Enregistrer l'utilisateur
        usersDatabase.put(identifiant, password);
        showAlert("Succès", "Inscription réussie !");

        // Rediriger vers l'écran de connexion
        try {
            MainApplication.showLoginScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLoginLinkClick(ActionEvent event) {
        try {
            MainApplication.showLoginScreen();
        } catch (IOException e) {
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
