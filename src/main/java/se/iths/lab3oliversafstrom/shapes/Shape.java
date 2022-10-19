package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import se.iths.lab3oliversafstrom.Model;

public interface Shape {
    void draw(GraphicsContext context, Model model);
}
