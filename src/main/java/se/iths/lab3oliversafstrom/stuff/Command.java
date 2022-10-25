package se.iths.lab3oliversafstrom.stuff;

import se.iths.lab3oliversafstrom.shapes.Shape;

public class Command {
    Shape shape;
    ShapeCopy shapeCopy;


    public void execute(Shape shapeToCopy) {
        shapeCopy.setObjectCopy(shapeToCopy);
    }

    public void redo() {

    }

    public void undo() {

    }


}

