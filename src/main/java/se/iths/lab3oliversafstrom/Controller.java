package se.iths.lab3oliversafstrom;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;

public class Controller {
    @FXML
    public Spinner sizeSpinner;
    @FXML
    public Label chatLabel;
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
    private GraphicsContext context;

    private Model model = new Model();

    public void initialize() {
        model = new Model();
        model.chatWindowString = FXCollections.observableArrayList();
        model.shapeList = FXCollections.observableArrayList();
        chatWindow.setItems(model.chatWindowString);
        context = canvas.getGraphicsContext2D();

    }
    //TODO G
    //TODO Select shapes and change size/color find location Tips! Implementera en metod på dina shapes för att fråga om koordinaterna är inom shapens area.
    //TODO Gör om undo till comand pattern.
    //TODO Export to SVG file use file diaglog
    // skriv 2 tester JUnit5

    //TODO VG
    //TODO TREADS for connection
    //TODO connect to server, send paint commands SVG format
    //TODO Chat


    private void draw() {

    }

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
        model.shapeList.remove(model.shapeList.size() - 1);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawShapes(context);
    }

    public void redo(ActionEvent actionEvent) {
        System.out.println("Redo");
    } //TODO redo method

    public void resize(ActionEvent actionEvent) {
        System.out.println("Resize");
    } //TODO

    public boolean drawRectangleButton(ToggleButton rectangleButton) {
        return rectangleButton.isSelected();
    }

    public boolean drawCircleButton(ToggleButton circleButton) {
        return circleButton.isSelected();
    }

    public void canvasClicked(MouseEvent mouseEvent) {

        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());
        System.out.println(mouseEvent.getY());
        System.out.println(mouseEvent.getX());

        checkShapeAndDraw(context);

    }

    private void checkShapeAndDraw(GraphicsContext context) {
        if (checkCircleButton()) {
            model.shapeList.add(createNewCircle());
            drawShapes(context);

        } else if (checkRectangleButton()) {
            model.shapeList.add(createNewRectangle());
            drawShapes(context);
        } else {
            for (var shape : model.shapeList) {
                if (shape.findPosition(model.getMouseX(), model.getMouseY()))
                    System.out.println("inshape "+ shape);
            }
        }
    }

    private void drawShapes(GraphicsContext context) {
        for (var shape : model.shapeList) {
            shape.draw(context);
        }
    }

    private boolean checkRectangleButton() {
        return drawRectangleButton(rectangleButton) && !drawCircleButton(circleButton);
    }

    private boolean checkCircleButton() {
        return drawCircleButton(circleButton) && !drawRectangleButton(rectangleButton);
    }

    private Rectangle createNewRectangle() {
        return new Rectangle((Integer) sizeSpinner.getValue(), model.getMouseX(), model.getMouseY(), colorPicker.getValue());
    }

    private Circle createNewCircle() {
        return new Circle((Integer) sizeSpinner.getValue(), model.getMouseX(), model.getMouseY(), colorPicker.getValue());
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

    public boolean selectClicked(ActionEvent actionEvent) {
        return actionEvent.isConsumed();
    }
}