package com.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MatrixGridPaneExample extends Application {
    private static final int MATRIX_SIZE = 5;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
         gridPane.setPadding(new Insets(50));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int row = 0; row < MATRIX_SIZE; row++) {
            for (int col = 0; col < MATRIX_SIZE; col++) {
                Text text = new Text("Cell " + (row + 1) + "-" + (col + 1));
                gridPane.add(text, col, row);
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
