package com.theorygameapplication;


import com.dto.SharingData;
import com.screen.SwitchBetweenScreen;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeScreenController implements Initializable {

    @FXML
    private Button example;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button task;

    @FXML
    private Button theory;

    @FXML
    private Button user;
    @FXML
    private Button UserName;

    @FXML
    void UserName(ActionEvent event) throws IOException {
        String screen = "statistic-user-screen.fxml";
        new SwitchBetweenScreen(main, screen);
    }
    @FXML
    void example(ActionEvent event) throws IOException {
        String screen = "example-list-screen.fxml";
        new SwitchBetweenScreen(main, screen);
    }

    @FXML
    void task(ActionEvent event) throws IOException{
        String screen = "calculator-screen.fxml";
        new SwitchBetweenScreen(main, screen);
    }

    @FXML
    void task_real(ActionEvent event) throws IOException {
        String screen = "task-list-screen.fxml";
        new SwitchBetweenScreen(main, screen);
    }

    @FXML
    void theory(ActionEvent event) throws IOException{
        String screen = "theory-screen.fxml";
        new SwitchBetweenScreen(main, screen);
    }
    @FXML
    void test(ActionEvent event) throws IOException {
        String screen = "test-list-screen.fxml";
        new SwitchBetweenScreen(main, screen);
    }
    @FXML
    void user(ActionEvent event) throws IOException{
        String screen = "user-screen.fxml";
        new SwitchBetweenScreen(main, screen);
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Закрытие программы
    }


    @FXML
    private AnchorPane top;

    @FXML
    void maximize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (stage.isMaximized()) {
            stage.setMaximized(false); // Восстановление окна к исходному размеру
        } else {
            stage.setMaximized(true); // Развертывание окна
        }
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true); // Сворачивание окна
    }
    @FXML
    private  Button addAdmin;

    @FXML
    private  Label addPoint;

    @FXML
    void addAdmin(ActionEvent event) throws IOException{
        String screen = "add-content.fxml";
        new SwitchBetweenScreen(main, screen);
    }

    private double xOffset;

    private double yOffset;

    public   void updateButtonsVisibility() {

        System.out.println("SharingData.isAdmin "+SharingData.getIsAdmin());
        if (SharingData.getIsAdmin()) {
            addAdmin.setVisible(true);
            addAdmin.setDisable(false);
            addPoint.setVisible(true);
            addPoint.setDisable(false);
        } else {
            addAdmin.setVisible(false);
            addAdmin.setDisable(true);
            addPoint.setVisible(false);
            addPoint.setDisable(true);
        }

    }
    private static HomeScreenController instance;
    public static HomeScreenController getInstance() {
        return instance;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        instance = this;
        System.out.println("is admin"+SharingData.getIsAdmin());
        try {
            new SwitchBetweenScreen(main, "user-screen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        top.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        top.setOnMouseDragged(event -> {
            Stage stage = (Stage) top.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        StringProperty userNameProperty = SharingData.userNameProperty();

        UserName.setText("Welcome!");

        UserName.textProperty().bind(userNameProperty);
    }
}
