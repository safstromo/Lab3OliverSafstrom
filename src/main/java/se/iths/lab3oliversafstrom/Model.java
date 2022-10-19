package se.iths.lab3oliversafstrom;


import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;

public class Model {
    private double mouseX;
    private double mouseY;
    private ColorPicker colorPicker;
    private String chatBoxInput;
    private ListView chatWindow;

    public String getChatBoxInput() {
        return chatBoxInput;
    }

    public void setChatBoxInput(String chatBoxInput) {
        this.chatBoxInput = chatBoxInput;
    }

    public ListView getChatWindow() {
        return chatWindow;
    }

    public void setChatWindow(ListView chatWindow) {
        this.chatWindow = chatWindow;
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
