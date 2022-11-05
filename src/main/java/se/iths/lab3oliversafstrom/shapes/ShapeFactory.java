package se.iths.lab3oliversafstrom.shapes;

import se.iths.lab3oliversafstrom.Model;

import java.util.List;

public class ShapeFactory {


    public static Shape createShape(String shape, Model model) {
        if (shape.equals("circle"))
            return createNewCircle(model);
        else if (shape.equals("rectangle")) {
            return createNewRectangle(model);
        }
        return null;
    }

    public static void copyShapeAddToList(Shape shape, List<Shape> list) {
        if (shape.getClass() == Circle.class)
            list.add(new Circle(shape));
        else if (shape.getClass() == Rectangle.class) {
            list.add(new Rectangle(shape));
        }
    }


    public static Circle createNewCircle(Model model) {
        return new Circle(model.getSizeSpinner(), model.getMouseX(), model.getMouseY(), model.getColorPicker());
    }

    public static Rectangle createNewRectangle(Model model) {
        return new Rectangle(model.getSizeSpinner(), model.getMouseX(), model.getMouseY(), model.getColorPicker());
    }

    public static Rectangle createNewRectangleChanged(Shape shape, Model model) {
        return new Rectangle(model.getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }

    public static Circle createNewCircleChanged(Shape shape, Model model) {
        return new Circle(model.getSizeSpinner(),shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }
}
