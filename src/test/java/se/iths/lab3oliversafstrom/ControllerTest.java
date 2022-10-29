package se.iths.lab3oliversafstrom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    Controller controller = new Controller();
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

        controller.model.chatWindow.add("You: " + controller.model.getChatBoxInput());
        controller.onEnter();

        var expected = "You: test2";
        var actual = controller.model.chatWindow.get(1);

        assertEquals(expected, actual);

    }
    @Test
    void chatBoxInputSaveToChatBoxWindowListOnSendButton() {
        controller.model.chatBoxInputProperty().setValue(test1);
        controller.sendMessage();
        controller.model.chatBoxInputProperty().setValue(test2);
        controller.sendMessage();

        var expected = "You: test2";
        var actual = controller.model.chatWindow.get(1);

        assertEquals(expected, actual);

    }


}