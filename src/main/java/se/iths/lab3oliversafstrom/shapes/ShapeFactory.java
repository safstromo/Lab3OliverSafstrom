package se.iths.lab3oliversafstrom.shapes;

import javafx.scene.paint.Color;
import se.iths.lab3oliversafstrom.Model;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShapeFactory {
    static Pattern color = Pattern.compile("fill=\\d+");
    static Pattern x = Pattern.compile("x=.\\d+");
    static Pattern y = Pattern.compile("y=.\\d+");
    static Pattern radius = Pattern.compile("r=\\d+");
    Pattern size = Pattern.compile("width=.\\d+");


    public static Shape createShape(String shape, Model model) {
        if (shape.equals("circle"))
            return createNewCircle(model);
        else if (shape.equals("rectangle")) {
            return createNewRectangle(model);
        }
        return null;
    }

    public static Shape createShape(String SVGString) {
        if (SVGString.contains("circle"))
            return new Circle(findSVGValue(SVGString, radius), findSVGValue(findSVGValue(SVGString, x), findSVGValue(SVGString, y), findSVGValue(SVGString, color)));
        else if (SVGString.contains("rect")) {
            return new Rectangle(findSVGValue(SVGString, radius), findSVGValue(findSVGValue(SVGString, x), findSVGValue(SVGString, y), findSVGValue(SVGString, color)));
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

    public static Rectangle createNewRectangle(int size, double x, double y, Color color) {
        return new Rectangle(size, x, y, color);
    }

    public static Rectangle createNewRectangleChanged(Shape shape, Model model) {
        return new Rectangle(model.getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }

    public static Circle createNewCircleChanged(Shape shape, Model model) {
        return new Circle(model.getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }

    public static String findSVGValue(String string, Pattern pattern) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            String found = matcher.group(0);
            List<String> foundStrings = Arrays.stream(found.split("=\"?")).toList();
            return foundStrings.get(1);
        }
        return "";
    }
}
