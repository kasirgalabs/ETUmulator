package com.kasirgalabs.etumulator.navigator;

import com.google.inject.Inject;
import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.util.Observer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class APSRStatus implements Initializable, Observer {
    @FXML
    private Label negative;
    @FXML
    private Label zero;
    @FXML
    private Label carry;
    @FXML
    private Label overflow;
    private final APSR apsr;

    @Inject
    public APSRStatus(APSR apsr) {
        this.apsr = apsr;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apsr.addObserver(this);
    }

    @Override
    public void update(Class<?> clazz, Object arg) {
        int n = apsr.isNegative() ? 1 : 0;
        negative.setText(Integer.toString(n));
        int z = apsr.isZero() ? 1 : 0;
        zero.setText(Integer.toString(z));
        int c = apsr.isCarry() ? 1 : 0;
        carry.setText(Integer.toString(c));
        int v = apsr.isOverflow() ? 1 : 0;
        overflow.setText(Integer.toString(v));
    }
}
