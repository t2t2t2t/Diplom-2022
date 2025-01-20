package com.theorygameapplication;

import com.datastore.LectureArrayList;
import com.dto.SharingData;
import com.dto.Topic;
import com.sandec.mdfx.MarkdownView;
import com.screen.GetFile;
import com.screen.SwitchBetweenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;

public class TopicScreenController implements Initializable {


    @FXML
    private Label topicName;

    @FXML
    void back(ActionEvent event) throws IOException {
        new SwitchBetweenScreen(main, "theory-screen.fxml");
    }

    @FXML
    private AnchorPane main;

    @FXML
    private TextFlow TextFlow;

    @FXML
    private ScrollPane ScrollPane;

    @FXML
    private TextArea textArea;


    @FXML
    void lastLecture(ActionEvent event) throws IOException {
        SharingData.setIdLecture(SharingData.getIdLecture()-1);
        new SwitchBetweenScreen(main, "topic-screen.fxml");
    }
    @FXML
    private Button lastLecture;


    @FXML
    private Button nextLecture;

    @FXML
    void nextLecture(ActionEvent event) throws IOException {
        SharingData.setIdLecture(SharingData.getIdLecture()+1);
        new SwitchBetweenScreen(main, "topic-screen.fxml");
    }
    @FXML
    void goToTest(ActionEvent event)  throws IOException {
        SharingData.test.setId(SharingData.getIdLecture());
        new SwitchBetweenScreen(main, "test-screen.fxml");
    }

    @FXML
    private Button goToTest;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        int targetId = SharingData.getIdLecture();
        Topic topic = null;

        for (Topic lecture : LectureArrayList.getLectureList()) {
            if (lecture.getId() == targetId) {
                topic = lecture;
                break;
            }
        }

        if(SharingData.getIdLecture()==0){
            goToTest.setDisable(true);
        }

        if(SharingData.getIdLecture()==9){
            goToTest.setDisable(true);
        }

        if(LectureArrayList.getLectureList().get(0)==topic){
            lastLecture.setDisable(true);
        }

        if(LectureArrayList.getLectureList().get(LectureArrayList.getLectureList().size()-1)==topic){
            nextLecture.setDisable(true);
        }

        topicName.setText(topic.getName());

        String text="";

        text=GetFile.readFile(TopicScreenController.class,"/com/datastore/"+topic.getLectureFile());
        MarkdownView mdfx = new MarkdownView(text)

        {
            @Override
            protected List<String> getDefaultStylesheets() {
                return List.of(getClass().getResource("/css/mdfx-sample.css").toString());
            }
        }
        ;
        System.out.println(mdfx);
        mdfx.setMaxWidth(783);
       BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        TextArea textArea = new TextArea(text);
        textArea.setPrefWidth(783);
        textArea.setPrefHeight(599);
        ScrollPane.setBackground(background);
        ScrollPane.setContent(mdfx);
    }
    private static String printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line="";
            while ((line = reader.readLine()) != null) {
                line+=line;
            }
            return line;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}