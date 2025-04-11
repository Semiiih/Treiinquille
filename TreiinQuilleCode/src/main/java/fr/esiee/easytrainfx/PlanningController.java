package fr.esiee.easytrainfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanningController {
    @FXML
    private Label usernameLabel;

    @FXML
    private BorderPane rootPane; // Add this to reference the root BorderPane

    private final String[] daysOfWeek = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    private final String[] timeSlots = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};

    // Sample data for scheduled slots (row, column)
    private final List<int[]> scheduledSlots = new ArrayList<>();

    @FXML
    public void initialize() {
        // Set username
        if (usernameLabel != null) {
            usernameLabel.setText(UserSession.getCurrentUser() != null ?
                    UserSession.getCurrentUser().getUsername() : "$NAME");
        }

        // Initialize sample scheduled slots (time, day)
        scheduledSlots.add(new int[]{1, 1}); // 09:00 Mardi
        scheduledSlots.add(new int[]{1, 2}); // 09:00 Mercredi
        scheduledSlots.add(new int[]{2, 1}); // 10:00 Mardi
        scheduledSlots.add(new int[]{2, 2}); // 10:00 Mercredi
        scheduledSlots.add(new int[]{2, 4}); // 10:00 Vendredi
        scheduledSlots.add(new int[]{3, 2}); // 11:00 Mercredi

        // Create planning view
        BorderPane planningContent = createPlanningView();

        // Add to the root pane directly (this is the key change)
        if (rootPane != null) {
            rootPane.setCenter(planningContent);
        }
    }

    private BorderPane createPlanningView() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #333333;");

        // Title for the planning view
        Label planningTitle = new Label("Tout les planning");
        planningTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        planningTitle.setTextFill(Color.WHITE);
        planningTitle.setPadding(new Insets(0, 0, 20, 0));
        root.setTop(planningTitle);

        // Create split pane for conductors and timetable
        SplitPane splitPane = new SplitPane();

        // Left side - Conductors list
        VBox conductorsBox = createConductorsPanel();

        // Right side - Timetable
        VBox timetableBox = createTimetablePanel();

        splitPane.getItems().addAll(conductorsBox, timetableBox);
        splitPane.setDividerPositions(0.3);

        root.setCenter(splitPane);

        return root;
    }

    private VBox createConductorsPanel() {
        VBox conductorsBox = new VBox(10);
        conductorsBox.setPadding(new Insets(20));
        conductorsBox.setStyle("-fx-background-color: white; -fx-background-radius: 10 0 0 10;");

        Label title = new Label("Conducteurs");
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        conductorsBox.getChildren().add(title);

        // Add sample conductors
        for (int i = 1; i <= 7; i++) {
            HBox conductorRow = new HBox(10);
            conductorRow.setAlignment(Pos.CENTER_LEFT);

            Label iconLabel = new Label("⊞");
            Label nameLabel = new Label("$CO_NAME");

            conductorRow.getChildren().addAll(iconLabel, nameLabel);
            conductorsBox.getChildren().add(conductorRow);
        }

        return conductorsBox;
    }

    private VBox createTimetablePanel() {
        VBox timetableBox = new VBox(10);
        timetableBox.setPadding(new Insets(20));
        timetableBox.setStyle("-fx-background-color: #232323; -fx-background-radius: 0 10 10 0;");

        // Timetable title
        Label title = new Label("Emploi du temps");
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        title.setTextFill(Color.WHITE);
        timetableBox.getChildren().add(title);

        // Grid for timetable
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // Add day headers
        for (int day = 0; day < daysOfWeek.length; day++) {
            Label dayLabel = new Label(daysOfWeek[day]);
            dayLabel.setTextFill(Color.WHITE);
            dayLabel.setPrefWidth(60);
            dayLabel.setAlignment(Pos.CENTER);
            gridPane.add(dayLabel, day + 1, 0);
        }

        // Add time slots and checkboxes
        for (int time = 0; time < timeSlots.length; time++) {
            Label timeLabel = new Label(timeSlots[time]);
            timeLabel.setTextFill(Color.WHITE);
            timeLabel.setPrefWidth(60);
            gridPane.add(timeLabel, 0, time + 1);

            // Add checkboxes for each day at this time
            for (int day = 0; day < daysOfWeek.length; day++) {
                StackPane cell = createTimeCell(time, day);
                gridPane.add(cell, day + 1, time + 1);
            }
        }

        timetableBox.getChildren().add(gridPane);

        // Navigation controls
        HBox navigation = new HBox(10);
        navigation.setAlignment(Pos.CENTER);
        navigation.setPadding(new Insets(20, 0, 0, 0));

        Button prevButton = new Button("◀");
        Label weekLabel = new Label("$SEMAINE");
        weekLabel.setTextFill(Color.WHITE);
        Button nextButton = new Button("▶");

        navigation.getChildren().addAll(prevButton, weekLabel, nextButton);
        timetableBox.getChildren().add(navigation);

        return timetableBox;
    }

    private StackPane createTimeCell(int timeIndex, int dayIndex) {
        StackPane cell = new StackPane();
        cell.setPrefSize(40, 40);

        // Create cell background
        Region background = new Region();
        background.setPrefSize(30, 30);
        background.setStyle("-fx-border-color: #555555; -fx-border-width: 1; -fx-background-color: transparent;");

        // Check if this slot is scheduled
        boolean isScheduled = false;
        for (int[] slot : scheduledSlots) {
            if (slot[0] == timeIndex && slot[1] == dayIndex) {
                isScheduled = true;
                break;
            }
        }

        if (isScheduled) {
            background.setStyle("-fx-border-color: #00FF00; -fx-border-width: 1; -fx-background-color: #00FF00;");
        }

        cell.getChildren().add(background);

        // Make cells clickable
        cell.setOnMouseClicked(event -> {
            boolean currentlyScheduled = background.getStyle().contains("#00FF00");
            if (currentlyScheduled) {
                background.setStyle("-fx-border-color: #555555; -fx-border-width: 1; -fx-background-color: transparent;");
            } else {
                background.setStyle("-fx-border-color: #00FF00; -fx-border-width: 1; -fx-background-color: #00FF00;");
            }
        });

        return cell;
    }

    @FXML
    protected void onLogoutButtonClick() throws IOException {
        UserSession.logout();
        MainApplication.showLoginScreen();
    }

    @FXML
    public void onTrajetButtonClick(MouseEvent event) {
        try {
            MainApplication.showTrajetScreen();
        } catch (IOException e) {
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
            e.printStackTrace();
        }
    }
}