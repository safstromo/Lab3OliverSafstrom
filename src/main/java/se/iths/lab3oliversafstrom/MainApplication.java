package se.iths.lab3oliversafstrom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

        Image icon = new Image(new FileInputStream("src/icon.png"));
        Controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        //TODO 14:15 fredag importera controller. kolla javafx branch


        stage.setTitle("Banana Paint");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}