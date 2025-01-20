package com.test;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class GameSolver extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Line Chart Example");

        // Create axes for the chart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        // Create a LineChart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Line Chart Example");

        // Set the background color of the chart to black
        lineChart.setStyle("-fx-background-color: black;");

        // Create some sample data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Data");
        series.getData().add(new XYChart.Data<>(1, 3));
        series.getData().add(new XYChart.Data<>(2, 4));
        series.getData().add(new XYChart.Data<>(3, 8));
        series.getData().add(new XYChart.Data<>(4, 5));
        series.getData().add(new XYChart.Data<>(5, 12));

        lineChart.getXAxis().setStyle("-fx-border-color: OrangeRed transparent transparent; -fx-border-width:3");
        lineChart.getYAxis().setStyle("-fx-border-color: transparent OrangeRed transparent transparent; -fx-border-width:3");
        //Creating a Group object

        // Add the series to the chart
        lineChart.getData().add(series);

        // Create a scene and add the chart to it
        Scene scene = new Scene(lineChart, 400, 300);

        // Set the scene to the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

