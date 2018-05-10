package com.kasirgalabs.etumulator.navigator;

import com.google.inject.Inject;
import com.kasirgalabs.etumulator.processor.LR;
import com.kasirgalabs.etumulator.processor.PC;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.util.Observer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegistersTab implements Initializable, Observer {
    @FXML
    private TableView<NavigatorRow> table;
    @FXML
    private TableColumn<NavigatorRow, String> property;
    @FXML
    private TableColumn<NavigatorRow, String> value;
    private final RegisterFile registerFile;
    private final PC pc;
    private final LR lr;
    private final Navigator navigator;
    private final ObservableList<NavigatorRow> data;

    @Inject
    public RegistersTab(RegisterFile registerFile, PC pc, LR lr, Navigator navigator) {
        this.registerFile = registerFile;
        this.pc = pc;
        this.lr = lr;
        this.navigator = navigator;
        data = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerFile.addObserver(this);
        pc.addObserver(this);
        lr.addObserver(this);
        navigator.addObserver(this);
        property.setCellValueFactory(new PropertyValueFactory<>("property"));
        property.setComparator(new NavigatorRowComparator());
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        for(int i = 0; i < 13; i++) {
            String registerName = "r" + Integer.toString(i);
            data.add(new NavigatorRow(registerName, registerFile.getValue(registerName)));
        }
        data.add(new NavigatorRow("LR", lr.getValue()));
        data.add(new NavigatorRow("PC", pc.getValue()));
        table.setItems(data);
    }

    @Override
    public void update(Class<?> clazz, Object arg) {
        if(clazz.equals(RegisterFile.class)) {
            String registerName = (String) arg;
            for(int i = 0; i < data.size(); i++) {
                NavigatorRow navigatorRow = data.get(i);
                if(navigatorRow.getProperty().equals(registerName)) {
                    navigatorRow.setValue(registerFile.getValue(registerName));
                }
            }
        }
        else if(clazz.equals(PC.class)) {
            NavigatorRow navigatorRow = data.get(data.size() - 1);
            navigatorRow.setValue(pc.getValue());
        }
        else if(clazz.equals(LR.class)) {
            NavigatorRow navigatorRow = data.get(data.size() - 2);
            navigatorRow.setValue(lr.getValue());
        }
        table.refresh();
    }
}
