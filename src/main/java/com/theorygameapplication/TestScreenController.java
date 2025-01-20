package com.theorygameapplication;

import com.database.DBConnect;
import com.datastore.LectureArrayList;
import com.datastore.TestArrayList;
import com.dto.SharingData;
import com.dto.SharingUser;
import com.dto.Test;
import com.dto.Topic;
import com.screen.GetFile;
import com.screen.SwitchBetweenScreen;
import com.test.TestApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
public class TestScreenController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    private List<Question> questions;

    private List<CheckBox> answerCheckboxes;

    @FXML
    private Label lecture_name;

    @FXML
    private AnchorPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //Test test = TestArrayList.getTestList().get(SharingData.test.getId()-1);

        int targetId = SharingData.test.getId();
        Test test = null;

        for (Test t : TestArrayList.getTestList()) {
            if (t.getId() == targetId) {
                test = t;
                break;
            }
        }


        System.out.println("name" + test.getName()+
                "id" +test.getId()+
                "");
        lecture_name.setText("Тест по лекции "+test.getName());
        String testFilePath = "/com/datastore/" + test.getText();
        System.out.println(testFilePath);
        VBox testContent = new VBox();
        testContent.setPadding(new Insets(10));

        testContent.setBackground(new Background(new BackgroundFill(Color.web( "#0c192a"), null, null)));

        questions = new ArrayList<>();

        answerCheckboxes = new ArrayList<>();
        // Чтение файла и добавление вопросов и ответов в questions и testContent
        try {
            InputStream inputStream = TopicScreenController.class.getResourceAsStream(testFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length >= 3) {
                    String questionText = parts[0].trim();
                    List<String> answers = new ArrayList<>();

                    for (int i = 1; i < parts.length - 1; i++) {
                        answers.add(parts[i].trim());
                    }

                    String correctAnswer = parts[parts.length - 1].trim();
                    Question question = new Question(questionText, answers, correctAnswer);
                    questions.add(question);

                    // Создание элементов для отображения вопроса и ответов
                    Label questionLabel = new Label(questionText);
                    questionLabel.setFont(new Font(18));
                    questionLabel.setTextFill(Color.WHITE);
                    questionLabel.setMaxWidth(700); // Установите нужное значение ширины
                    questionLabel.setWrapText(true); // Разрешить перенос текста на новую строку
                    GridPane answerContainer = new GridPane();
                    answerContainer.setAlignment(Pos.CENTER);

                    for (int i = 0; i < answers.size(); i++) {
                        CheckBox checkBox = new CheckBox(answers.get(i));
                        checkBox.setFont(new Font(18));
                        checkBox.setTextFill(Color.WHITE);
                        answerCheckboxes.add(checkBox);
                        answerContainer.addRow(i, checkBox);
                    }

                        // Добавление элементов в testContent
                        testContent.getChildren().addAll(questionLabel, answerContainer);

                    }
                }


            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Button submitButton = new Button("Проверить ответы");
        Button backLecture = new Button("Прочесть лекцию");

        backLecture.setOnAction(e -> backLecture());
        submitButton.setOnAction(e -> checkAnswers());

        testContent.getChildren().add(submitButton);

        testContent.getChildren().add(backLecture);

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
            List<String> userAnswers = new ArrayList<>();

            for (int j = i * question.getAnswers().size(); j < (i + 1) * question.getAnswers().size(); j++) {
                CheckBox checkBox = answerCheckboxes.get(j);

                if (checkBox.isSelected()) {
                    userAnswers.add(checkBox.getText());
                }
            }

            if (userAnswers.size() == question.getCorrectAnswer().size() &&
                    userAnswers.containsAll(question.getCorrectAnswer())) {
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
        if((correctCount * 100 / totalQuestions)>70){
            DBConnect dbConnect=new DBConnect();
            dbConnect.addStatisticLecture(SharingUser.getUserId(),SharingData.test.getId());
        }

    }

    @FXML
    private AnchorPane alertPane;


    private static class Question {
        private String questionText;
        private List<String> answers;
        private List<String> correctAnswer;

        public Question(String questionText, List<String> answers, String correctAnswer) {
            this.questionText = questionText;
            this.answers = answers;
            this.correctAnswer = new ArrayList<>();
            this.correctAnswer.add(correctAnswer);
        }

        public String getQuestionText() {
            return questionText;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public List<String> getCorrectAnswer() {
            return correctAnswer;
        }
    }
}