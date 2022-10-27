package se.iths.lab3oliversafstrom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;
import se.iths.lab3oliversafstrom.shapes.Shape;

public class Controller {
    @FXML
    public Spinner<Integer> sizeSpinner;
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
    public ToggleButton circleButton;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private String chatBoxMessage;
    private GraphicsContext context;

    Model model = new Model();
    SvgFileWriter svgFileWriter = new SvgFileWriter();


    public void initialize() {
        setToggleGroup();
        context = canvas.getGraphicsContext2D();
        chatWindow.setItems(model.chatWindowString);
        chatBoxInput.textProperty().bindBidirectional(model.chatBoxInputProperty());

        colorPicker.valueProperty().bindBidirectional(model.colorPickerProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(model.sizeSpinnerProperty());
        bindButton();
    }

    private void bindButton() {
        selectButton.selectedProperty().bindBidirectional(model.selectButtonProperty());
        circleButton.selectedProperty().bindBidirectional(model.circleButtonProperty());
        circleButton.selectedProperty().bindBidirectional(model.rectangleButtonProperty());
        sendButton.disableProperty().bind(model.chatBoxInputProperty().isEmpty());
    }
    //TODO G
    // skriv 2 tester JUnit5


    //TODO VG
    //TODO TREADS for connection
    //TODO connect to server, send paint commands SVG format
    //TODO Chat

    private void setToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        selectButton.setToggleGroup(toggleGroup);
        circleButton.setToggleGroup(toggleGroup);
        rectangleButton.setToggleGroup(toggleGroup);

    }

    public void connectServer(ActionEvent actionEvent) {
        System.out.println("Connecting to server......");
    }

    public void saveToFile() {
        System.out.println("Saving to file.....");
        svgFileWriter.saveToFile(model);
    }

    public void exitProgram() {
        System.exit(0);
    }

    public void undo() {
        model.shapeList.remove(model.shapeList.size() - 1);
        if (!model.undoList.isEmpty()) {
            model.shapeList.add(model.undoList.get(model.undoList.size() - 1));
            model.undoList.remove(model.undoList.size() - 1);
        }
        clearCanvasDrawShapes();
    }
    public void redo() {
        model.shapeList.add(model.undoList.get(model.undoList.size() - 1));
        model.undoList.remove(model.undoList.size() - 1);
        clearCanvasDrawShapes();
    }

    private void clearCanvasDrawShapes() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawShapes(context);
    }

    public void canvasClicked(MouseEvent mouseEvent) {

        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());

        checkShapeAndDraw();

    }

    private void checkShapeAndDraw() {
        if (circleButton.isSelected()) {
            createShapeAndCopyToUndoList();

        } else if (rectangleButton.isSelected()) {
            createShapeAndCopyToUndoList();

        } else if (selectButton.isSelected()) {
            for (int i = 0; i < model.shapeList.size() - 1; i++) {
                Shape shape = model.shapeList.get(i);
                ifFoundChangeValue(shape, i);
            }
        }
        clearCanvasDrawShapes();
    }

    private void ifFoundChangeValue(Shape shape, int index) {
        if (shape.findPosition(model.getMouseX(), model.getMouseY())) {
            createShapeAndCopyToUndoList(shape);
            model.shapeList.remove(index);
            clearCanvasDrawShapes();

        }
    }

    private void createShapeAndCopyToUndoList(Shape shape) {
        if (shape.getClass() == Circle.class) {
            Circle circle = model.copyCircle(shape);
            if (!model.shapeList.isEmpty())
                model.undoList.add(circle);
            Circle newCircle = createNewCircleChanged((Circle) shape);
            model.shapeList.add(newCircle);

        } else if (shape.getClass() == Rectangle.class) {
            Rectangle rectangle = model.copyRectangle(shape);
            if (!model.shapeList.isEmpty())
                model.undoList.add(rectangle);
            Rectangle newRectangle = createNewRectangleChanged((Rectangle) shape);
            model.shapeList.add(newRectangle);
        }
    }

    public void createShapeAndCopyToUndoList() {
        if (circleButton.isSelected()) {
            if (!model.shapeList.isEmpty()) {
                Shape circle = model.copyCircle(model.shapeList.get(model.shapeList.size() - 1));
                model.undoList.add(circle);
            }
            Circle newCircle = model.createNewCircle();
            model.shapeList.add(newCircle);

        } else if (rectangleButton.isSelected()) {
            if (!model.shapeList.isEmpty()) {
                Shape rectangle = model.copyRectangle(model.shapeList.get(model.shapeList.size() - 1));
                model.undoList.add(rectangle);
            }
            Rectangle newRectangle = model.createNewRectangle();
            model.shapeList.add(newRectangle);
        }
    }

    private Rectangle createNewRectangleChanged(Rectangle shape) {
        return new Rectangle(model.getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }

    private Circle createNewCircleChanged(Circle shape) {
        return new Circle(model.getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }





    private void drawShapes(GraphicsContext context) {
        for (var shape : model.shapeList) {
            shape.draw(context);
        }
    }

    public void sendMessage() {
        model.chatWindowString.add("LocalUser: " + model.getChatBoxInput());
        model.chatBoxInputProperty().setValue("");
    }

    public void onEnter() {
        model.chatWindowString.add("LocalUser: " + model.getChatBoxInput());
        model.chatBoxInputProperty().setValue("");
    }
}