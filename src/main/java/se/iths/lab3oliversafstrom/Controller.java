package se.iths.lab3oliversafstrom;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;

public class Controller {

    double mouseX;
    double mouseY;
    ColorPicker colorPicker;

    //TODO initialize method()
    private Model model = new Model();

    public void connectServer(ActionEvent actionEvent) {
        System.out.println("Connecting to server......");
    }

    public void saveToFile(ActionEvent actionEvent) {
        System.out.println("Saving to file.....");
    }

    public void exitProgram() {
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

    public boolean drawRectangleButton() {
        return true;
    }

    public boolean drawCircleButton() {
        return true;
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getSceneX();
        this.mouseY = mouseEvent.getSceneY();
        System.out.println(mouseX);
        System.out.println(mouseY);
        if (drawCircleButton()) {
            Circle circle = new Circle(10, mouseX, mouseY,colorPicker.getValue());
        } else if (drawRectangleButton()) {
            Rectangle rectangle = new Rectangle(10, mouseX, mouseY,colorPicker.getValue());
        }


    }
}