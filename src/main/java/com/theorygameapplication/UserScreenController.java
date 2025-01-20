package com.theorygameapplication;


import com.database.DBConnect;
import com.dto.SharingData;
import com.dto.SharingUser;
import com.test.TableViewExample;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;


public class UserScreenController implements Initializable {



    @FXML
    void singup(ActionEvent event) {

        DBConnect dbConnect = new DBConnect();
        dbConnect.getConnection();


        if (dbConnect.insertData(userNameTextFlow.getText(), PasswordTextFlow.getText())==-1){
            showAlert("Ошибка","Это имя уже существует в системе","Пожалуйста, придумайте новое имя");
        }else{
            showAlertUser("Успешный вход", "Вход выполнен", "Вы успешно вошли в систему.");
            SharingUser.setUserId(dbConnect.getUserIdByUsername(userNameTextFlow.getText()));
            System.out.println(SharingUser.getUserId());
            SharingData.setUserName(userNameTextFlow.getText());
            System.out.println("userNameTextFlow.getText()"+userNameTextFlow.getText());

        };

    }
    @FXML
    private MFXPasswordField PasswordTextFlow;

    @FXML
    private MFXTextField userNameTextFlow;



    @FXML
    void login(ActionEvent event) {

        DBConnect dbConnect = new DBConnect();
        dbConnect.getConnection();

        if(dbConnect.authenticateUser(userNameTextFlow.getText(),PasswordTextFlow.getText())){
            SharingUser.setUserId(dbConnect.getUserIdByUsername(userNameTextFlow.getText()));
            System.out.println(SharingUser.getUserId());
            SharingData.setUserName(userNameTextFlow.getText());
           // showAlertUser("Успешный вход", "Вход выполнен", "Вы успешно вошли в систему.");
            System.out.println(userNameTextFlow.getText());
            System.out.println(userNameTextFlow.getText()=="admin");
            if(userNameTextFlow.getText().equals("admin")){
                System.out.println("SharingData.isAdmin=true;");
                SharingData.setIsAdmin(true);
                HomeScreenController.getInstance().updateButtonsVisibility();
            }else{
                SharingData.setIsAdmin(false);
                HomeScreenController.getInstance().updateButtonsVisibility();
            }

        }


    }
    private void showAlertUser(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private static HomeScreenController homeScreenController;

    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
