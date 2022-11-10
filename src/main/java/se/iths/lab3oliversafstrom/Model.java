package se.iths.lab3oliversafstrom;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;
import se.iths.lab3oliversafstrom.shapes.Shape;
import se.iths.lab3oliversafstrom.shapes.ShapeFactory;
import se.iths.lab3oliversafstrom.stuff.Server;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static se.iths.lab3oliversafstrom.shapes.ShapeFactory.*;


public class Model {
    public StringProperty chatBoxInput = new SimpleStringProperty();
    public BooleanProperty selectButton = new SimpleBooleanProperty();
    public BooleanProperty circleButton = new SimpleBooleanProperty();
    public BooleanProperty rectangleButton = new SimpleBooleanProperty();
    public BooleanProperty ServerConnected = new SimpleBooleanProperty();

    public BooleanProperty serverConnectedProperty() {
        return ServerConnected;
    }

    public ObservableList<String> chatWindow = FXCollections.observableArrayList();
    public ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.BLACK);
    public ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(30);
    public Deque<Shape> undoList = new ArrayDeque<>();
    public Deque<Shape> redoList = new ArrayDeque<>();
    public ObservableList<Shape> shapeList = FXCollections.observableArrayList();
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


    public void checkShapeAndDraw() {
        redoList.clear();

        if (circleButtonProperty().getValue()) {
            undoList.addAll(copyShapeListToDeque());
            createShapeAddToList();

        } else if (rectangleButtonProperty().getValue()) {
            undoList.addAll(copyShapeListToDeque());
            createShapeAddToList();

        } else if (selectButtonProperty().getValue()) {
            undoList.addAll(copyShapeListToDeque());

                ShapeFactory.updateShape(findShape(), this);
                newUpdatedShapeList();


        }

    }

    private Shape findShape() {
        return shapeList.stream()
                .filter(shape -> shape.findPosition(getMouseX(), getMouseY())).findAny().orElseThrow();
    }

    public void newUpdatedShapeList() {
        ObservableList<Shape> tempList = getTempList();
        shapeList.clear();
        shapeList.addAll(tempList);
    }

    public ObservableList<Shape> getTempList() {
        ObservableList<Shape> tempList = FXCollections.observableArrayList();
        for (Shape shape : shapeList)
            tempList.add(ShapeFactory.copyShape(shape));
        return tempList;
    }

    public void createShapeAddToList() {
        if (circleButtonProperty().getValue()) {
            shapeList.add(createShape("circle", this));

        } else if (rectangleButtonProperty().getValue()) {
            shapeList.add(createShape("rectangle", this));
        }
    }
//    public void createShapeAddToList() {
//        if (circleButtonProperty().getValue()) {
//            copyLastShapeInShapeListAddToUndoList();
//            shapeList.add(createShape("circle", this));
//
//        } else if (rectangleButtonProperty().getValue()) {
//            copyLastShapeInShapeListAddToUndoList();
//            shapeList.add(createShape("rectangle", this));
//        }
//    }

    public Shape lastShapeInList(List<Shape> list) {
        return list.get(list.size() - 1);

    }

    public void createShapeAndCopyToUndoList(Shape shape) {
        if (shape.getClass() == Circle.class) {
            copyShapeAddToUndoList(shape);

            Circle newCircle = createNewCircleChanged(shape, this);
            shapeList.remove(shape);
            shapeList.add(newCircle);
//            undoList.addLast(newCircle);
        } else if (shape.getClass() == Rectangle.class) {
            copyShapeAddToUndoList(shape);
            Rectangle newRectangle = createNewRectangleChanged(shape, this);
            shapeList.remove(shape);
            shapeList.add(newRectangle);
        }
    }

    public Deque<Shape> copyShapeListToDeque() {
        Deque<Shape> tempDeque = new ArrayDeque<>();
        for (Shape shape : shapeList)
            tempDeque.add(ShapeFactory.copyShape(shape));
        return tempDeque;
    }

    private void copyLastShapeInShapeListAddToUndoList() {
        if (!shapeList.isEmpty()) {
            copyShapeAddToList(lastShapeInList(shapeList), undoList);
        }
    }

    private void copyShapeAddToUndoList(Shape shape) {
        if (!shapeList.isEmpty()) {
            undoList.removeLast();
            copyShapeAddToList(shape, undoList);
        }
    }

    public void importSvgString(String string) {
        try {
            shapeList.add(ShapeFactory.createShape(string));
        } catch (Exception ignored) {
        }

    }
}

