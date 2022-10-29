module se.iths.lab3oliversafstrom {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.lab3oliversafstrom to javafx.fxml;
    exports se.iths.lab3oliversafstrom;
    exports se.iths.lab3oliversafstrom.stuff;
    opens se.iths.lab3oliversafstrom.stuff to javafx.fxml;
}