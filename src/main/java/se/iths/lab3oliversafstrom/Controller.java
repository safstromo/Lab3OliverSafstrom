package se.iths.lab3oliversafstrom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Controller {
//TODO initialize method()
private Model model = new Model();

    public void connectServer(ActionEvent actionEvent) {
        System.out.println("Connecting to server......");
    }

    public void saveToFile(ActionEvent actionEvent) {
        System.out.println("Saving to file.....");
    }

    public void exitProgram(ActionEvent actionEvent) {
        System.out.println("Good bye!");
        System.exit(0);
    }

    public void undo(ActionEvent actionEvent) {
        System.out.println("Undo");
    }

    public void redo(ActionEvent actionEvent) {
        System.out.println("Redo");
    }

    public void resize(ActionEvent actionEvent) {
        System.out.println("Resize");
    }

    public void drawRectangleButton(ActionEvent actionEvent) {

    }

    public void drawCircleButton(ActionEvent actionEvent) {

    }
}