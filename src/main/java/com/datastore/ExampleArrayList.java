package com.datastore;

import com.database.DBConnect;
import com.dto.Example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExampleArrayList {

    private static ArrayList<Example> ExampleList;


    public static ArrayList<Example> getExampleList() {
        return ExampleList;
    }

    public static void setExampleList(ArrayList<Example> exampleList) {
        ExampleList = exampleList;
    }



    public static void generationExampleList() {
        ArrayList<Example> exampleList = new ArrayList<>();

        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT id, name, file FROM examples";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String file = resultSet.getString("file");

                Example example = new Example(id, name, file);
                exampleList.add(example);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ExampleList= exampleList;
    }

}
