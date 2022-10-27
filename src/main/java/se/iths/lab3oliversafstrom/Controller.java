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
    private ToggleButton circleButton;
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
        sendButton.disableProperty().bind(model.chatBoxInputProperty().isEmpty());
        colorPicker.valueProperty().bindBidirectional(model.colorPickerProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(model.sizeSpinnerProperty());

        //TODO BINDA CHATT MED SERVER!
    }

    private void setToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        selectButton.setToggleGroup(toggleGroup);
        circleButton.setToggleGroup(toggleGroup);
        rectangleButton.setToggleGroup(toggleGroup);
    }
    //TODO G
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
        svgFileWriter.saveToFile(model);


    }

    public void exitProgram() {
        System.exit(0);
    }

    public void undo(ActionEvent actionEvent) {
        model.shapeList.remove(model.shapeList.size() - 1);
        if (!model.undoList.isEmpty()) {
            model.shapeList.add(model.undoList.get(model.undoList.size() - 1));
            model.undoList.remove(model.undoList.size() - 1);
        }
        clearCanvasDrawShapes();
    }//TODO TA BORT GAMLA OBJEKTET.
    //TODO bara kopiera undolistan?

    private void clearCanvasDrawShapes() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawShapes(context);
    }

    public void redo(ActionEvent actionEvent) {
        //   model.shapeList.add(model.undoList.get(model.undoList.size() - 1));
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
            createShapeAndCopyToUndoList();

        } else if (drawRectangleButton(rectangleButton)) {
            createShapeAndCopyToUndoList();

        } else if (selectButton(selectButton)) {
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
            Circle circle = copyCircle((Circle) shape);
            if (!model.shapeList.isEmpty())
                model.undoList.add(circle);
            Circle newCircle = createNewCircleChanged((Circle) shape);
            model.shapeList.add(newCircle);

        } else if (shape.getClass() == Rectangle.class) {
            Rectangle rectangle = copyRectangle((Rectangle) shape);
            if (!model.shapeList.isEmpty())
                model.undoList.add(rectangle);
            Rectangle newRectangle = createNewRectangleChanged((Rectangle) shape);
            model.shapeList.add(newRectangle);
        }
    }

    private void createShapeAndCopyToUndoList() {
        if (drawCircleButton(circleButton)) {
            if (!model.shapeList.isEmpty()) {
//                Shape circle = copyCircle((Circle) model.shapeList.get(model.shapeList.size()-1));
//                model.undoList.add(circle);
            }
            Circle newCircle = createNewCircle();
            model.shapeList.add(newCircle);

        } else if (drawRectangleButton(rectangleButton)) {
            if (!model.shapeList.isEmpty()) {
//                Shape rectangle = copyRectangle((Rectangle) model.shapeList.get(model.shapeList.size()-1));
//                model.undoList.add(rectangle);
            }
            Rectangle newRectangle = createNewRectangle();
            model.shapeList.add(newRectangle);
        }
    }

    private Rectangle createNewRectangleChanged(Rectangle shape) {
        return new Rectangle(model.getSizeSpinner(), shape.getxPosition(), shape.getyPosition(), model.getColorPicker());
    }

    private static Rectangle copyRectangle(Rectangle shape) {
        return new Rectangle(shape.getSize(), shape.getxPosition(), shape.getyPosition(), shape.getColor());
    }

    private Circle createNewCircleChanged(Circle shape) {
        return new Circle(model.getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }

    private static Circle copyCircle(Circle shape) {
        return new Circle((int) shape.getSize(), shape.getXPosition(), shape.getYPosition(), shape.getColor());
    }

    private void drawShapes(GraphicsContext context) {
        for (var shape : model.shapeList) {
            shape.draw(context);
        }
    }

    private Rectangle createNewRectangle() {
        return new Rectangle(model.getSizeSpinner(), model.getMouseX(), model.getMouseY(), model.getColorPicker());
    }

    private Circle createNewCircle() {
        return new Circle(model.getSizeSpinner(), model.getMouseX(), model.getMouseY(), model.getColorPicker());
    }

    public void sendMessage() {
        model.chatWindowString.add("LocalUser: " + model.getChatBoxInput());
        chatBoxInput.setText("");
    }

    public void onEnter() {
        model.chatWindowString.add("LocalUser: " + model.getChatBoxInput());
        chatBoxInput.setText("");
    }
}