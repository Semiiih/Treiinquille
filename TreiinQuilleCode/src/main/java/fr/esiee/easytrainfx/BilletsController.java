package fr.esiee.easytrainfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BilletsController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label usernameLabel;

    @FXML
    private ComboBox<String> trajetComboBox;

    @FXML
    public void initialize() {
        // Initialiser le nom d'utilisateur (puisque UserSession n'existe pas, utilisons du texte statique)
        usernameLabel.setText("Utilisateur");

        // Remplir le ComboBox avec des trajets d'exemple
        ObservableList<String> trajets = FXCollections.observableArrayList(
                "T001 - Paris → Lyon (25/04/2025)",
                "T002 - Marseille → Paris (26/04/2025)",
                "T003 - Lyon → Bordeaux (27/04/2025)",
                "T004 - Lille → Strasbourg (28/04/2025)"
        );
        trajetComboBox.setItems(trajets);

        // Sélectionner le premier trajet par défaut
        if (!trajets.isEmpty()) {
            trajetComboBox.setValue(trajets.get(0));
        }
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton de déconnexion
     */
    @FXML
    private void onLogoutButtonClick() {
        try {
            // Rediriger vers la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur lors de la déconnexion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Naviguer vers la page Trajet
     */
    @FXML
    private void onTrajetButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trajets.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);
        } catch (IOException e) {
            showAlert("Erreur lors du chargement de la page Trajet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Naviguer vers la page Planning
     */
    @FXML
    private void onPlanningButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("planning.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);
        } catch (IOException e) {
            showAlert("Erreur lors du chargement de la page Planning: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Naviguer vers la page Planning
     */
    @FXML
    private void onBilletButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("billets.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);
        } catch (IOException e) {
            showAlert("Erreur lors du chargement de la page Planning: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Affiche une boîte de dialogue d'alerte
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}