package com.kasirgalabs.etumulator.lang;

import com.kasirgalabs.etumulator.lang.Linker.ExecutableCode;
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.Memory.Size;
import java.util.List;

public class Loader {
    private final Memory memory;

    public Loader(Memory memory) {
        this.memory = memory;
    }

    public void load(ExecutableCode executablecode) {
        List<Data> data = executablecode.getData();
        data.forEach(item -> {
            String value = item.getValue();
            int address = item.getAddress();
            for(int i = 0; i < value.length(); i++) {
                memory.set(address + i, value.charAt(i), Size.BYTE);
            }
        });
    }
}
