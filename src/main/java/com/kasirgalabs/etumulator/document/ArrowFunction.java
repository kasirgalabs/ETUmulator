package com.kasirgalabs.etumulator.document;

import java.util.function.IntFunction;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.reactfx.value.Val;

public class ArrowFunction implements IntFunction<Node> {
    private final ObservableValue<Integer> shownLine;

    public ArrowFunction(ObservableValue<Integer> shownLine) {
        this.shownLine = shownLine;
    }

    @Override
    public Node apply(int lineNumber) {
        Polygon triangle = new Polygon(0.0, 0.0, 10.0, 5.0, 0.0, 10.0);
        triangle.setFill(Color.GREEN);
        ObservableValue<Boolean> visible = Val.map(shownLine, sl -> sl == lineNumber);
        triangle.visibleProperty().bind(
                Val.flatMap(triangle.sceneProperty(), scene -> {
                    return scene != null ? visible : Val.constant(false);
                }));
        return triangle;
    }
}
