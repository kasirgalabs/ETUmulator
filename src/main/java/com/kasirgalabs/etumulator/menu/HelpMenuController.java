package com.kasirgalabs.etumulator.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpMenuController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void aboutOnAction(ActionEvent event) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/About.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("About ETUmulator");
        stage.setScene(scene);
        stage.show();
    }
}
