package se.iths.lab3oliversafstrom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    public Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

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
        rectangleButton.selectedProperty().bindBidirectional(model.rectangleButtonProperty());
        sendButton.disableProperty().bind(model.chatBoxInputProperty().isEmpty());
    }
    //TODO G
    // skriv 2 tester JUnit5


    //TODO fixa shapebuilder//factory!!!


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
        svgFileWriter.saveToFile(model, stage);
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

    public void clearCanvasDrawShapes() {
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
            model.createShapeAndCopyToUndoList();

        } else if (rectangleButton.isSelected()) {
            model.createShapeAndCopyToUndoList();

        } else if (selectButton.isSelected()) {
            for (int i = 0; i < model.shapeList.size() - 1; i++) {
                Shape shape = model.shapeList.get(i);
                model.ifFoundChangeValue(shape, i);
            }
        }
        clearCanvasDrawShapes();
    }


    public void drawShapes(GraphicsContext context) {
        for (var shape : model.shapeList) {
            shape.draw(context);
        }
    }

    public void sendMessage() {
        model.chatWindowString.add("You: " + model.getChatBoxInput());
        model.chatBoxInputProperty().setValue("");
    }

    public void onEnter() {
        model.chatWindowString.add("You: " + model.getChatBoxInput());
        model.chatBoxInputProperty().setValue("");
    }
}