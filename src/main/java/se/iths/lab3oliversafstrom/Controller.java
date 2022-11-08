package se.iths.lab3oliversafstrom;


import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.iths.lab3oliversafstrom.shapes.Shape;
import se.iths.lab3oliversafstrom.shapes.ShapeFactory;
import se.iths.lab3oliversafstrom.stuff.SvgFileWriter;

public class Controller {
    @FXML
    public Spinner<Integer> sizeSpinner;
    @FXML
    public Label chatLabel;
    @FXML
    public Button sendShape;
    @FXML
    public Label serverConnected;
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
    public Canvas canvas;
    @FXML
    private ColorPicker colorPicker;

    public GraphicsContext context;
    public Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    Model model = new Model();
    SvgFileWriter svgFileWriter = new SvgFileWriter();


    public void initialize() {
        setToggleGroup();
        context = canvas.getGraphicsContext2D();
        chatWindow.setItems(model.chatWindow);
        chatBoxInput.textProperty().bindBidirectional(model.chatBoxInputProperty());
        model.shapeList.addListener((ListChangeListener<Shape>) onChange -> drawShapes(context));
        bindTool();
        bindButton();
    }

    private void bindTool() {
        colorPicker.valueProperty().bindBidirectional(model.colorPickerProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(model.sizeSpinnerProperty());
    }

    private void bindButton() {
        selectButton.selectedProperty().bindBidirectional(model.selectButtonProperty());
        circleButton.selectedProperty().bindBidirectional(model.circleButtonProperty());
        rectangleButton.selectedProperty().bindBidirectional(model.rectangleButtonProperty());
        sendButton.disableProperty().bind(model.chatBoxInputProperty().isEmpty());
        serverConnected.disableProperty().bind(model.serverConnectedProperty());

    }


    //TODO VG
    //TODO TREADS for connection
    //TODO connect to server, send paint commands SVG format
    //Todo göra shapesList till observable så man kan ta emot shapes
    //ToDO Label för att kolla om man är connected eller inte.

    private void setToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        selectButton.setToggleGroup(toggleGroup);
        circleButton.setToggleGroup(toggleGroup);
        rectangleButton.setToggleGroup(toggleGroup);

    }

    public void connectServer() {
        System.out.println("Connecting to server......");
        model.server.connect(model);
    }

    public void saveToFile() {
        System.out.println("Saving to file.....");
        svgFileWriter.saveToFile(model, stage);
    }

    public void exitProgram() {
        System.exit(0);
    }

    public void undo() {
        model.shapeList.remove(model.lastShapeInList(model.shapeList));
        if (!model.undoList.isEmpty())
            moveLastShapeFromUndoToShapeList();
        clearCanvasDrawShapes();
    }

    public void redo() { //TODO Implement
        moveLastShapeFromUndoToShapeList();
        clearCanvasDrawShapes();
    }

    private void moveLastShapeFromUndoToShapeList() {
        model.shapeList.add(model.undoList.get(model.undoList.size() - 1));
        model.undoList.remove(model.undoList.size() - 1);
    }

    public void clearCanvasDrawShapes() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawShapes(context);
    }

    public void canvasClicked(MouseEvent mouseEvent) {

        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());

        model.checkShapeAndDraw();
        clearCanvasDrawShapes();
    }

    public void drawShapes(GraphicsContext context) {
        for (var shape : model.shapeList) {
            shape.draw(context);
        }
    }

    public void sendMessage() {
        model.server.sendMessage(model.getChatBoxInput());
    }

    public void onEnter() {
        model.server.sendMessage(model.getChatBoxInput());
    }

    public void sendShape() {
        model.server.sendMessage(model.lastShapeInList(model.shapeList).toSVG());
    }

    public void importSvgString(String string) {
       model.shapeList.add(ShapeFactory.createShape(string));
       clearCanvasDrawShapes();

    }
}