package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.Controller;
import se.iths.lab3oliversafstrom.Model;

import java.util.Objects;

public class Circle extends javafx.scene.shape.Circle implements Shape {

    double xPosition;
    double yPosition;
    Color color;

    public Circle(double radius, double xPosition, double yPosition, Color color) {
        super.setRadius(radius);
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle circle)) return false;
        return Double.compare(circle.xPosition, xPosition) == 0 && Double.compare(circle.yPosition, yPosition) == 0 && Objects.equals(color, circle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition, color);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", color=" + color +
                '}';
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

    @Override
    public void draw(GraphicsContext context) {
        System.out.println("testar draw Circle");
        context.setFill(color);
        context.fillOval(centerX(), centerY(), getRadius(), getRadius());

    }

    private double centerY() {
        return yPosition - (getRadius() / 2);
    }

    private double centerX() {
        return xPosition - (getRadius() / 2);
    }
}

