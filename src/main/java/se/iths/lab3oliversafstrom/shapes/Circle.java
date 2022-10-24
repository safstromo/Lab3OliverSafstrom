package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


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

    public boolean findPosition(double mouseX, double mouseY) {
        return isWithinShape(mouseX, mouseY);

    }

    private boolean isWithinShape(double mouseX, double mouseY) {
        double distanceX = mouseX - xPosition;
        double distanceY = mouseY - yPosition;
        if (distanceY < 0)
            distanceY = distanceY * -1;
        if (distanceX <0)
            distanceX= distanceX * -1;
        boolean insideX = distanceX < getRadius()/2;
        boolean insideY = distanceY < getRadius()/2;
        return insideX && insideY;
    }

    @Override
    public void draw(GraphicsContext context) {
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

