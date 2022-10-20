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

    private Model model = new Model();

    public void initialize() {
        model = new Model();
        model.chatWindowString = FXCollections.observableArrayList();
        model.shapeList = FXCollections.observableArrayList();
        chatWindow.setItems(model.chatWindowString);

    }

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
        GraphicsContext context = canvas.getGraphicsContext2D();
        model.shapeList.remove(model.shapeList.size()-1);
        context.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        drawShapes(context);
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
        GraphicsContext context = canvas.getGraphicsContext2D();

        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());
        checkShapeAndDraw(context);


    }

    private void checkShapeAndDraw(GraphicsContext context) {
        if (checkCircleButton()) {
            model.shapeList.add(createNewCircle());
            drawShapes(context);

        } else if (checkRectangleButton()) {
            model.shapeList.add(createNewRectangle());
            drawShapes(context);
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
}