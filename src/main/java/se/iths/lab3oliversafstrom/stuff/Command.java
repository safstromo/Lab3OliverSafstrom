package se.iths.lab3oliversafstrom.stuff;

import se.iths.lab3oliversafstrom.shapes.Shape;

public class Command {
    Shape shape;
    ObjectCopy objectCopy;


    public void execute(Shape shapeToCopy) {
        objectCopy.setObjectCopy(shapeToCopy);
    }

    public void redo() {

    }

    public void undo() {

    }


}

