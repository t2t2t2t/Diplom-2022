package com.dto;

import javafx.scene.chart.LineChart;

public class SharingText {

    public static LineChart getGraph() {
        return graph;
    }

    public static void setGraph(LineChart graph) {
        SharingText.graph = graph;
    }

    public static LineChart<Number, Number> graph;

    public static StringBuilder getText() {
        return text;
    }

    public static void setText(StringBuilder newText) {
        text = newText;
    }

    private static StringBuilder text=new StringBuilder("no text");

}
