package com.theorygameapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class AlgorithmScreenController {

    @FXML
    private Label witchAlgoritm;

    public void displayNameAlgorithm(String algorithm){
        witchAlgoritm.setText(algorithm);
    }

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label algorithm;

    @FXML
    private TextField numColom;

    @FXML
    private TextField numRow;

    @FXML
    private TextField[][] matrixFields;

    @FXML
    private Button continueInput;


    @FXML
    private GridPane grid;

    @FXML
    void parse(ActionEvent event) {
       /* double[][] matrix= getMatrix() ;
        printMatrix(matrix);

        MinMaxFinder.minMaxFinder(matrix);
        System.out.println("----");
        printMatrix(removeDominantStrategies.removeDominantStrategies(matrix));
        String screen="hello-view.fxml";
        System.out.println(screen);
        OpenScreen obj=new OpenScreen();
        Pane view=obj.getPage(screen);
        solution.getChildren().clear();
        solution.setCenter(view);*/


    }

    @FXML
    private AnchorPane Pane;

    @FXML
    private BorderPane solution;

    @FXML
    void solution(ActionEvent event) {

    }

    private void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    @FXML
    void continueInput(ActionEvent event) {
        int rows = Integer.parseInt(numRow.getText());
        int cols = Integer.parseInt(numColom.getText());

        matrixFields = new TextField[rows][cols];

        grid.getChildren().clear();

        GridPane matrixPane = grid;
        matrixPane.setAlignment(Pos.CENTER);
        matrixPane.setHgap(10);
        matrixPane.setVgap(10);
        matrixPane.setPadding(new Insets(25, 25, 25, 25));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TextField textField = new TextField();
                matrixFields[i][j] = textField;
                matrixPane.add(textField, j, i);
            }
        }
    }

    public double[][] getMatrix() {

        double[][] matrix = new double[matrixFields.length][matrixFields[0].length];

        for (int i = 0; i < matrixFields.length; i++) {
            for (int j = 0; j < matrixFields[i].length; j++) {
                String value = matrixFields[i][j].getText();
                matrix[i][j] = Double.parseDouble(value);
            }
        }
        return matrix;
    }
}
