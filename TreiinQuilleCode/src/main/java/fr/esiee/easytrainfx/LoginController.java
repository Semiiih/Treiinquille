package fr.esiee.easytrainfx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void onLoginButtonClick() {
        String username = identifiantField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Erreur de connexion", "Veuillez remplir tous les champs.");
            return;
        }

        // Ici, vous devriez vérifier les identifiants auprès de votre système d'authentification
        // Pour l'exemple, nous utilisons une vérification simple
        if (username.equals("admin") && password.equals("admin")) {
            try {
                // Naviguer directement vers le dashboard
                MainApplication.showDashboardScreen();
            } catch (IOException e) {
                showAlert("Erreur", "Impossible de charger le dashboard: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            showAlert("Erreur de connexion", "Identifiant ou mot de passe incorrect.");
        }
    }

    @FXML
    private void onSignupLinkClick() {
        try {
            MainApplication.showSignupScreen();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page d'inscription: " + e.getMessage());
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