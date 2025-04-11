package fr.esiee.easytrainfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label ticketCountLabel;

    @FXML
    private Label tripCountLabel;

    @FXML
    private Label nextTripLabel;

    @FXML
    private VBox trajetNavItem;

    @FXML
    private void initialize() {
        // Simuler des valeurs dynamiques
        String currentUser = "JeanDupont"; // tu peux l'adapter avec un système d'utilisateur
        int billetsVendus = 42;
        int trajets = 12;
        String prochainDepart = LocalDateTime.now().plusHours(2)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        usernameLabel.setText(currentUser);
        ticketCountLabel.setText(String.valueOf(billetsVendus));
        tripCountLabel.setText(String.valueOf(trajets));
        nextTripLabel.setText("Prochain départ : " + prochainDepart);

        // Add click handler for trip items in the dashboard
        if (trajetNavItem != null) {
            trajetNavItem.setOnMouseClicked(this::onTrajetItemClick);
        }
    }

    @FXML
    private void onTrajetItemClick(MouseEvent event) {
        try {
            MainApplication.showTrajetScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event) {
        try {
            MainApplication.showLoginScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddTripButtonClick(ActionEvent event) {
        try {
            MainApplication.showAddTripDialog();
            // Après la fermeture du dialogue, vous pourriez vouloir rafraîchir la liste des trajets
            refreshTripList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Exemple de méthode pour rafraîchir la liste des trajets
    private void refreshTripList() {
        // Code pour mettre à jour l'affichage de la liste des trajets
    }
}
