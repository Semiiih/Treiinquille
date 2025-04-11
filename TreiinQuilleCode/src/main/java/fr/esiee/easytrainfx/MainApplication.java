package fr.esiee.easytrainfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        showBilletScreen();
        stage.setTitle("Treiinquille");
        stage.show();
    }

    public static void showLoginScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        primaryStage.setScene(scene);
    }

    public static void showPlanningScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("planning.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setScene(scene);
    }

    public static void showSignupScreen() throws IOException {
        System.out.println("Chargement de signup-view.fxml...");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fr/esiee/easytrainfx/signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        primaryStage.setScene(scene);
    }

    public static void showDashboardScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        primaryStage.setScene(scene);
    }

    public static void showTrajetScreen() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("trajets.fxml"));

            // If not found, try alternative names
            if (fxmlLoader.getLocation() == null) {
                System.out.println("Trying alternative FXML file name for trajets screen");
                fxmlLoader = new FXMLLoader(MainApplication.class.getResource("trajets.fxml"));
            }

            if (fxmlLoader.getLocation() == null) {
                throw new IOException("Cannot find FXML file for trajets screen");
            }

            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Failed to load trajets screen: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static void showTripDetailScreen(/* You could add parameters here */) throws IOException {
        // This will be implemented when you create the trip detail view
        // For now, it just shows the trajets view
        showTrajetScreen();
    }

    public static void showBilletScreen() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("billets.fxml"));

            if (fxmlLoader.getLocation() == null) {
                System.out.println("Fichier FXML pour l'écran des billets introuvable");
                throw new IOException("Cannot find billets.fxml file");
            }

            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Échec du chargement de l'écran des billets: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static void showAddTripDialog() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ajoutTrajet.fxml"));

            // If the file isn't found with that name, try the name from your first document
            if (fxmlLoader.getLocation() == null) {
                fxmlLoader = new FXMLLoader(MainApplication.class.getResource("trajets.fxml"));
            }

            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un trajet");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println("Could not load the add trip dialog: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }





    public static void main(String[] args) {
        launch();
    }
}
