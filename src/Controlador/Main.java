package Controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Vista/Login.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Login NotePad");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.getIcons().add(new Image("Assets/web-programming.png"));
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}