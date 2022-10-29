package se.iths.lab3oliversafstrom;

import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import se.iths.lab3oliversafstrom.shapes.Circle;
import se.iths.lab3oliversafstrom.shapes.Rectangle;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = new Controller();
    Model model = new Model();

    Circle circle1 = new Circle(10, 20, 20, Color.BLACK);
    Circle circle2 = new Circle(10, 20, 20, Color.RED);
    Rectangle rectangle1 = new Rectangle(10, 20, 20, Color.BLACK);
    Rectangle rectangle2 = new Rectangle(10, 20, 20, Color.RED);
    String test1 = "test1";
    String test2 = "test2";

    @Test
    void chatBoxInput() {
        controller.model.chatBoxInputProperty().setValue(test1);

        var expected = "test1";
        var actual = controller.model.chatBoxInputProperty().getValue();

        assertEquals(expected, actual);
    }

    @Test
    void chatBoxInputSaveToChatBoxWindowListOnEnter() {
        controller.model.chatBoxInputProperty().setValue(test1);
        controller.model.chatBoxInputProperty().setValue(test2);

        controller.model.chatWindowString.add("LocalUser: " + controller.model.getChatBoxInput());
        controller.onEnter();

        var expected = "LocalUser: test2";
        var actual = controller.model.chatWindowString.get(1);

        assertEquals(expected, actual);

    }
    @Test
    void chatBoxInputSaveToChatBoxWindowListOnSendButton() {
        controller.model.chatBoxInputProperty().setValue(test1);
        controller.model.chatBoxInputProperty().setValue(test2);

        controller.model.chatWindowString.add("LocalUser: " + controller.model.getChatBoxInput());
        controller.sendMessage();

        var expected = "LocalUser: test2";
        var actual = controller.model.chatWindowString.get(1);

        assertEquals(expected, actual);

    }
    @Test
    void addShapeToShapeList() {
       controller.initialize();

        controller.model.setCircleButton(true);
        model.createShapeAndCopyToUndoList();

        var expected = 1;
        var actual = controller.model.shapeList.size();
        assertEquals(expected, actual);


    }


}