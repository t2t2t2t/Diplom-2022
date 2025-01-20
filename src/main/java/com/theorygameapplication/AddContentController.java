package com.theorygameapplication;

import com.database.DBConnect;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddContentController  implements Initializable {
    @FXML
    private Button AddAll;

    @FXML
    private CheckBox ExampleCB;

    @FXML
    private MFXTextField Name;

    @FXML
    private CheckBox TaskCB;

    @FXML
    private CheckBox TestCB;

    @FXML
    private MFXTextField Topic_name;

    @FXML
    private Button addFileTest;

    @FXML
    private AnchorPane anch;

    @FXML
    private Label file_name;

    @FXML
    private CheckBox lectureCB;

    @FXML
    private Label lecture_name;

    @FXML
    private AnchorPane main;



    @FXML
    void addFileTest(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                // Определите целевую папку, например: "src/main/resources"
                String targetFolderPath = "src/main/resources/com/datastore/"+folder;
                System.out.println("src/main/resources/com/datastore/"+folder);
                Path targetFolder = Path.of(targetFolderPath);

                // Переместите выбранный файл в целевую папку
                Path sourcePath = selectedFile.toPath();
                Path targetPath = targetFolder.resolve(selectedFile.getName());
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                Connection connection = new DBConnect().getConnection();
                DBConnect dbConnect = new DBConnect();
                dbConnect.getConnection();
                System.out.println("Файл успешно перемещен в ресурсы проекта.");
                switch (folder){
                    case "example":
                        dbConnect.addExample(Name.getText(),folder+"/"+selectedFile.getName(),choiceTopicId);
                        showAlertInfo("Успешно", "Добавление материала", "Пример добавлен в систему");
                        break;
                    case "test":
                        dbConnect.addTest(Name.getText(),folder+"/"+selectedFile.getName(),choiceTopicId);
                        showAlertInfo("Успешно", "Добавление материала", "Тест добавлен в систему");
                        break;
                    case "lecture":
                        dbConnect.addLecture(Name.getText(),folder+"/"+selectedFile.getName(),choiceTopicId);
                        showAlertInfo("Успешно", "Добавление материала", "Лекция добавлена в систему");
                        break;
                    case "task":
                        dbConnect.addTask(Name.getText(),folder+"/"+selectedFile.getName(),choiceTopicId);
                        showAlertInfo("Успешно", "Добавление материала", "Задачи добавлены в систему");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Ошибка при перемещении файла: " + e.getMessage());
            }
        }
    }
    private void showAlertInfo(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void AddAll(ActionEvent event) {

    }
    private String folder = "";

    @FXML
    void TestCB(ActionEvent event) {
        folder="test";
        ExampleCB.setSelected(false);
        TaskCB.setSelected(false);
        lectureCB.setSelected(false);
        List<String> topicNames = getAllTopicNamesForTest();
        topicChoiceBox.getItems().clear();
        topicChoiceBox.getItems().addAll(topicNames);
    }
    @FXML
    private Button addTopicButton;
    @FXML
    void addTopicButton(ActionEvent event) {
        addTopic(topicName.getText());
    }
    private void addTopic(String topicName) {
        String query = "INSERT INTO topics (name) VALUES (?)";

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, topicName);
            statement.executeUpdate();
            System.out.println("Новая тема успешно добавлена в базу данных.");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении новой темы: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private MFXTextField topicName;
    @FXML
    void lectureCB(ActionEvent event) {
        folder="lecture";
        ExampleCB.setSelected(false);
        TaskCB.setSelected(false);
        TestCB.setSelected(false);
        // Обновите список названий тем для выбранной папки
        List<String> topicNames = getAllTopicNames();
        topicChoiceBox.getItems().clear();
        topicChoiceBox.getItems().addAll(topicNames);
    }
    @FXML
    void ExampleCB(ActionEvent event) {
        folder="example";
        lectureCB.setSelected(false);
        TaskCB.setSelected(false);
        TestCB.setSelected(false);
        // Обновите список названий тем для выбранной папки
        List<String> topicNames = getAllTopicNames();
        topicChoiceBox.getItems().clear();
        topicChoiceBox.getItems().addAll(topicNames);
    }

    @FXML
    void TaskCB(ActionEvent event) {
        folder="task";
        lectureCB.setSelected(false);
        ExampleCB.setSelected(false);
        TestCB.setSelected(false);

        // Обновите список названий тем для выбранной папки
        List<String> topicNames = getAllTopicNames();
        topicChoiceBox.getItems().clear();
        topicChoiceBox.getItems().addAll(topicNames);
    }

    private DBConnect dbConnect;
    @FXML
    private ChoiceBox<String> topicChoiceBox;
    private int choiceTopicId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         dbConnect = new DBConnect();

        // Получите список всех названий тем из базы данных
        List<String> topicNames = getAllTopicNames();

        // Установите список названий тем в ChoiceBox
        topicChoiceBox.getItems().addAll(topicNames);

        // Установите обработчик события выбора элемента в ChoiceBox
        topicChoiceBox.setOnAction(event -> {
            String selectedTopic = topicChoiceBox.getValue();
            int topicId = findTopicIdByName(selectedTopic);
            if(folder=="test"){
                topicId = findTopicLectureIdByName(selectedTopic);
            }

            choiceTopicId=topicId;

            // Выполните необходимые действия при выборе темы, например, вывод другой информации или выполнение запроса к базе данных
            System.out.println("Выбрана тема: " + selectedTopic + ", ID: " + topicId);
            System.out.println(choiceTopicId);
        });
    }

    private List<String> getAllTopicNames() {
        List<String> topicNames = new ArrayList<>();

        try (Connection connection = dbConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM topics")) {
            while (resultSet.next()) {
                String topicName = resultSet.getString("name");
                topicNames.add(topicName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topicNames;
    }

    private List<String> getAllTopicNamesForTest() {
        List<String> topicNames = new ArrayList<>();


        try (Connection connection = dbConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM lectures")) {
            while (resultSet.next()) {
                String topicName = resultSet.getString("name");
                topicNames.add(topicName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return topicNames;
    }
    private int findTopicIdByName(String topicName) {
        String query = "SELECT id FROM topics WHERE name = ?";
        int topicId = -1;

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, topicName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                topicId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topicId;
    }
    private int findTopicLectureIdByName(String topicName) {
        String query = "SELECT id FROM lectures WHERE name = ?";
        int topicId = -1;

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, topicName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                topicId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topicId;
    }
}
