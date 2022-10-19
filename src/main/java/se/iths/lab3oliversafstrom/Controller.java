package se.iths.lab3oliversafstrom;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;

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
    private Button RectangleButton;
    @FXML
    private Button circleButton;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private String chatBoxMessage;

    public void initialize() {
        model = new Model();
        colorPicker = new ColorPicker();
        model.chatWindowString = FXCollections.observableArrayList();
        chatWindow.setItems(model.chatWindowString);
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.fillRect(200,200,200,200);


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

    public boolean drawRectangleButton() {
        return true;
    }

    public boolean drawCircleButton() {
        return true;
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        model.setMouseX(mouseEvent.getSceneX());
        model.setMouseY(mouseEvent.getSceneY());
        System.out.println(model.getMouseX());
        System.out.println(model.getMouseY());
        if (drawCircleButton()) {
            createNewCircle();
        } else if (drawRectangleButton()) {
            createNewRectangle();
        }
    }

    private Rectangle createNewRectangle() {
        return new Rectangle(10, model.getMouseX(), model.getMouseY(), model.getColorPicker().getValue());
    }

    private Circle createNewCircle() {
        return new Circle(10, model.getMouseX(), model.getMouseY(), model.getColorPicker().getValue());
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