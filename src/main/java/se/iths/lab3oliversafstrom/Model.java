package se.iths.lab3oliversafstrom;


import javafx.collections.ObservableList;
import javafx.scene.control.ColorPicker;
import se.iths.lab3oliversafstrom.shapes.Shape;

import java.util.LinkedList;
import java.util.List;


public class Model {
    private double mouseX;
    private double mouseY;
    private ColorPicker colorPicker;
    private String chatBoxInput;
    public ObservableList<Shape> shapeList;
    public ObservableList<String> chatWindowString;
    public List<Shape> undoList = new LinkedList<>();
    public List<Shape> shapeListCopy;
    public Shape shapeCopy;


    public String getChatBoxInput() {
        return chatBoxInput;
    }

    public void setChatBoxInput(String chatBoxInput) {
        this.chatBoxInput = chatBoxInput;
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

    public Model() {
    }
}
