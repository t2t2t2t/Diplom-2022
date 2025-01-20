package com.theorygameapplication;

import com.datastore.ExampleArrayList;
import com.datastore.LectureArrayList;
import com.datastore.TaskArrayList;
import com.datastore.TestArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ExampleArrayList.generationExampleList();
        System.out.println(ExampleArrayList.getExampleList().get(0).getName());
        LectureArrayList.generationLectureList();
        TestArrayList.generationTestList();
        TaskArrayList.generationLectureList();
        System.out.println("name+ "+TaskArrayList.getTaskList().get(3).getName()+"id "+TaskArrayList.getTaskList().get(3).getId());
        String table = "TableViewExample.fxml";;
        String home = "home-screen.fxml";
        String web = "web.fxml";
        String user = "user-screen.fxml";
        String theory = "theory-screen.fxml";
        String screen = "topic-screen.fxml";
        String example = "example-screen.fxml";
        String task = "calculator-screen.fxml";
        String adsada = "calculator-solution.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(home));


        System.out.println(getClass().getResource(adsada));
        Parent root = loader.load();

        // Создание объекта Image с новой иконкой
   //     Image newIcon = new Image("img.png");

        // Установка новой иконки для объекта Stage
     //   stage.getIcons().add(newIcon);
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED); // Remove window decorations
        stage.setScene(scene);
        stage.setResizable(true); // Разрешить изменение размера окна
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}