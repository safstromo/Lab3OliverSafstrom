package se.iths.lab3oliversafstrom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import se.iths.lab3oliversafstrom.shapes.Shape;
import se.iths.lab3oliversafstrom.stuff.Command;

import se.iths.lab3oliversafstrom.stuff.ShapeCopy;

import java.io.File;

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

    private Model model;
    private Command command;

    public void initialize() {
        model = new Model();
        command = new Command();
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




    public void connectServer(ActionEvent actionEvent) {
        System.out.println("Connecting to server......");
    }

    public void saveToFile(ActionEvent actionEvent) {
        System.out.println("Saving to file.....");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Location to save file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".svg Files", "*.svg"));

        File savePath = fileChooser.showSaveDialog(new Stage());
        System.out.println(savePath);

    }

    public void exitProgram() {
        System.exit(0);
    }

    public void undo(ActionEvent actionEvent) {
        model.shapeList.remove(model.shapeList.size() - 1);
        model.shapeList.add(model.undoList.get( model.undoList.size() -1));
        clearCanvasDrawShapes();
    }

    private void clearCanvasDrawShapes() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawShapes(context);
    }

    public void redo(ActionEvent actionEvent) {
        model.shapeList.add(model.undoList.get(model.undoList.size() - 1));
        model.undoList.remove(model.undoList.size() - 1);
        clearCanvasDrawShapes();
    }


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

        checkShapeAndDraw(context);

    }

    private void checkShapeAndDraw(GraphicsContext context) {
        if (drawCircleButton(circleButton)) {
            Circle circle = createNewCircle();
            model.shapeList.add(circle);
            createCopyAddToToUndoList(circle);

        } else if (drawRectangleButton(rectangleButton)) {
            Rectangle rectangle = createNewRectangle();
            model.shapeList.add(rectangle);
            createCopyAddToToUndoList(rectangle);

        } else if (selectButton(selectButton)) {
            ObservableList<Shape> shapeList = model.shapeList;
            for (int i = 0; i < shapeList.size(); i++) {
                Shape shape = shapeList.get(i);
                ifFoundChangeValue(shape,i);
            }
        }
        drawShapes(context);
    }

    private void ifFoundChangeValue(Shape shape, int index) {
        if (shape.findPosition(model.getMouseX(), model.getMouseY())) {
            createCopyAddToToUndoList(shape);
            shape.setColor(colorPicker.getValue());
            shape.setSize((Integer) sizeSpinner.getValue());



        }
    }

    private void createCopyAddToToUndoList(Shape shape) {
        if (shape.getClass() == Circle.class) {
            Circle circle = new Circle(((Circle) shape).getRadius(), ((Circle) shape).getxPosition(),((Circle) shape).getyPosition(),((Circle) shape).getColor());
            model.undoList.add(circle);
        } else if (shape.getClass()== Rectangle.class) {
            Rectangle rectangle = new Rectangle(((Rectangle) shape).getSize(),((Rectangle) shape).getxPosition(),((Rectangle) shape).getyPosition(),((Rectangle) shape).getColor());
            model.undoList.add(rectangle);
        }
//        ShapeCopy shapeCopy = new ShapeCopy();
//        shapeCopy.setObjectCopy(shape);
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