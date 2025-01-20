package com.theorygameapplication;

import com.database.DBConnect;
import com.datastore.TaskArrayList;
import com.datastore.TestArrayList;
import com.dto.SharingData;
import com.dto.SharingUser;
import com.dto.Task;
import com.dto.Test;
import com.sandec.mdfx.MarkdownView;
import com.screen.GetFile;
import com.screen.SwitchBetweenScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TaskController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    private List<Question> questions;

    private List<TextField> answerTextFields;

    @FXML
    private Label lecture_name;

    @FXML
    private AnchorPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int targetId = SharingData.task.getId();
        Task task = null;

        for (Task t : TaskArrayList.getTaskList()) {
            if (t.getId() == targetId) {
                task = t;
                break;
            }
        }
        String testFilePath = "/com/datastore/" + task.getText();
        System.out.println(testFilePath);
        VBox testContent = new VBox();
        testContent.setPadding(new Insets(10));

        testContent.setBackground(new Background(new BackgroundFill(Color.web( "#0c192a"), null, null)));
        testContent.setAlignment(Pos.CENTER);
        questions = new ArrayList<>();
        answerTextFields = new ArrayList<>();

        String text = GetFile.readFile(TopicScreenController.class, testFilePath);
        String[] lines = text.split(";");

        for (int i = 0; i < lines.length; i += 2) {
            String questionText = lines[i].trim();
            String correctAnswer = lines[i + 1].trim();

            Question question = new Question(questionText, correctAnswer);
            questions.add(question);
            System.out.println(question.getQuestionText());

            MarkdownView mdfx = new MarkdownView(question.getQuestionText()) {
                @Override
                protected List<String> getDefaultStylesheets() {
                    return List.of(getClass().getResource("/css/mdfx-sample.css").toString());
                }
            };

            GridPane answerContainer = new GridPane();
            answerContainer.setAlignment(Pos.CENTER);
            TextFlow answerTextFlow = new TextFlow();


                TextField textField = new TextField();
                answerTextFields.add(textField);
                answerTextFlow.getChildren().add(textField);


            answerContainer.addRow(i, answerTextFlow);
            testContent.getChildren().addAll(mdfx, answerContainer);
        }




        Button submitButton = new Button("Проверить ответы");
        submitButton.setOnAction(e -> checkAnswers());
        testContent.getChildren().add(submitButton);
        BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        testContent.setPrefWidth(783);
        testContent.setMinHeight(617);
        testContent.setAlignment(Pos.CENTER);
        scrollPane.setBackground(background);
        scrollPane.setContent(testContent);


    }
    @FXML
    private void backLecture()  {
        try {
            SharingData.setIdLecture(SharingData.test.getId());
            new SwitchBetweenScreen(main, "topic-screen.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void checkAnswers() {
        int correctCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String userAnswer = answerTextFields.get(i).getText().trim();

            if (userAnswer.equals(question.getCorrectAnswer())) {
                correctCount++;
            }
        }

        int totalQuestions = questions.size();
        int incorrectCount = totalQuestions - correctCount;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Результаты теста");
        alert.setHeaderText(null);
        alert.setContentText("Правильные ответы: " + correctCount +
                "\nНеправильные ответы: " + incorrectCount +
                "\nПроцент правильных ответов: " + (correctCount * 100 / totalQuestions) + "%");
        alert.showAndWait();

        if ((correctCount * 100 / totalQuestions) > 70) {
            DBConnect dbConnect = new DBConnect();
            dbConnect.addStatisticTask(SharingUser.getUserId(), SharingData.task.getId());
        }
    }

    private static class Question {
        private String questionText;
        private String correctAnswer;

        public Question(String questionText, String correctAnswer) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
