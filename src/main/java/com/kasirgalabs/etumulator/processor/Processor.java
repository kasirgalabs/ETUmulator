package com.kasirgalabs.etumulator.processor;

import com.kasirgalabs.etumulator.lang.Linker.ExecutableCode;

public interface Processor {
    void run(ExecutableCode executableCode);
}
