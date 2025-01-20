package com.datastore;

import com.database.DBConnect;
import com.dto.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class TestArrayList {

    public static ArrayList<Test> getTestList() {
        return TestList;
    }

    public static void setTestList(ArrayList<Test> testList) {
        TestList = testList;
    }

    /*private static ArrayList<Test> TestList=new ArrayList<> (
            Arrays.asList(
                    new Test(1,"Оптимальные стратегии игроков","test/SaddlePoint.txt"),
                    new Test(2,"Минимакс: метод решения игры","test/SaddlePoint.txt"),
                    new Test(3,"Симплекс-метод","test/SaddlePoint.txt"),
                    new Test(4,"Графический метод","test/SaddlePoint.txt"),
                    new Test(5,"Итерационный метод Брауна-Робинсона","test/SaddlePoint.txt"),
                    new Test(6,"Биматричная игра","test/SaddlePoint.txt"),
                    new Test(7,"Игры с природой","test/SaddlePoint.txt"),
                    new Test(8,"Аддитивный критерий оптимальности","test/SaddlePoint.txt")
            )
    );*/
    private static ArrayList<Test> TestList;

    public static void generationTestList() {
        ArrayList<Test> testList = new ArrayList<>();

        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT id, name, file FROM tests";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String file = resultSet.getString("file");

                Test test = new Test(id, name, file);
                testList.add(test);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TestList = testList;
    }
}
