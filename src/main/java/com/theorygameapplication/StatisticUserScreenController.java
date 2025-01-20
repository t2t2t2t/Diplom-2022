package com.theorygameapplication;

import com.database.DBConnect;
import com.dto.SharingData;
import com.dto.SharingUser;
import com.sandec.mdfx.MarkdownView;
import com.screen.GetFile;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.swing.border.EmptyBorder;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticUserScreenController implements Initializable  {
    @FXML
    private BorderPane center;

    @FXML
    private AnchorPane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        StringBuilder markdownTable = new StringBuilder();
        try {
            Connection connection = new DBConnect().getConnection();
            Statement statement = connection.createStatement();
            if(SharingData.getIsAdmin()==false){
                String stat="SELECT topics.name AS topic_name, " +
                        "COUNT( lectures.id) AS lectures_read, COUNT( examples.id) AS examples_solved, " +
                        "COUNT( statistics.task_id) AS tasks_solved " +
                        "FROM users " +
                        "LEFT JOIN statistics ON users.id = statistics.user_id " +
                        "LEFT JOIN lectures ON statistics.lecture_id = lectures.id " +
                        "LEFT JOIN examples ON statistics.example_id = examples.id " +
                        "LEFT JOIN tasks ON statistics.task_id = tasks.id " +
                        "LEFT JOIN topics ON topics.id IN (lectures.topic_id, examples.topic_id, tasks.topic_id) "
                        +"WHERE" +
                        "  users.id = "+ SharingUser.getUserId()
                        +
                        " GROUP BY topic_name";


                System.out.println(stat);
                ResultSet resultSet = statement.executeQuery(stat);
                markdownTable.append("| Название темы    | Лекций прочитано| Рассмотрено примеров | Решено задач |\n");
                markdownTable.append("| ---------------- | --------------- | -------------------- | ------------ |\n");

                while (resultSet.next()) {
                    String topicName = resultSet.getString("topic_name");
                    int lecturesRead = resultSet.getInt("lectures_read");
                    int examplesSolved = resultSet.getInt("examples_solved");
                    int tasksSolved = resultSet.getInt("tasks_solved");

                    markdownTable.append(String.format("| %s | %13d | %14d | %12d |\n",
                            topicName, lecturesRead, examplesSolved, tasksSolved));
                }

            }
            else {
                ResultSet resultSet = statement.executeQuery("SELECT " +
                        "users.username AS username, " +
                        "topics.name AS topic_name, " +
                        "COUNT(lectures.id) AS lectures_read, " +
                        "COUNT(examples.id) AS examples_solved, " +
                        "COUNT(statistics.task_id) AS tasks_solved " +
                        "FROM users " +
                        "LEFT JOIN statistics ON users.id = statistics.user_id " +
                        "LEFT JOIN lectures ON statistics.lecture_id = lectures.id " +
                        "LEFT JOIN examples ON statistics.example_id = examples.id " +
                        "LEFT JOIN tasks ON statistics.task_id = tasks.id " +
                        "LEFT JOIN topics ON topics.id IN (lectures.topic_id, examples.topic_id, tasks.topic_id) " +
                        "GROUP BY users.id, users.username, topics.id, topics.name");

                markdownTable.append("| Пользователь | Название темы    | Лекций прочитано | Рассмотрено примеров | Решено задач |\n");
                markdownTable.append("| --------------------- | ---------------- | ---------------- | --------------------- | ----- |\n");

                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String topicName = resultSet.getString("topic_name");
                    int lecturesRead = resultSet.getInt("lectures_read");
                    int examplesSolved = resultSet.getInt("examples_solved");
                    int tasksSolved = resultSet.getInt("tasks_solved");

                    markdownTable.append(String.format("| %-17s | %-16s | %17d | %21d | %5d |\n",
                            username, topicName, lecturesRead, examplesSolved, tasksSolved));
                }

                System.out.println(markdownTable.toString());

            }






        } catch (SQLException e) {
            e.printStackTrace();
        }



        MarkdownView mdfx = new MarkdownView(markdownTable.toString())

        {
            @Override
            protected List<String> getDefaultStylesheets() {
                return List.of(getClass().getResource("/css/mdfx-sample.css").toString());
            }
        }
                ;
        ScrollPane scrollPane = new ScrollPane(mdfx);
        mdfx.setMaxWidth(783);
        mdfx.setMinHeight(1000);
        scrollPane.setMaxWidth(783);
        scrollPane.setPrefHeight(1000);

        //scrollPane.setPrefWidth(783);
        //scrollPane.setPrefHeight(599);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Убрать прокрутку в бок

        BackgroundFill backgroundFill = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.web( "#0c192a"), null, null)));

        scrollPane.setBackground(background);
        center.setBackground(background);
        center.setCenter(scrollPane);


       /* System.out.println(mdfx);
        mdfx.setMaxWidth(783);
        BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);

        center.setBackground(background);

        center.setCenter(mdfx);*/


    }

    public static class PlayerStats {
        private String lectures;
        private String problems;
        private String tests;

        public PlayerStats(String lectures, String problems, String tests) {
            this.lectures = lectures;
            this.problems = problems;
            this.tests = tests;
        }

        public String getLectures() {
            return lectures;
        }

        public String getProblems() {
            return problems;
        }

        public String getTests() {
            return tests;
        }
    }
}
