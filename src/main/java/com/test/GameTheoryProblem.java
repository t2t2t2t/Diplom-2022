package com.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameTheoryProblem extends Application {
    private int correctValue = 42; // Правильное значение

    @Override
    public void start(Stage primaryStage) {
        // Создание элементов интерфейса
        Label instructionLabel = new Label("Введите значение:");
        TextField valueTextField = new TextField();
        Button checkButton = new Button("Проверить");
        Label resultLabel = new Label();

        // Обработчик события при нажатии кнопки "Проверить"
        checkButton.setOnAction(e -> {
            try {
                int userInput = Integer.parseInt(valueTextField.getText());
                if (userInput == correctValue) {
                    resultLabel.setText("Верно! Значение " + userInput + " правильное.");
                } else {
                    resultLabel.setText("Неверно! Значение " + userInput + " неправильное.");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Ошибка! Введите целое число.");
            }
        });

        // Создание контейнера VBox и добавление элементов интерфейса
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(instructionLabel, valueTextField, checkButton, resultLabel);

        // Создание сцены и отображение приложения
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Проверка значения");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

