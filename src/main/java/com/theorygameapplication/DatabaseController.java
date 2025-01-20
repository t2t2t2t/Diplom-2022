package com.theorygameapplication;


import com.database.DBConnect;
import com.dto.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
/*
public class DatabaseController implements Initializable {


    @FXML
    private TableColumn<Users, Integer> id;

    @FXML
    private TableColumn<Users, String> password;

    @FXML
    private Label showUsername;

    @FXML
    private TableView<Users> table;

    @FXML
    private TableColumn<Users, String> Test;

    @FXML
    private TableColumn<Users, String> username;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void detel(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }
    ResultSet resSet=null;

    public  ResultSet getResult(){
        DBConnect connect=new DBConnect();
        PreparedStatement prepStat=null;
        String connectQuery = "SELECT * FROM users";
        try {
            prepStat= connect.getConnection().prepareStatement(connectQuery);
            resSet=prepStat.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resSet;
    }


    public void connectButton(ActionEvent event) {
        DBConnect connectNow = new DBConnect();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT password FROM users";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()) {
                showUsername.setText(queryOutput.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   // private final ObservableList<Users> data= FXCollections.observableArrayList();
   private ObservableList<Users> data= FXCollections.observableArrayList(
           new Users(1,"AAA","BBB","test"),
           new Users(2,"SFDS","ET","dfg"),
           new Users(3,"SFDS","ET","dfg")
   );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      username.setCellValueFactory(new PropertyValueFactory<>("username"));
      table.setItems(data);

    }

    public void  addInf(){
        ResultSet users=getResult();

        try {
            while (users.next()){
                Users user=new Users(
                        users.getInt(1),
                        users.getString(2),
                        users.getString(3),
                        users.getString(4)
                );
                data.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}*/
