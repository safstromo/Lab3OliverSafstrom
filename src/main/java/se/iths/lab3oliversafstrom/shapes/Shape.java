package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;


public interface Shape {
    void draw(GraphicsContext context);

    boolean findPosition(double mouseX, double mouseY);


}