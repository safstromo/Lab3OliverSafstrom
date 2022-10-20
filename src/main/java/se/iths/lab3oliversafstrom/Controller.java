package se.iths.lab3oliversafstrom;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;
import se.iths.lab3oliversafstrom.shapes.ShapeFactory;

public class Controller {
    @FXML
    private Button sendButton;
    @FXML
    private TextField chatBoxInput;
    @FXML
    private ListView<String> chatWindow;
    @FXML
    private Button selectShape;
    @FXML
    private ToggleButton rectangleButton;
    @FXML
    private ToggleButton circleButton;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private String chatBoxMessage;

    public void initialize() {
        model = new Model();
        model.chatWindowString = FXCollections.observableArrayList();
        model.shapeObservableList = FXCollections.observableArrayList();
        chatWindow.setItems(model.chatWindowString);

    }

    private void draw() {

    }

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

    public boolean drawRectangleButton(ToggleButton rectangleButton) {
        return rectangleButton.isSelected();
    }

    public boolean drawCircleButton(ToggleButton circleButton) {
        return circleButton.isSelected();
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());
        System.out.println(model.getMouseX());
        System.out.println(model.getMouseY());

        GraphicsContext context = canvas.getGraphicsContext2D();
        if (drawCircleButton(circleButton) && !drawRectangleButton(rectangleButton)) {
            Circle circle = createNewCircle();
            circle.draw(context);
//            context.setFill(colorPicker.getValue());
//            context.fillOval(model.getMouseX()-25, model.getMouseY()-25, 50, 50);
            System.out.println("Circle = " + drawCircleButton(circleButton));
        }else if (drawRectangleButton(rectangleButton) && !drawCircleButton(circleButton)){
            context.setFill(colorPicker.getValue());
            context.fillRect(model.getMouseX()-50, model.getMouseY()-50, 100,100);
            System.out.println("Rectangle = "+ drawRectangleButton(rectangleButton));

        }

    }

    private Rectangle createNewRectangle() {
        return new Rectangle(10, model.getMouseX(), model.getMouseY(), colorPicker.getValue());
    }

    private Circle createNewCircle() {
        return new Circle(50, model.getMouseX(), model.getMouseY(), colorPicker.getValue());
    }

    public void sendMessage() {
        model.setChatBoxInput(chatBoxInput.getText());
        model.chatWindowString.add("LocalUser: " + model.getChatBoxInput());
        chatBoxInput.setText("");
    }

    public void onEnter() {
        model.setChatBoxInput(chatBoxInput.getText());
        model.chatWindowString.add("LocalUser: " + model.getChatBoxInput());
        chatBoxInput.setText("");
    }
}