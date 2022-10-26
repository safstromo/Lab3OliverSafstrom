package se.iths.lab3oliversafstrom;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.shapes.Shape;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Model {
    private double mouseX;
    private double mouseY;

    private StringProperty chatBoxInput = new SimpleStringProperty();
    public List<Shape> shapeList = new ArrayList<>();
    public ObservableList<String> chatWindowString = FXCollections.observableArrayList();
    public List<Shape> undoList = new LinkedList<>();

    public ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>();

    public StringProperty chatBoxInputProperty() {
        return chatBoxInput;
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

    public void connectToServer(){

    }

}
