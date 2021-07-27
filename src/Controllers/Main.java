package Controllers;

import Utilities.Database.DBConnection;
import Utilities.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Logger.initializeLogManager();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Views/Login.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }


    public static void main(String[] args) {
        // Locale.setDefault(new Locale("fr"));
        launch(args);
        DBConnection.closeConnection();
    }
}