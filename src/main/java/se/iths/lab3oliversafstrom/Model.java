package se.iths.lab3oliversafstrom;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;
import se.iths.lab3oliversafstrom.shapes.Shape;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Model {
    private double mouseX;
    private double mouseY;

    public StringProperty chatBoxInput = new SimpleStringProperty();
    public BooleanProperty selectButton = new SimpleBooleanProperty();
    public BooleanProperty circleButton = new SimpleBooleanProperty();
    public BooleanProperty rectangleButton = new SimpleBooleanProperty();
    public ObservableList<String> chatWindowString = FXCollections.observableArrayList();
    public ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.BLACK);

    public ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(30);
    public List<Shape> undoList = new LinkedList<>();
    public List<Shape> shapeList = new ArrayList<>();


    public void setCircleButton(boolean circleButton) {
        this.circleButton.set(circleButton);
    }



    public Circle copyCircle(Shape shape) {
        return new Circle(shape.getSize(), shape.getXPosition(), shape.getYPosition(), shape.getColor());
    }

    public Rectangle copyRectangle(Shape shape) {
        return new Rectangle(shape.getSize(), shape.getXPosition(), shape.getYPosition(), shape.getColor());
    }


    public Rectangle createNewRectangle() {
        return new Rectangle(getSizeSpinner(), getMouseX(), getMouseY(), getColorPicker());
    }

    public Circle createNewCircle() {
        return new Circle(getSizeSpinner(), getMouseX(), getMouseY(), getColorPicker());
    }

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
    public void connectToServer(){

    }


    public void createShapeAndCopyToUndoList() {
        if (circleButton.get()) {
            if (!shapeList.isEmpty()) {
                Shape circle = copyCircle(shapeList.get(shapeList.size() - 1));
                undoList.add(circle);
            }
            Circle newCircle = createNewCircle();
            shapeList.add(newCircle);

        } else if (rectangleButton.get()) {
            if (!shapeList.isEmpty()) {
                Shape rectangle = copyRectangle(shapeList.get(shapeList.size() - 1));
                undoList.add(rectangle);
            }
            Rectangle newRectangle = createNewRectangle();
            shapeList.add(newRectangle);
        }
    }

    public void createShapeAndCopyToUndoList(Shape shape) {
        if (shape.getClass() == Circle.class) {
            Circle circle = copyCircle(shape);
            if (!shapeList.isEmpty())
                undoList.add(circle);
            Circle newCircle = createNewCircleChanged((Circle) shape);
            shapeList.add(newCircle);

        } else if (shape.getClass() == Rectangle.class) {
            Rectangle rectangle = copyRectangle(shape);
            if (!shapeList.isEmpty())
                undoList.add(rectangle);
            Rectangle newRectangle = createNewRectangleChanged((Rectangle) shape);
            shapeList.add(newRectangle);
        }
    }
    private Rectangle createNewRectangleChanged(Rectangle shape) {
        return new Rectangle(getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), getColorPicker());
    }

    private Circle createNewCircleChanged(Circle shape) {
        return new Circle(getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), getColorPicker());
    }
}
