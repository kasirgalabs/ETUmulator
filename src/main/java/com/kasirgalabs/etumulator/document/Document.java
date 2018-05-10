package com.kasirgalabs.etumulator.document;

import java.io.File;
import java.io.IOException;

public interface Document {
    void setText(String text);

    String getText();

    void clear();

    void setTargetFile(File targetFile);

    File getTargetFile();

    void saveDocument() throws IOException;
}
