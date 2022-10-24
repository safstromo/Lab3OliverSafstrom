package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.util.Objects;

public class Rectangle extends javafx.scene.shape.Rectangle implements Shape {

    double xPosition;
    double yPosition;

    Color color;

    public Rectangle(int size, double xPosition, double yPosition, Color color) {
        super.setHeight(size);
        super.setWidth(size);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }
    public void setSize(double size) {
        super.setWidth(size);
        super.setHeight(size);
    }

    public void setColor(Color color) {
        this.color = color;
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

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(centerX(), centerY(), getHeight(), getWidth());


    }

    @Override
    public boolean findPosition(double mouseX, double mouseY) {
        return isWithinShape(mouseX, mouseY);

    }

    public boolean isWithinShape(double mouseX, double mouseY) {

        boolean insideX = mouseX <= centerX() + getHeight() && mouseX >= centerX();
        boolean insideY = mouseY <= centerY() + getWidth() && mouseY >= centerY();

        return insideX && insideY;
    }

    private double centerX() {
        return xPosition - (getHeight() / 2);
    }

    private double centerY() {
        return yPosition - (getWidth() / 2);
    }
}
