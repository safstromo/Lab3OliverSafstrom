package se.iths.lab3oliversafstrom;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private ToggleButton selectButton;
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
        setToggleGroup();


    }

    private void setToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        selectButton.setToggleGroup(toggleGroup);
        circleButton.setToggleGroup(toggleGroup);
        rectangleButton.setToggleGroup(toggleGroup);
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Location to save file");
        fileChooser.showOpenDialog(new Stage());

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

    public boolean selectButton(ToggleButton selectButton) {
        return selectButton.isSelected();
    }

    public void canvasClicked(MouseEvent mouseEvent) {

        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());
        System.out.println(mouseEvent.getY());
        System.out.println(mouseEvent.getX());

        checkShapeAndDraw(context);

    }

    private void checkShapeAndDraw(GraphicsContext context) {
        if (drawCircleButton(circleButton)) {
            model.shapeList.add(createNewCircle());
            drawShapes(context);

        } else if (drawRectangleButton(rectangleButton)) {
            model.shapeList.add(createNewRectangle());
            drawShapes(context);
        } else if (selectButton(selectButton)) {
            for (var shape : model.shapeList) {
                if (shape.findPosition(model.getMouseX(), model.getMouseY()))
                    System.out.println("inshape " + shape);
            }
        }
    }

    private void drawShapes(GraphicsContext context) {
        for (var shape : model.shapeList) {
            shape.draw(context);
        }
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