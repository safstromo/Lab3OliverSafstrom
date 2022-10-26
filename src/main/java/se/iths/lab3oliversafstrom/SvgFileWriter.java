package se.iths.lab3oliversafstrom;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.lab3oliversafstrom.shapes.Shape;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SvgFileWriter {

    private final String start = "<svg width=\"993.0\" height=\"712.0\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\"";
    private final String end = "</svg>";
    FileChooser fileChooser = new FileChooser();
    List<String> svgString = new ArrayList<>();

    public void saveToFile(Model model) {
        createFileChooser();
        File savePath = fileChooser.showSaveDialog(new Stage());
        
        buildString(model);
        try {
            Files.write(savePath.toPath(), svgString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createFileChooser() {
        fileChooser.setTitle("Location to save file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".svg Files", "*.svg"));
    }

    private void buildString(Model model) {
        svgString.add(start);
        model.shapeList.forEach(shape -> svgString.add(shape.toSVG()));
        svgString.add(end);

    }

    private String addShapes(Shape shape) {
        return shape.toSVG();
    }


}
