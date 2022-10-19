package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.paint.Color;

import java.util.Objects;

public class Rectangle extends javafx.scene.shape.Rectangle {

    double xPosition;
    double yPosition;

    public Rectangle(int size, double xPosition, double yPosition, Color color) {
        super.setHeight(size);
        super.setWidth(size);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        super.setFill(color);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle rectangle)) return false;
        return Double.compare(rectangle.xPosition, xPosition) == 0 && Double.compare(rectangle.yPosition, yPosition) == 0 && Objects.equals(color, rectangle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition, color);
    }

    Color color = Color.BLACK;

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
