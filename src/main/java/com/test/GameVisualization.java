package com.test;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GameVisualization extends Application {




    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private double[][] matrix = {{1, 14}, {13, 2}};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Visualization");

        // Создание осей графика
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("p1");
        yAxis.setLabel("Payoff");

        // Создание графика
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Game Payoff");

        // Создание серии данных для стратегий игрока B
        XYChart.Series<Number, Number> seriesB1 = new XYChart.Series<>();
        seriesB1.setName("Player B1");
        seriesB1.getData().add(new XYChart.Data<>(0, matrix[0][0]));
        seriesB1.getData().add(new XYChart.Data<>(1, matrix[1][0]));

        XYChart.Series<Number, Number> seriesB2 = new XYChart.Series<>();
        seriesB2.setName("Player B2");
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


        // Создание серии данных для точки пересечения
        XYChart.Series<Number, Number> intersectionPointSeries = new XYChart.Series<>();
        intersectionPointSeries.setName("Intersection Point");

        intersectionPointSeries.getData().add(new XYChart.Data<>(x, y));
        intersectionPointSeries.getData().add(new XYChart.Data<>(x, 0));
        // Добавление серии данных для точки пересечения на график
        lineChart.getData().add(intersectionPointSeries);
        lineChart.setBackground(new Background(new BackgroundFill(Color.web("#1a2b3b"), null, null)));

        // Создание сцены и добавление графика на нее
        Scene scene = new Scene(lineChart, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Нахождение цены игры
        double gameValue = calculateGameValue(matrix);


        System.out.println("Цена игры: " + gameValue);

        // Решение полученной игры 2x2
        solve2x2Game(matrix, gameValue);
    }

    private double calculateGameValue(double[][] matrix) {
        double maxValue = Double.NEGATIVE_INFINITY;
        for (double[] row : matrix) {
            for (double value : row) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
        }
        return maxValue;
    }

    private void solve2x2Game(double[][] matrix, double gameValue) {
        double p1 = (matrix[1][1] - matrix[1][0]) / (matrix[0][0] - matrix[1][0] + matrix[1][1] - matrix[0][1]);
        double p2 = (matrix[1][1] - matrix[0][1]) / (matrix[0][0] - matrix[1][0] + matrix[1][1] - matrix[0][1]);

        double q1 = (gameValue - matrix[1][1]) / (matrix[0][1] - matrix[1][1]);
        double q2 = (gameValue - matrix[1][1]) / (matrix[0][0] - matrix[1][1]);

        System.out.println("Решение игры 2x2:");
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("q1 = " + q1);
        System.out.println("q2 = " + q2);
    }
}
