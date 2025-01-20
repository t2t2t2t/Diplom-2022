package com.test;


import com.database.DBConnect;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableViewExample extends Application {

    public static class DataModel {
        private int topicId;
        private String topicName;
        private int lecturesRead;
        private int examplesSolved;
        private int tasksSolved;

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public int getLecturesRead() {
            return lecturesRead;
        }

        public void setLecturesRead(int lecturesRead) {
            this.lecturesRead = lecturesRead;
        }

        public int getExamplesSolved() {
            return examplesSolved;
        }

        public void setExamplesSolved(int examplesSolved) {
            this.examplesSolved = examplesSolved;
        }

        public int getTasksSolved() {
            return tasksSolved;
        }

        public void setTasksSolved(int tasksSolved) {
            this.tasksSolved = tasksSolved;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<DataModel> tableView = new TableView<>();
        ObservableList<DataModel> data = FXCollections.observableArrayList();

        try {
            Connection connection = new DBConnect().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT topics.id AS topic_id, topics.name AS topic_name, " +
                    "COUNT(DISTINCT lectures.id) AS lectures_read, COUNT(DISTINCT examples.id) AS examples_solved, " +
                    "COUNT(DISTINCT statistics.task_id) AS tasks_solved " +
                    "FROM users " +
                    "LEFT JOIN statistics ON users.id = statistics.user_id " +
                    "LEFT JOIN lectures ON statistics.lecture_id = lectures.id " +
                    "LEFT JOIN examples ON statistics.example_id = examples.id " +
                    "LEFT JOIN tasks ON statistics.task_id = tasks.id " +
                    "LEFT JOIN topics ON topics.id IN (lectures.topic_id, examples.topic_id, tasks.topic_id) " +
                    "WHERE users.id = 10 " +
                    "GROUP BY topics.id, topics.name");

            while (resultSet.next()) {
                DataModel dataModel = new DataModel();
                dataModel.setTopicId(resultSet.getInt("topic_id"));
                dataModel.setTopicName(resultSet.getString("topic_name"));
                dataModel.setLecturesRead(resultSet.getInt("lectures_read"));
                dataModel.setExamplesSolved(resultSet.getInt("examples_solved"));
                dataModel.setTasksSolved(resultSet.getInt("tasks_solved"));
                data.add(dataModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TableColumn<DataModel, Integer> topicIdCol = new TableColumn<>("Topic ID");
        topicIdCol.setCellValueFactory(new PropertyValueFactory<>("topicId"));

        TableColumn<DataModel, String> topicNameCol = new TableColumn<>("Topic Name");
        topicNameCol.setCellValueFactory(new PropertyValueFactory<>("topicName"));

        TableColumn<DataModel, Integer> lecturesReadCol = new TableColumn<>("Lectures Read");
        lecturesReadCol.setCellValueFactory(new PropertyValueFactory<>("lecturesRead"));

        TableColumn<DataModel, Integer> examplesSolvedCol = new TableColumn<>("Examples Solved");
        examplesSolvedCol.setCellValueFactory(new PropertyValueFactory<>("examplesSolved"));

        TableColumn<DataModel, Integer> tasksSolvedCol = new TableColumn<>("Tasks Solved");
        tasksSolvedCol.setCellValueFactory(new PropertyValueFactory<>("tasksSolved"));

        tableView.getColumns().addAll(topicIdCol, topicNameCol, lecturesReadCol, examplesSolvedCol, tasksSolvedCol);
        tableView.setItems(data);

        Scene scene = new Scene(tableView);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
