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
import javafx.scene.control.MenuItem;
import java.util.List;
import java.util.ArrayList;
import javafx.event.EventHandler;
import java.io.*;


public class FileMenuController {

    public final Document document;
    public final FileChooser fileChooser;
    public Window window;
    public int lengthStart;
    public static int son; //for start length
    private final List<File> recentFiles=new ArrayList<>();
    @FXML private Menu openRecentTab;
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
        if(file != null) {
            document.setTargetFile(file);
            if (!checkDuplicate(recentFiles,file)){
                recentFiles.add(file);
            }
            controlRecent=true;
            document.clear();

        }
    }

    @FXML
    public void openOnAction(ActionEvent event) throws IOException {
        File file = fileChooser.showOpenDialog(window);
        if(file != null) {
            StringBuilder text = new StringBuilder(256);
            try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = bf.readLine()) != null) {
                    text.append(line).append('\n');
                }

            }


            son=0;
            document.setText(text.toString());
            document.setTargetFile(file);
            lengthStart=document.getText().length();
            if (!checkDuplicate(recentFiles,file)){
                recentFiles.add(file);
            }
            controlRecent=true;
            int b=lengthStart;
            setLength(b);
            controlRecent=true;
        }
    }

    @FXML
    public void saveOnAction(ActionEvent event) throws IOException {
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
        if (!checkDuplicate(recentFiles,file)){
            recentFiles.add(file);
        }
        controlRecent=true;
    }
    @FXML
    private void openRecentFilesOnAction(ActionEvent event){
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
                            catch(Exception e){ System.out.println(e.getMessage());}
                            document.setText(text.toString());
                            document.setTargetFile(file);
                        }
                    }
                });
            }
        }
        controlRecent = false;
    }
    public boolean checkDuplicate(List<File> recent,File file){
        for (int i=0;i<recent.size();i++){
            if (file.getName().equals(recent.get(i).getName())){
                return true;
            }
        }
        return false;
    }
    public int getLength(){
        return son;
    }
    public void setLength(int length){
      this.son=length;
    }
}
