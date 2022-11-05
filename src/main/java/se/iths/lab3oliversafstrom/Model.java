package se.iths.lab3oliversafstrom;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;
import se.iths.lab3oliversafstrom.shapes.Shape;
import se.iths.lab3oliversafstrom.stuff.Server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static se.iths.lab3oliversafstrom.shapes.ShapeFactory.*;


public class Model {
    public StringProperty chatBoxInput = new SimpleStringProperty();
    public BooleanProperty selectButton = new SimpleBooleanProperty();
    public BooleanProperty circleButton = new SimpleBooleanProperty();
    public BooleanProperty rectangleButton = new SimpleBooleanProperty();
    public ObservableList<String> chatWindow = FXCollections.observableArrayList();
    public ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.BLACK);
    public ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(30);
    public List<Shape> undoList = new LinkedList<>();
    public List<Shape> shapeList = new ArrayList<>();
    public Server server = new Server();
    private double mouseX;
    private double mouseY;


    public BooleanProperty circleButtonProperty() {
        return circleButton;
    }

    public BooleanProperty rectangleButtonProperty() {
        return rectangleButton;
    }

    public StringProperty chatBoxInputProperty() {
        return chatBoxInput;
    }

    public ObjectProperty<Color> colorPickerProperty() {
        return colorPicker;
    }

    public BooleanProperty selectButtonProperty() {
        return selectButton;
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public String getChatBoxInput() {
        return chatBoxInput.get();
    }

    public Integer getSizeSpinner() {
        return sizeSpinner.get();
    }

    public ObjectProperty<Integer> sizeSpinnerProperty() {
        return sizeSpinner;
    }

    public Color getColorPicker() {
        return colorPicker.get();
    }

    public void connectToServer() {

    }


    public void checkShapeAndDraw() {
        if (circleButtonProperty().getValue()) {
            createShapeAndCopyToUndoList();

        } else if (rectangleButtonProperty().getValue()) {
            createShapeAndCopyToUndoList();

        } else if (selectButtonProperty().getValue()) {
            try {
                createShapeAndCopyToUndoList(findShape());
            } catch (Exception ignored) {
            }
        }
    }

    private Shape findShape() {
        return shapeList.stream()
                .filter(shape -> shape.findPosition(getMouseX(), getMouseY())).findAny().orElseThrow();
    }


    public void createShapeAndCopyToUndoList() {
        if (circleButtonProperty().getValue()) {
            copyLastShapeInShapeListAddToUndoList();
            shapeList.add(createShape("circle", this));

        } else if (rectangleButtonProperty().getValue()) {
            copyLastShapeInShapeListAddToUndoList();
            shapeList.add(createShape("rectangle", this));
        }
    }

    private Shape lastShapeInShapeList() {
        return shapeList.get(shapeList.size() - 1);
    }

    public void createShapeAndCopyToUndoList(Shape shape) {
        if (shape.getClass() == Circle.class) {
            copyShapeAddToUndoList(shape);
            Circle newCircle = createNewCircleChanged(shape, this);
            shapeList.remove(shape);
            shapeList.add(newCircle);

        } else if (shape.getClass() == Rectangle.class) {
            copyShapeAddToUndoList(shape);
            Rectangle newRectangle = createNewRectangleChanged(shape, this);
            shapeList.remove(shape);
            shapeList.add(newRectangle);
        }
    }


    private void copyLastShapeInShapeListAddToUndoList() {
        if (!shapeList.isEmpty())
            copyShapeAddToList(lastShapeInShapeList(), undoList);
    }

    private void copyShapeAddToUndoList(Shape shape) {
        if (!shapeList.isEmpty()) {
            copyShapeAddToList(shape, undoList);

        }
    }

    public Shape importSvgString(String string) {


        return null;
    }//TODO
}
