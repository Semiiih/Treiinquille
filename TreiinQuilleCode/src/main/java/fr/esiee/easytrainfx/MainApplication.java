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
        showLoginScreen();
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




    public static void main(String[] args) {
        launch();
    }
}
