package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.Model;

public class ShapeFactory {

    public Shape getShape(Model model, String shapeType){

        if (shapeType.equalsIgnoreCase("circle")){
            return new Circle(50,model.getMouseX(),model.getMouseY(), Color.BLACK );
        } else if (shapeType.equals("rectangle")) {
            return new Rectangle(50, model.getMouseX(),model.getMouseY(),Color.BLACK);
        }
    return null;
    }


}
