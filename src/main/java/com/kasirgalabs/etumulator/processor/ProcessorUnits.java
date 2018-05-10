package com.kasirgalabs.etumulator.processor;

public interface ProcessorUnits {
    void setRegisterFile(RegisterFile registerFile);

    RegisterFile getRegisterFile();

    void setAPSR(APSR apsr);

    APSR getAPSR();

    void setStack(Stack stack);

    Stack getStack();

    void setMemory(Memory memory);

    Memory getMemory();

    void setUART(UART uart);

    UART getUART();

    void setPC(PC pc);

    PC getPC();

    void setLR(LR lr);

    LR getLR();

    void reset();
}
