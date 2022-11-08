package se.iths.lab3oliversafstrom.stuff;

import javafx.application.Platform;
import se.iths.lab3oliversafstrom.Model;
import se.iths.lab3oliversafstrom.shapes.ShapeFactory;

import java.io.*;
import java.net.Socket;


public class Server {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public void connect(Model model) {

        try {
            socket = new Socket("localhost", 8000);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            var thread = new Thread(() -> {
                try {
                    while (true) {
                        String line = reader.readLine();
                        if (line.endsWith("/>"))
                            Platform.runLater(() -> model.importSvgString(line));
                        //else
                            Platform.runLater(() -> model.chatWindow.add(line));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.setDaemon(true);
            thread.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String string) {
        writer.println(string);
    }
}


