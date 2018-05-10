package com.kasirgalabs.etumulator;

import com.google.inject.AbstractModule;
import com.kasirgalabs.etumulator.console.BaseConsole;
import com.kasirgalabs.etumulator.console.Console;
import com.kasirgalabs.etumulator.document.BaseDocument;
import com.kasirgalabs.etumulator.document.Document;
import com.kasirgalabs.etumulator.processor.GUISafeProcessor;
import com.kasirgalabs.etumulator.processor.GUISafeProcessorUnits;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import com.kasirgalabs.etumulator.util.Dispatcher;
import com.kasirgalabs.etumulator.util.GUISafeDispatcher;

public class ETUmulatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Processor.class).to(GUISafeProcessor.class);
        bind(ProcessorUnits.class).to(GUISafeProcessorUnits.class);
        bind(Document.class).to(BaseDocument.class);
        bind(Console.class).to(BaseConsole.class);
        bind(Dispatcher.class).to(GUISafeDispatcher.class);
    }
}
