/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
