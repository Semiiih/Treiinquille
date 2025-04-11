package fr.esiee.easytrainfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TrajetsController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField dateFilter;

    @FXML
    private ComboBox<String> conducteurFilter;

    @FXML
    private VBox prochainsTrajetsList;

    @FXML
    private VBox trajetsRealisesList;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        // Set username from session
        if (UserSession.getCurrentUser() != null) {
            usernameLabel.setText(UserSession.getCurrentUser().getUsername());
        } else {
            usernameLabel.setText("$NAME");
        }

        // Initialize conducteur filter
        conducteurFilter.getItems().addAll("Tous", "Conducteur 1", "Conducteur 2", "Conducteur 3");
        conducteurFilter.setValue("Tous");

        // Load sample data
        loadSampleData();
    }

    private void loadSampleData() {
        // Clear existing items
        prochainsTrajetsList.getChildren().clear();
        trajetsRealisesList.getChildren().clear();

        // Sample data for upcoming trips
        for (int i = 0; i < 5; i++) {
            HBox trajetRow = createTrajetRow("12/07/2024", "Cergy", "12/07/2024", "Cergy",
                    "$CO_NAME", "$PLA_DIS", false);
            prochainsTrajetsList.getChildren().add(trajetRow);
        }

        // Sample data for completed trips
        for (int i = 0; i < 4; i++) {
            HBox trajetRow = createTrajetRow("12/07/2024", "Cergy", "12/07/2024", "Cergy",
                    "$CO_NAME", "$NBVOYA", true);
            trajetsRealisesList.getChildren().add(trajetRow);
        }
    }

    private HBox createTrajetRow(String dateDepart, String lieuDepart, String dateArrivee,
                                 String lieuArrivee, String conducteur, String info, boolean completed) {
        HBox row = new HBox(10);
        row.setPrefHeight(40);
        row.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");

        // Status indicator (red for upcoming, green for completed)
        Label statusIndicator = new Label(completed ? "✓" : "⊚");
        statusIndicator.setStyle(completed ?
                "-fx-text-fill: #00FF00; -fx-font-weight: bold;" :
                "-fx-text-fill: #FF0000; -fx-font-weight: bold;");
        statusIndicator.setPrefWidth(20);

        // Trip details
        Label details = new Label(
                dateDepart + ", " + lieuDepart + " ----> " +
                        dateArrivee + ", " + lieuArrivee + " | " +
                        conducteur + " | " + info
        );

        row.getChildren().addAll(statusIndicator, details);

        // Make the row clickable
        row.setOnMouseClicked(event -> {
            try {
                // Show detailed trip view when clicked
                MainApplication.showTripDetailScreen(/* you could pass a trip ID here */);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return row;
    }

    @FXML
    private void onAddTripButtonClick() {
        try {
            MainApplication.showAddTripDialog();
            // Refresh the list after adding
            loadSampleData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogoutButtonClick() {
        try {
            UserSession.logout();
            MainApplication.showLoginScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPlanningButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("planning.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBilletButtonClick() {
        try {
            // Assuming you'll create this method
            MainApplication.showBilletScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}