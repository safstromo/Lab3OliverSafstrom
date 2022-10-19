package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class Circle extends Shape {
    int radius;
    double xPosition;
    double yPosition;
    Color color = Color.BLACK;

    public Circle(int radius, double xPosition, double yPosition, Color color) {
        this.radius = radius;
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle circle)) return false;
        return radius == circle.radius && Double.compare(circle.xPosition, xPosition) == 0 && Double.compare(circle.yPosition, yPosition) == 0 && Objects.equals(color, circle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, xPosition, yPosition, color);
    }

    public Circle(int radius, double xPosition, double yPosition) {
        this.radius = radius;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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

