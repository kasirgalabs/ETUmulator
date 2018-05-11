package com.kasirgalabs.etumulator.menu;

import com.google.inject.Inject;
import com.kasirgalabs.etumulator.document.Document;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileMenuController {

    public static int son;
    public final Document document;
    public final FileChooser fileChooser;
    private final List<File> recentFiles = new ArrayList<>();
    public Window window;
    public int lengthStart;
    @FXML
    private Menu openRecentTab;
    private boolean controlRecent;

    @Inject
    public FileMenuController(Document document) {
        this.document = document;
        this.fileChooser = new FileChooser();
        fileChooser.setTitle("ETUmulator");
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    @FXML
    private void newOnAction(ActionEvent event) {
        File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            document.setTargetFile(file);
            if (!checkDuplicate(recentFiles, file)) {
                recentFiles.add(file);
            }
            controlRecent = true;
            document.clear();

        }
    }

    @FXML
    public void openOnAction(ActionEvent event) throws IOException {
        File file = fileChooser.showOpenDialog(window);
        takeFileToText(file);
    }

    @FXML
    public void openBranchExampleOnAction(ActionEvent event) throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource("examples/BranchExample");
        takeFileToText(new File(url.toURI()));

    }

    @FXML
    public void openLoopExampleOnAction(ActionEvent event) throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource("examples/LoopExample");
        takeFileToText(new File(url.toURI()));
    }

    @FXML
    public void openStackExampleOnAction(ActionEvent event) throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource("examples/StackExample");
        takeFileToText(new File(url.toURI()));
    }

    @FXML
    public void saveOnAction(ActionEvent event) throws IOException {
        document.saveDocument();
    }

    @FXML
    private void saveAsOnaction(ActionEvent event) throws IOException {
        File file = fileChooser.showSaveDialog(window);
        if (recentFiles.contains(file)) {
            recentFiles.remove(file);
        }
        if (file == null) {
            return;
        }
        document.setTargetFile(file);
        document.saveDocument();
        if (!checkDuplicate(recentFiles, file)) {
            recentFiles.add(file);
        }
        controlRecent = true;
    }

    @FXML
    private void openRecentFilesOnAction(ActionEvent event) {
        openRecentTab = (Menu) event.getSource();
        openRecentTab.getItems().clear();
        MenuItem menuItem;
        if (controlRecent) {
            for (int i = recentFiles.size() - 1; i >= 0; i--) {
                File file = recentFiles.get(i);
                menuItem = new MenuItem(recentFiles.get(i).getName());
                openRecentTab.getItems().add(menuItem);
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        if (file != null) {
                            StringBuilder text = new StringBuilder(256);
                            try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
                                String line;
                                while ((line = bf.readLine()) != null) {
                                    text.append(line).append('\n');
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            document.setText(text.toString());
                            document.setTargetFile(file);
                        }
                    }
                });
            }
        }
        controlRecent = false;
    }

    public void takeFileToText(File file) {
        if (file != null) {
            StringBuilder text = new StringBuilder(256);
            try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    text.append(line).append('\n');
                }

            } catch (Exception e) {
                System.err.println("FILE NOT FOUND!");
            }

            son = 0;
            document.setText(text.toString());
            document.setTargetFile(file);
            lengthStart = document.getText().length();
            if (!checkDuplicate(recentFiles, file)) {
                recentFiles.add(file);
            }
            controlRecent = true;
            int b = lengthStart;
            setLength(b);
            controlRecent = true;
        }
    }

    public boolean checkDuplicate(List<File> recent, File file) {
        for (int i = 0; i < recent.size(); i++) {
            if (file.getName().equals(recent.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return son;
    }

    public void setLength(int length) {
        this.son = length;
    }
}

