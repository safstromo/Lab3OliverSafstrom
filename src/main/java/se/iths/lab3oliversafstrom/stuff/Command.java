package se.iths.lab3oliversafstrom.stuff;

import se.iths.lab3oliversafstrom.shapes.Shape;

public abstract class Command {
    Shape shape;
    ObjectCopy objectCopy;


    public abstract void execute();

    public abstract void redo();

    public abstract void undo();


}

