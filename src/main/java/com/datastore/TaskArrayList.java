package com.datastore;

import com.database.DBConnect;
import com.dto.Task;
import com.dto.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskArrayList {

    public static ArrayList<Task> getTaskList() {
        return TaskList;
    }

    public static void setTaskList(ArrayList<Task> taskList) {
        TaskList = taskList;
    }

    private static ArrayList<Task> TaskList;

    public static void generationLectureList() {
        ArrayList<Task> lectureList = new ArrayList<>();

        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT id, name, file FROM tasks";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String file = resultSet.getString("file");

                Task lecture = new Task(id, name, file);
                lectureList.add(lecture);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TaskList = lectureList;
    }

}
