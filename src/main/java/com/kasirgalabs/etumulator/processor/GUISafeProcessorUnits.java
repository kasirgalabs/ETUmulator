package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.GUISafeDispatcher;

@Singleton
public class GUISafeProcessorUnits extends BaseProcessorUnits {
    public GUISafeProcessorUnits() {
        super(new RegisterFile(new GUISafeDispatcher()),
                new APSR(new GUISafeDispatcher()),
                new Stack(new GUISafeDispatcher()),
                new Memory(new GUISafeDispatcher()),
                new UART(null, new GUISafeDispatcher()),
                new PC(new GUISafeDispatcher()),
                new LR(new GUISafeDispatcher()));
        super.getUART().setRegisterFile(super.getRegisterFile());
    }

    @Inject
    public GUISafeProcessorUnits(RegisterFile registerFile, APSR apsr, Stack stack, Memory memory,
            UART uart, PC pc, LR lr) {
        super(registerFile, apsr, stack, memory, uart, pc, lr);
    }
}
