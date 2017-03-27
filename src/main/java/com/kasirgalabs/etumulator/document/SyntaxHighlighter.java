package com.kasirgalabs.etumulator.document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Parent;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

public class SyntaxHighlighter {
    private static final String[] KEYWORDS = new String[]{
        "add", "adds", "adc", "adcs", "sub", "subs", "sbc", "sbcs",
        "rsb", "rsbs", "rsc", "rscs", "mul", "muls", "mla", "mlas",
        "mls", "sdiv", "udiv", "mov", "movs", "mvn", "mvns", "movt",
        "asr", "asrs", "lsl", "lsls", "lsr", "lsrs", "ror", "rors",
        "rrx", "rrxs", "cmp", "cmn", "tst", "teq", "and", "ands",
        "eor", "eors", "orr", "orrs", "orn", "orns", "bic", "bics",
        "b", "beq", "bne", "bcs", "bhs", "bcc", "blo", "bmi",
        "bpl", "bvs", "bvc", "bhi", "bls", "bge", "blt", "bgt",
        "ble", "bal", "bl", "ldr", "ldrb", "ldrsb", "ldrh", "ldrsh",
        "str", "strb", "strsb", "strh", "strsh", "push", "pop", "asciz"
    };
    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
    private static final String LABEL_PATTERN = "[a-zA-Z0-9_]+\\s*:";
    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
            + "|(?<STRING>" + STRING_PATTERN + ")"
            + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
            + "|(?<LABEL>" + LABEL_PATTERN + ")"
    );
    private static final Set<String> DICTIONARY = new HashSet<String>();
    private final Parent parent;

    public SyntaxHighlighter(Parent parent) {
        this.parent = parent;
        parent.getStylesheets().add(SyntaxHighlighter.class.getClassLoader()
                .getResource("styles/arm-syntax-highlight.css").toExternalForm());
        parent.getStylesheets().add(SyntaxHighlighter.class.getClassLoader()
                .getResource("styles/spellchecking.css").toExternalForm());
        try(InputStream input = SyntaxHighlighter.class.getClassLoader()
                .getResourceAsStream("other/spellchecking.dict");
                BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while((line = br.readLine()) != null) {
                DICTIONARY.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public StyleSpans<Collection<String>> highlightSyntax(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKeywordEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass
                    = matcher.group("KEYWORD") != null ? "keyword"
                    : matcher.group("STRING") != null ? "string"
                    : matcher.group("COMMENT") != null ? "comment"
                    : matcher.group("LABEL") != null ? "label"
                    : null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKeywordEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKeywordEnd = matcher.end();
        }
        if(lastKeywordEnd == 0) {
            return highlightMisspelled(text);
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKeywordEnd);
        return spansBuilder.create();
    }

    private StyleSpans<Collection<String>> highlightMisspelled(String text) {
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(text);
        int lastIndex = wb.first();
        int lastKwEnd = 0;
        while(lastIndex != BreakIterator.DONE) {
            int firstIndex = lastIndex;
            lastIndex = wb.next();
            if(lastIndex != BreakIterator.DONE
                    && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                String word = text.substring(firstIndex, lastIndex).toLowerCase();
                if(!DICTIONARY.contains(word)) {
                    spansBuilder.add(Collections.emptyList(), firstIndex - lastKwEnd);
                    spansBuilder.add(Collections.singleton("underlined"), lastIndex - firstIndex);
                    lastKwEnd = lastIndex;
                }
            }
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}
