package com.kasirgalabs.etumulator.document;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        "ble", "bal", "bl", "ldr", "ldrb", "ldrh", "str", "strb",
        "strh", "push", "pop", "asciz", "bfc"
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
    private final Set<String> dictionary;

    public SyntaxHighlighter() {
        dictionary = new HashSet<>(KEYWORDS.length);
        dictionary.addAll(Arrays.asList(KEYWORDS));
    }

    public StyleSpans<Collection<String>> highlight(String text) {
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
        int lastKeywordEnd = 0;
        while(lastIndex != BreakIterator.DONE) {
            int firstIndex = lastIndex;
            lastIndex = wb.next();
            if(lastIndex != BreakIterator.DONE
                    && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                String word = text.substring(firstIndex, lastIndex).toLowerCase();
                if(!dictionary.contains(word)) {
                    spansBuilder.add(Collections.emptyList(), firstIndex - lastKeywordEnd);
                    spansBuilder.add(Collections.singleton("underlined"), lastIndex - firstIndex);
                    lastKeywordEnd = lastIndex;
                }
            }
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKeywordEnd);
        return spansBuilder.create();
    }
}
