package fr.esiee.easytrainfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlanningController {

    @FXML
    private ListView<String> conducteursList;

    @FXML
    private Label weekLabel;

    private LocalDate currentWeekStart;

    @FXML
    private void initialize() {
        currentWeekStart = LocalDate.now().with(java.time.DayOfWeek.MONDAY);
        updateWeekLabel();

        // Charger les conducteurs fictifs (remplace par des données dynamiques si nécessaire)
        conducteursList.getItems().addAll("Jean Dupont", "Marie Curie", "Albert Einstein");
    }

    @FXML
    private void previousWeek(ActionEvent event) {
        currentWeekStart = currentWeekStart.minusWeeks(1);
        updateWeekLabel();
    }

    @FXML
    private void nextWeek(ActionEvent event) {
        currentWeekStart = currentWeekStart.plusWeeks(1);
        updateWeekLabel();
    }

    @FXML
    private void onSignupLinkClick(ActionEvent event) {
        try {
            MainApplication.showSignupScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void updateWeekLabel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate weekEnd = currentWeekStart.plusDays(6);
        weekLabel.setText("Semaine du " + currentWeekStart.format(formatter) + " au " + weekEnd.format(formatter));
    }
}
