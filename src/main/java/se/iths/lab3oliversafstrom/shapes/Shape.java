package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;


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

    public double centerY() {
        return getYPosition() - (getSize() / 2.0);
    }

    public double centerX() {
        return getXPosition() - (getSize() / 2.0);
    }

    public abstract void draw(GraphicsContext context);

    public abstract boolean findPosition(double mouseX, double mouseY);

    public abstract boolean isWithinShape(double mouseX, double mouseY);


    public abstract String toSVG();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;
        return size == shape.size && Double.compare(shape.xPosition, xPosition) == 0 && Double.compare(shape.yPosition, yPosition) == 0 && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, xPosition, yPosition, color);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "size=" + size +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", color=" + color +
                '}';
    }
}