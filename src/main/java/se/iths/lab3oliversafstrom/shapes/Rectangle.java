package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Rectangle implements Shape {
    String type = "rectangle";
    int size;
    double xPosition;
    double yPosition;

    Color color;

    public Rectangle(int size, double xPosition, double yPosition, Color color) {
        this.size = size;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public Color getColor() {
        return color;
    }

    public void setSize(int size) {
        this.size = size;

    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(centerX(), centerY(), getSize(), getSize());


    }

    @Override
    public boolean findPosition(double mouseX, double mouseY) {
        return isWithinShape(mouseX, mouseY);

    }

    public boolean isWithinShape(double mouseX, double mouseY) {

        boolean insideX = mouseX <= centerX() + getSize() && mouseX >= centerX();
        boolean insideY = mouseY <= centerY() + getSize() && mouseY >= centerY();

        return insideX && insideY;
    }

    private double centerX() {
        return xPosition - (getSize() / 2);
    }

    private double centerY() {
        return yPosition - (getSize() / 2);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "type='" + type + '\'' +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle rectangle)) return false;
        return Double.compare(rectangle.xPosition, xPosition) == 0 && Double.compare(rectangle.yPosition, yPosition) == 0 && Objects.equals(type, rectangle.type) && Objects.equals(color, rectangle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, xPosition, yPosition, color);
    }
}
