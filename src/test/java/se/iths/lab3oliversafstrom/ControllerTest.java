package se.iths.lab3oliversafstrom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    Controller controller = new Controller();
    String test1 = "test1";


    @Test
    void chatBoxInput() {
        controller.model.chatBoxInputProperty().setValue(test1);

        var expected = "test1";
        var actual = controller.model.chatBoxInputProperty().getValue();

        assertEquals(expected, actual);
    }

}