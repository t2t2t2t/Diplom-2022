package com.datastore;

import com.database.DBConnect;
import com.dto.Example;
import com.dto.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class LectureArrayList {

    public static ArrayList<Topic> getLectureList() {
        return LectureList;
    }

    public static void setLectureList(ArrayList<Topic> lectureList) {
        LectureList = lectureList;
    }

  /*  private static ArrayList<Topic> LectureList=new ArrayList<> (
            Arrays.asList(
                    new Topic(0,"Введение","lecture/introduction.md"),
                    new Topic(1,"Оптимальные стратегии игроков","lecture/lecture1.md"),
                    new Topic(2,"Минимакс: метод решения игры","lecture/lecture1.md"),
                    new Topic(3,"Симплекс-метод","lecture/lecture1.md"),
                    new Topic(4,"Графический метод","lecture/lecture1.md"),
                    new Topic(5,"Итерационный метод Брауна-Робинсона","lecture/lecture1.md"),
                    new Topic(6,"Биматричная игра","lecture/lecture1.md"),
                    new Topic(7,"Игры с природой","lecture/lecture1.md"),
                    new Topic(8,"Аддитивный критерий оптимальности","lecture/lecture1.md"),
                    new Topic(9,"Заключение","lecture/lecture1.md")
            )
    );*/
  private static ArrayList<Topic> LectureList;

    public static void generationLectureList() {
        ArrayList<Topic> lectureList = new ArrayList<>();

        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT id, name, file FROM lectures";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String file = resultSet.getString("file");

                Topic lecture = new Topic(id, name, file);
                lectureList.add(lecture);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LectureList= lectureList;
    }

}
