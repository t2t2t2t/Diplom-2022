package com.algorithms;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;

public class GraphicMethod {

    public static StringBuilder getText(double[][] matrix) {
       /* double x1 = 0;
        double y1 = matrix[0][0];
        double x2 = 1;
        double y2 = matrix[1][0];
        double x3 = 0;
        double y3 =  matrix[0][1];
        double x4 = 1;
        double y4 = matrix[1][1];

        double xNumerator = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        double yNumerator = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        double x = xNumerator / denominator;
        double y = yNumerator / denominator;

        if (denominator == 0) {
            x = 0;
            y = 0;
        }*/


        double p1 = (matrix[1][1] - matrix[1][0]) / (matrix[0][0] + matrix[1][1] - matrix[0][1] - matrix[1][0]);
        double p2 = (matrix[0][0] - matrix[0][1]) / (matrix[0][0] + matrix[1][1] - matrix[0][1] - matrix[1][0]);
        double y = matrix[0][0] * p1 + matrix[0][1] * (1 - p1);
        double a = matrix[0][0];
        double b = matrix[0][1];
        double c = matrix[1][0];
        double d = matrix[1][1];
        double q1 = (d - b) / (a - c - b + d);
        double q2 = 1 - q1;

        DecimalFormat decimalFormat = new DecimalFormat("#.000");

        StringBuilder text = new StringBuilder();
        text.append("Решим задачу геометрическим методом, который включает в себя следующие этапы:  \n" +
                "1. В декартовой системе координат по оси абсцисс откладывается отрезок, длина которого равна 1. Левый конец отрезка (точка х = 0) соответствует стратегии A1, правый - стратегии A2 (x = 1). Промежуточные точки х соответствуют вероятностям некоторых смешанных стратегий S1 = (p1,p2).  \n" +
                "2. На левой оси ординат откладываются выигрыши стратегии A1. На линии, параллельной оси ординат, из точки 1 откладываются выигрыши стратегии A2.");
        text.append("y = ").append(matrix[0][0]).append(" + (").append(matrix[1][0]).append(" - ").append(matrix[0][0]).append(")p2  \n");
        text.append("y = ").append(matrix[0][1]).append(" + (").append(matrix[1][1]).append(" - ").append(matrix[0][1]).append(")p2  \n");
        text.append("p1 = ").append(decimalFormat.format(p1)).append("  \n");
        text.append("p2 = ").append(decimalFormat.format(p2)).append("  \n");
        text.append("Цена игры, y = ").append(decimalFormat.format(y)).append("  \n");
        text.append("Стратегия игрока B:  \n");
        text.append(matrix[0][0]).append("*q1 + ").append(matrix[0][1]).append("*q2 = y  \n");
        text.append(matrix[1][0]).append("*q1 + ").append(matrix[1][1]).append("*q2 = y  \n");
        text.append("q1 + q2 = 1  \n");
        text.append("или  \n");
        text.append(matrix[0][0]).append("*q1 + ").append(matrix[0][1]).append("*q2 = ").append(decimalFormat.format(y)).append("  \n");
        text.append(matrix[1][0]).append("*q1 + ").append(matrix[1][1]).append("*q2 = ").append(decimalFormat.format(y)).append("  \n");
        text.append("q1 + q2 = 1  \n");
        text.append("q1 = ").append(decimalFormat.format(q1)).append("  \n");
        text.append("q2 = ").append(decimalFormat.format(q2)).append("  \n");


        return text;
    }



    public static LineChart getGraph(double [][]matrix){
        // Создание осей графика
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("");
        yAxis.setLabel("");

        // Создание графика
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("График ");

        // Создание серии данных для стратегий игрока B
        XYChart.Series<Number, Number> seriesB1 = new XYChart.Series<>();
        seriesB1.setName("Игрок B1");
        seriesB1.getData().add(new XYChart.Data<>(0, matrix[0][0]));
        seriesB1.getData().add(new XYChart.Data<>(1, matrix[1][0]));

        XYChart.Series<Number, Number> seriesB2 = new XYChart.Series<>();
        seriesB2.setName("Игрок B2");
        seriesB2.getData().add(new XYChart.Data<>(0, matrix[0][1]));
        seriesB2.getData().add(new XYChart.Data<>(1, matrix[1][1]));

        // Добавление серий данных на график
        lineChart.getData().addAll(seriesB1, seriesB2);

        double x1 = 0;
        double y1 = matrix[0][0];
        double x2 = 1;
        double y2 = matrix[1][0];
        double x3 = 0;
        double y3 =  matrix[0][1];
        double x4 = 1;
        double y4 = matrix[1][1];

        double xNumerator = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        double yNumerator = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        double x = xNumerator / denominator;
        double y = yNumerator / denominator;

        if (denominator == 0) {
             x = 0;
             y = 0;
        }


        XYChart.Series<Number, Number> intersectionPointSeries = new XYChart.Series<>();
        intersectionPointSeries.setName("Точка пересечения ");

        intersectionPointSeries.getData().add(new XYChart.Data<>(x, y));
        intersectionPointSeries.getData().add(new XYChart.Data<>(x, 0));
        lineChart.getData().add(intersectionPointSeries);
        lineChart.setMaxWidth(600);
        lineChart.setPrefHeight(300);
        lineChart.setBackground(new Background(new BackgroundFill(Color.web( "#0c192a"), null, null)));


        return lineChart;
    }

}
