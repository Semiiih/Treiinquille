package fr.esiee.easytrainfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        String identifiant = identifiantField.getText();
        String password = passwordField.getText();

        if (identifiant.isEmpty() || password.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs !");
            return;
        }

        if ("admin".equals(identifiant) && "1234".equals(password)) {
            showAlert("Succès", "Connexion réussie !");

            // Changer d'écran après connexion
            try {
                MainApplication.showPlanningScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Erreur", "Identifiant ou mot de passe incorrect !");
        }
    }


    @FXML
    private void onSignupLinkClick(ActionEvent event) {
        try {
            MainApplication.showSignupScreen(); // Redirection vers l'inscription
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
