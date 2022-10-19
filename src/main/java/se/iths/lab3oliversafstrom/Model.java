package se.iths.lab3oliversafstrom;


import javafx.collections.ObservableList;
import javafx.scene.control.ColorPicker;


public class Model {
    private double mouseX;
    private double mouseY;
    private ColorPicker colorPicker;
    private String chatBoxInput;


    public String getChatBoxInput() {
        return chatBoxInput;
    }

    public void setChatBoxInput(String chatBoxInput) {
        this.chatBoxInput = chatBoxInput;
    }


    public ObservableList<String> chatWindowString;
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

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public Model(double mouseX, double mouseY, ColorPicker colorPicker) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.colorPicker = colorPicker;
    }

    public Model() {
    }
}
