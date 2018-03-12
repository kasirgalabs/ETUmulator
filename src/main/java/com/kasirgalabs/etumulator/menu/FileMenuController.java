/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.menu;

import com.google.inject.Inject;
import com.kasirgalabs.etumulator.document.Document;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import java.util.ArrayList;
import javafx.event.EventHandler;
import java.io.*;
import javafx.event.ActionEvent;

public class FileMenuController {
    private final Document document;
    private final FileChooser fileChooser;
    private Window window;
    private ArrayList<File> recentFiles=new ArrayList<File>();
    @FXML private Menu openRecentTab;
    private boolean controlRecent=false;
    private ActionEvent op;


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
        if(file != null) {
            document.setTargetFile(file);
            recentFiles.add(file);
            controlRecent=true;
            document.clear();
            try{ openRecentFilesOnAction(op); } catch(NullPointerException e){}
        }
    }

    @FXML
    private void openOnAction(ActionEvent event) throws IOException {
        File file = fileChooser.showOpenDialog(window);
        if(file != null) {
            StringBuilder text = new StringBuilder(256);
            try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = bf.readLine()) != null) {
                    text.append(line).append('\n');
                }
            }
            catch(Exception e){}
            document.setText(text.toString());
            document.setTargetFile(file);
            recentFiles.add(file);
            controlRecent=true;
            try{ openRecentFilesOnAction(op); } catch(NullPointerException e){}
        }
    }

    @FXML
    private void saveOnAction(ActionEvent event) throws IOException {
        document.saveDocument();
    }

    @FXML
    private void saveAsOnaction(ActionEvent event) throws IOException {
        File file = fileChooser.showSaveDialog(window);
        if (recentFiles.contains(file)){
            recentFiles.remove(file);
        }
        if(file == null) {
            return;
        }
        document.setTargetFile(file);
        document.saveDocument();
        recentFiles.add(file);
        controlRecent=true;
        try{ openRecentFilesOnAction(op); } catch(NullPointerException e){}
    }
    @FXML
    private void openRecentFilesOnAction(ActionEvent event){
    	op = event;
        openRecentTab = (Menu)event.getSource();
        openRecentTab.getItems().clear();
        MenuItem menuItem;
        if (controlRecent){
            for (int i=recentFiles.size()-1;i>=0;i--){
                File file = recentFiles.get(i);
                menuItem = new MenuItem(recentFiles.get(i).getName());
                openRecentTab.getItems().add(menuItem);
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        if(file != null) {
                            StringBuilder text = new StringBuilder(256);
                            try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
                                String line;
                                while((line = bf.readLine()) != null) {
                                    text.append(line).append('\n');
                                }
                            }
                            catch(Exception e){ }
                            document.setText(text.toString());
                            document.setTargetFile(file);
                        }
                    }
                });
            }
        }
        controlRecent = false;
    }
}
