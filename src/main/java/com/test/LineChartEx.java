package com.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LineChartEx extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Создание корневого контейнера
        VBox root = new VBox();

        // Создание меню с предупреждением
        MenuItem warningItem = new MenuItem("Предупреждение");
        warningItem.setOnAction(event -> {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Произошла ошибка");
            alert.setContentText("Что-то пошло не так!");
            alert.showAndWait();
        });

        // Создание меню и добавление пункта меню
        Menu menu = new Menu("Меню");
        menu.getItems().add(warningItem);

        // Создание панели меню и добавление меню
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        // Добавление панели меню в корневой контейнер
        root.getChildren().add(menuBar);

        // Создание сцены
        Scene scene = new Scene(root, 400, 300);

        // Установка сцены для primaryStage
        primaryStage.setScene(scene);

        // Отображение primaryStage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}