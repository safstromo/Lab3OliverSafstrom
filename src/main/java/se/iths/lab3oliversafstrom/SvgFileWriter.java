package se.iths.lab3oliversafstrom;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SvgFileWriter {

    FileChooser fileChooser = new FileChooser();

    List<String> svgString = new ArrayList<>();

    public void saveToFile(Model model) {
        createFileChooser();
        Path savePath = fileChooser.showSaveDialog(new Stage()).toPath();

        buildString(model);
        try {
            if (!savePath.endsWith(".svg"))
                Files.write(savePath, svgString);
            else
                Files.write(Path.of(savePath + ".svg"), svgString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFileChooser() {
        fileChooser.setTitle("Location to save file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".svg Files", "*.svg"));
    }

    private void buildString(Model model) {
        svgString.add(svgStart());
        model.shapeList.forEach(shape -> svgString.add(shape.toSVG()));
        svgString.add(svgEnd());
    }

    private static String svgStart() {
        return "<svg width=\"993.0\" height=\"712.0\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">";
    }

    private static String svgEnd() {
        return "</svg>";
    }


}
