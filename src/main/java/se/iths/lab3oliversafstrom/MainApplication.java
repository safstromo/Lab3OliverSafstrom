package se.iths.lab3oliversafstrom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(new FXMLLoader(MainApplication.class.getResource("main-view.fxml")).getLocation());
        Scene scene = new Scene(root, 1280, 800);
        Image icon = new Image(new FileInputStream("src/icon.png"));



        stage.setTitle("Banana Paint");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}