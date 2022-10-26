package se.iths.lab3oliversafstrom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void getChatBoxInput() {
        Model model = new Model();
        model.chatBoxInputProperty();

        var expectedString = "Test";
        var actualString = model.chatBoxInputProperty();

        assertEquals(expectedString, actualString);
    }

    @Test
    void setChatBoxInput() {
    }

    @Test
    void getMouseX() {
    }

    @Test
    void setMouseX() {
    }

    @Test
    void getMouseY() {
    }

    @Test
    void setMouseY() {
    }
}