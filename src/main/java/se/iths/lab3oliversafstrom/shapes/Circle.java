package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.util.Objects;

public class Circle implements Shape {

    String type = "circle";
    int radius;
    double xPosition;

    double yPosition;
    Color color;

    public Circle(int radius, double xPosition, double yPosition, Color color) {
        this.radius = radius;
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        this.color = color;
    }

    public double getRadius() {
        return radius;
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



    public boolean findPosition(double mouseX, double mouseY) {
        return isWithinShape(mouseX, mouseY);

    }

    @Override
    public String toString() {
        return "Circle{" +
                "type='" + type + '\'' +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle circle)) return false;
        return Double.compare(circle.xPosition, xPosition) == 0 && Double.compare(circle.yPosition, yPosition) == 0 && Objects.equals(type, circle.type) && Objects.equals(color, circle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, xPosition, yPosition, color);
    }

    public boolean isWithinShape(double mouseX, double mouseY) {
        double distanceX = mouseX - xPosition;
        double distanceY = mouseY - yPosition;
        if (distanceY < 0)
            distanceY = distanceY * -1;
        else if (distanceX < 0)
            distanceX = distanceX * -1;

        boolean insideX = distanceX < getRadius() / 2;
        boolean insideY = distanceY < getRadius() / 2;
        return insideX && insideY;

    }

    @Override
    public void setSize(int size) {
        this.radius = size;
    }

    private double centerY() {
        return yPosition - (getRadius() / 2);
    }


    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toSVG() {
        String svgColorCode = "#" + getColor().toString().substring(2,10);
        return "<circle cx=\"" + xPosition + "\" cy=\"" + yPosition + "\" r=\"" + radius + "\" fill=\"" + svgColorCode + "\"/>";

    }

    private double centerX() {
        return xPosition - (getRadius() / 2);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillOval(centerX(), centerY(), getRadius(), getRadius());
    }
}

