package com.kasirgalabs.etumulator;

import com.google.inject.Inject;
import com.kasirgalabs.etumulator.document.Document;
import com.kasirgalabs.etumulator.lang.Assembler;
import com.kasirgalabs.etumulator.lang.LabelError;
import com.kasirgalabs.etumulator.lang.Linker.ExecutableCode;
import com.kasirgalabs.etumulator.lang.SyntaxError;
import com.kasirgalabs.etumulator.processor.GUISafeProcessor;
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ETUmulatorController {
    @Inject
    private Document document;
    @Inject
    private ProcessorUnits processorUnits;
    @Inject
    private GUISafeProcessor processor;
    @Inject
    private Memory memory;

    @FXML
    private void runButtonOnAction(ActionEvent event){
        processor.stop();
        processorUnits.reset();
        Assembler assembler = new Assembler(memory);
        ExecutableCode executableCode;
        try {
            executableCode = assembler.assemble(document.getText() + "\n");
        } catch(SyntaxError | LabelError | NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        processor.run(executableCode);
    }

    @FXML
    private void stopButtonOnAction(ActionEvent event) {
        processor.stop();
    }
}
