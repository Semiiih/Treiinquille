package fr.esiee.easytrainfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AjoutTrajetController {

    @FXML
    private ComboBox<String> conducteurComboBox;

    @FXML
    private DatePicker dateDepart;

    @FXML
    private TextField heureDepart;

    @FXML
    private DatePicker dateArrivee;

    @FXML
    private TextField heureArrivee;

    @FXML
    private ComboBox<String> lieuDepartComboBox;

    @FXML
    private ComboBox<String> lieuArriveeComboBox;

    @FXML
    private ComboBox<Integer> placesDisponiblesComboBox;

    @FXML
    private TextField trajetNumero;

    @FXML
    private Button annulerButton;

    @FXML
    private Button validerButton;

    @FXML
    private void initialize() {
        // Initialiser les ComboBox avec des valeurs d'exemple
        conducteurComboBox.getItems().addAll("Jean Dupont", "Marie Martin", "Pierre Dubois");

        lieuDepartComboBox.getItems().addAll("Paris", "Lyon", "Marseille", "Lille", "Bordeaux", "Cergy");
        lieuArriveeComboBox.getItems().addAll("Paris", "Lyon", "Marseille", "Lille", "Bordeaux", "Cergy");

        // Places disponibles (capacité du train)
        for (int i = 20; i <= 200; i += 20) {
            placesDisponiblesComboBox.getItems().add(i);
        }

        // Générer automatiquement un numéro de trajet (exemple)
        int randomNum = 10000 + (int)(Math.random() * 90000);
        trajetNumero.setText("TRJ-" + randomNum);
        trajetNumero.setEditable(false);

        // Par défaut, on utilise la date d'aujourd'hui
        dateDepart.setValue(LocalDate.now());
        dateArrivee.setValue(LocalDate.now());

        // Exemples d'heures
        heureDepart.setText("08:00");
        heureArrivee.setText("10:00");
    }

    @FXML
    private void onAnnulerButtonClick(ActionEvent event) {
        // Fermer la fenêtre
        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onValiderButtonClick(ActionEvent event) {
        if (validateInputs()) {
            // Récupérer les valeurs
            String conducteur = conducteurComboBox.getValue();
            LocalDate dateDep = dateDepart.getValue();
            String heureDep = heureDepart.getText();
            LocalDate dateArr = dateArrivee.getValue();
            String heureArr = heureArrivee.getText();
            String lieuDepart = lieuDepartComboBox.getValue();
            String lieuArrivee = lieuArriveeComboBox.getValue();
            Integer places = placesDisponiblesComboBox.getValue();
            String numero = trajetNumero.getText();

            // Créer un nouvel objet Trajet (à implémenter selon votre modèle de données)
            // Trajet nouveauTrajet = new Trajet(conducteur, dateDep, heureDep, dateArr, heureArr,
            //                                 lieuDepart, lieuArrivee, places, numero);

            // Sauvegarder le trajet (exemple)
            saveTrip(conducteur, dateDep, heureDep, dateArr, heureArr,
                    lieuDepart, lieuArrivee, places, numero);

            // Fermer la fenêtre
            Stage stage = (Stage) validerButton.getScene().getWindow();
            stage.close();
        }
    }

    private boolean validateInputs() {
        // Vérifier que tous les champs obligatoires sont remplis
        if (conducteurComboBox.getValue() == null ||
                dateDepart.getValue() == null ||
                heureDepart.getText().isEmpty() ||
                dateArrivee.getValue() == null ||
                heureArrivee.getText().isEmpty() ||
                lieuDepartComboBox.getValue() == null ||
                lieuArriveeComboBox.getValue() == null ||
                placesDisponiblesComboBox.getValue() == null) {

            // Afficher un message d'erreur (à implémenter avec une alerte)
            System.out.println("Veuillez remplir tous les champs obligatoires");
            return false;
        }

        // Vérifier que le lieu de départ est différent du lieu d'arrivée
        if (lieuDepartComboBox.getValue().equals(lieuArriveeComboBox.getValue())) {
            System.out.println("Le lieu de départ et d'arrivée ne peuvent pas être identiques");
            return false;
        }

        // Vérifier que la date et l'heure de départ sont avant la date et l'heure d'arrivée
        LocalDateTime departDateTime = LocalDateTime.of(
                dateDepart.getValue(),
                LocalTime.parse(heureDepart.getText())
        );

        LocalDateTime arriveeDateTime = LocalDateTime.of(
                dateArrivee.getValue(),
                LocalTime.parse(heureArrivee.getText())
        );

        if (departDateTime.isAfter(arriveeDateTime) || departDateTime.isEqual(arriveeDateTime)) {
            System.out.println("La date et l'heure de départ doivent être antérieures à la date et l'heure d'arrivée");
            return false;
        }

        return true;
    }

    // Méthode pour sauvegarder le trajet (à adapter selon votre système de stockage)
    private void saveTrip(String conducteur, LocalDate dateDep, String heureDep,
                          LocalDate dateArr, String heureArr, String lieuDepart,
                          String lieuArrivee, Integer places, String numero) {

        // Exemple de formatage pour l'affichage dans la console
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Nouveau trajet enregistré :");
        System.out.println("Numéro : " + numero);
        System.out.println("Conducteur : " + conducteur);
        System.out.println("Départ : " + lieuDepart + " le " + dateDep.format(formatter) + " à " + heureDep);
        System.out.println("Arrivée : " + lieuArrivee + " le " + dateArr.format(formatter) + " à " + heureArr);
        System.out.println("Places disponibles : " + places);

        // Ici vous pouvez ajouter le code pour sauvegarder dans une base de données
        // ou dans une collection en mémoire
    }
}