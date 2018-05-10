package com.kasirgalabs.etumulator.document;

import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.CodeArea;

public class Thumb2CodeArea extends CodeArea {
    public Thumb2CodeArea() {
        getStylesheets().clear();
        getStylesheets().add(Thumb2CodeArea.class.getClassLoader()
                .getResource("styles/thumb2-syntax-highlight.css").toExternalForm());
        getStylesheets().add(Thumb2CodeArea.class.getClassLoader()
                .getResource("styles/spellchecking.css").toExternalForm());
        setCursor(Cursor.TEXT);
        richChanges().filter(ch -> !ch.getInserted().equals(ch.getRemoved()))
                .subscribe(change -> {
                    SyntaxHighlighter syntaxHighlighter = new SyntaxHighlighter();
                    setStyleSpans(0, syntaxHighlighter.highlight(getText()));
                });
        addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            if(e.getCode() == KeyCode.TAB) {
                String s = "    ";
                insertText(getCaretPosition(), s);
                e.consume();
            }
        });
    }
}
