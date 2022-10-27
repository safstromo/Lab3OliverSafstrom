package se.iths.lab3oliversafstrom;

import com.sun.scenario.effect.Blend;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Controller controller = new Controller();
    Model model = new Model();
    Circle circle1 = new Circle(10,20,20, Color.BLACK);
    Circle circle2 = new Circle(10,20,20, Color.RED);
    Rectangle rectangle1 = new Rectangle(10,20,20,Color.BLACK);
    Rectangle rectangle2 = new Rectangle(10,20,20, Color.RED);
    String test1 = "test1";
    String test2 = "test2";

    @Test
    void undo() {
        controller.model.shapeList.add(circle1);

        controller.undo(new ActionEvent());
        var expected = 0;
        var actual = controller.model.shapeList.size();

        assertEquals(expected, actual);
    }

    @Test
    void chatBoxInput() {
        controller.model.setChatBoxInput(test1);

        var expected = "test1";
        var actual = controller.model.chatBoxInputProperty().getValue();

        assertEquals(expected,actual);
    }
    @Test
    void chatBoxInputSaveToChatBoxWindow() {
        controller.model.setChatBoxInput(test1);
//       controller.model.chatWindowString.add("LocalUser: " + controller.model.getChatBoxInput());
       controller.onEnter();

        var expected = "LocalUser: test1";
        var actual = controller.model.chatWindowString.get(0);

        assertEquals(expected,actual);

    }
}