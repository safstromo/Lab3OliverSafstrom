package se.iths.lab3oliversafstrom;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import se.iths.lab3oliversafstrom.shapes.Circle;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Model model = new Model();

    @Test
    void createCircleAddToShapeList() {
        model.sizeSpinnerProperty().setValue(10);
        model.colorPickerProperty().setValue(Color.BLACK);
        model.setMouseY(10);
        model.setMouseX(10);

        model.circleButtonProperty().setValue(true);
        model.checkShapeAndDraw();

        var expected = 1;
        var actual = model.shapeList.size();

        assertEquals(expected,actual);

    }
    @Test
    void createRectangleAddToShapeList() {
        model.sizeSpinnerProperty().setValue(10);
        model.colorPickerProperty().setValue(Color.BLACK);
        model.setMouseY(10);
        model.setMouseX(10);

        model.rectangleButtonProperty().setValue(true);
        model.checkShapeAndDraw();

        var expected = 1;
        var actual = model.shapeList.size();

        assertEquals(expected,actual);

    }

    @Test
    void changeSizeAndColorOfShapeAddOldShapeToUndoList() {
        model.sizeSpinnerProperty().setValue(10);
        model.colorPickerProperty().setValue(Color.BLUE);
        model.setMouseY(10);
        model.setMouseX(10);
        Circle circle = new Circle(1,10,10,Color.BLACK);
        model.shapeList.add(circle);
        model.selectButtonProperty().setValue(true);

        model.checkShapeAndDraw();

        assertEquals(10,model.shapeList.get(0).getSize());
        assertEquals(Color.BLUE, model.shapeList.get(0).getColor());

        assertEquals(1, model.undoList.get(0).getSize());
        assertEquals(Color.BLACK, model.undoList.get(0).getColor());


    }
}