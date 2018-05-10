package com.kasirgalabs.etumulator.document;

import com.google.inject.Singleton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.IntFunction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

@Singleton
public class BaseDocument implements Initializable, Document {
    private static final String DEFAULT_NAME = "untitled";
    @FXML
    private Label label;
    @FXML
    private StackPane stackPane;
    private File targetFile;
    private final CodeArea document;

    public BaseDocument() {
        document = new Thumb2CodeArea();
        IntFunction<Node> numberFunction = LineNumberFunction.applyTo(document);
        IntFunction<Node> arrowFunction = new ArrowFunction(document.currentParagraphProperty());
        IntFunction<Node> graphicFactory = line -> {
            HBox hbox = new HBox(numberFunction.apply(line), arrowFunction.apply(line));
            hbox.setCursor(Cursor.DEFAULT);
            hbox.setAlignment(Pos.CENTER_LEFT);
            return hbox;
        };
        document.setParagraphGraphicFactory(graphicFactory);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stackPane.getChildren().add(new VirtualizedScrollPane<>(document));
        targetFile = new File(DEFAULT_NAME);
        label.setText(DEFAULT_NAME);
    }

    @Override
    public String getText() {
        return document.getText();
    }

    @Override
    public void setText(String text) {
        document.clear();
        document.appendText(text);
    }

    @Override
    public void setTargetFile(File targetFile) {
        
        label.setText(targetFile.getName());
        this.targetFile = targetFile;
    }

    @Override
    public File getTargetFile() {
        return targetFile;
    }

    @Override
    public void saveDocument() throws IOException {
        String text = document.getText();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
            bw.write(text);
        }
    }

    @Override
    public void clear() {
        document.clear();
    }
}
