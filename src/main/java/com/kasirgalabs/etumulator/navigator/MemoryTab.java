package com.kasirgalabs.etumulator.navigator;

import com.google.inject.Inject;
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.Memory.Size;
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

public class MemoryTab implements Initializable, Observer {
    @FXML
    private TableView<NavigatorRow> table;
    @FXML
    private TableColumn<NavigatorRow, String> property;
    @FXML
    private TableColumn<NavigatorRow, String> value;
    private final Memory memory;
    private final Navigator navigator;
    private final ObservableList<NavigatorRow> data;

    @Inject
    public MemoryTab(Memory memory, Navigator navigator) {
        this.memory = memory;
        this.navigator = navigator;
        data = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memory.addObserver(this);
        navigator.addObserver(this);
        property.setCellValueFactory(new PropertyValueFactory<>("property"));
        property.setComparator(new NavigatorRowComparator());
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        table.setItems(data);
    }

    @Override
    public void update(Class<?> clazz, Object arg) {
        if(clazz.equals(Memory.class)) {
            if(arg != null) {
                int address = (int) arg;
                boolean dataNotContainsAddress = true;
                for(int i = 0; i < data.size(); i++) {
                    NavigatorRow navigatorRow = data.get(i);
                    if(navigatorRow.getProperty().equals(Integer.toString(address))) {
                        navigatorRow.setValue(memory.get(address, Size.BYTE));
                        dataNotContainsAddress = false;
                        break;
                    }
                }
                if(dataNotContainsAddress) {
                    data.add(new NavigatorRow(address, memory.get(address, Size.BYTE)));
                }
            }
            else {
                data.clear();
            }
        }
        table.refresh();
    }
}
