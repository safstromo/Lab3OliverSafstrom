package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Shape {
    private int size;
    private double xPosition;

    private double yPosition;
    private Color color;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getXPosition() {
        return xPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(GraphicsContext context);

    public abstract boolean findPosition(double mouseX, double mouseY);

    public abstract boolean isWithinShape(double mouseX, double mouseY);


    public abstract String toSVG();
}