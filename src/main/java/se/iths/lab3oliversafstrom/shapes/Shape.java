package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public interface Shape {
    void draw(GraphicsContext context);

    boolean findPosition(double mouseX, double mouseY);

    boolean isWithinShape(double mouseX, double mouseY);

    void setSize(double size);


    void setColor(Color color);


}