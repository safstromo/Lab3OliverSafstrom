package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class Rectangle extends Shape {
    int size;
    double xPosition;
    double yPosition;

    public Rectangle(int size, double xPosition, double yPosition, Color color) {
        this.size = size;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }

    Color color = Color.BLACK;

    @Override
    public String toString() {
        return "Rectangle{" +
                "size=" + size +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle rectangle)) return false;
        return size == rectangle.size && Double.compare(rectangle.xPosition, xPosition) == 0 && Double.compare(rectangle.yPosition, yPosition) == 0 && Objects.equals(color, rectangle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, xPosition, yPosition, color);
    }

    public Rectangle(int size, double xPosition, double yPosition) {
        this.size = size;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
