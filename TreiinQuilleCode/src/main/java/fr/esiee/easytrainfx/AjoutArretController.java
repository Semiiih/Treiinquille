package fr.esiee.easytrainfx;

import fr.esiee.dao.EasyTrainDAO;
import fr.esiee.modele.Arret;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AjoutArretController {
    @FXML private TextField nomArretField;
    @FXML private ComboBox<Arret.TypeArret> typeArretComboBox;
    @FXML private Label messageLabel;

    private final EasyTrainDAO dao = new EasyTrainDAO();

    @FXML
    public void initialize() {
        // Populate ComboBox with TypeArret enum values
        typeArretComboBox.getItems().addAll(Arret.TypeArret.values());
    }

    @FXML
    private void onResetButtonClick() {
        nomArretField.clear();
        typeArretComboBox.setValue(null);
        messageLabel.setText("Les champs ont été réinitialisés.");
        messageLabel.setStyle("-fx-text-fill: blue;");
    }

    @FXML
    private void onAddButtonClick() {
        // Validate input
        String nomArret = nomArretField.getText().trim();
        Arret.TypeArret typeArret = typeArretComboBox.getValue();

        if (nomArret.isEmpty() || typeArret == null) {
            messageLabel.setText("Erreur : Tous les champs doivent être remplis !");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Check if the station already exists
        if (dao.verifierArretExistant(nomArret)) {
            messageLabel.setText("Erreur : Un arrêt avec ce nom existe déjà !");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Create and add the new station
        Arret nouvelArret = new Arret(0, nomArret, typeArret);

        try {
            boolean success = dao.ajouterArret(nouvelArret);

            if (success) {
                // Show success message with added station details
                messageLabel.setText("Arrêt ajouté avec succès : \n" +
                        "ID : " + nouvelArret.getId() + "\n" +
                        "Nom : " + nouvelArret.getNom() + "\n" +
                        "Type : " + nouvelArret.getTypeArret());
                messageLabel.setStyle("-fx-text-fill: green;");
            } else {
                messageLabel.setText("Erreur : Impossible d'ajouter l'arrêt.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (Exception e) {
            messageLabel.setText("Erreur : " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}