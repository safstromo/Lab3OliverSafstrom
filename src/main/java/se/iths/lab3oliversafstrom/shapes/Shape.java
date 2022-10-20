package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.Model;

public interface Shape {
    void draw(GraphicsContext context);

    boolean findPosition(double mouseX, double mouseY, Color color);

}