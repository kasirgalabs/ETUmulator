package com.kasirgalabs.etumulator.navigator;

import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.Observable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

@Singleton
public class Navigator extends BaseDispatcher implements Initializable, Observable {
    @FXML
    private ComboBox<String> valueTypeComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueTypeComboBox.setItems(FXCollections.observableArrayList(
                new String[]{"Decimal", "HEX", "ASCII", "Binary"}));
        valueTypeComboBox.getSelectionModel().select(0);
    }

    @FXML
    private void valueTypeOnAction(ActionEvent event) {
        int type = valueTypeComboBox.getSelectionModel().getSelectedIndex();
        NavigatorRow.setType(NavigatorRow.Type.values()[type]);
        notifyObservers(Navigator.class);
    }
}
