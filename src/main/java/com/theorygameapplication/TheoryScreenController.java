package com.theorygameapplication;


import com.datastore.LectureArrayList;
import com.dto.SharingData;
import com.dto.Topic;
import com.screen.SwitchBetweenScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;


public class TheoryScreenController implements Initializable {

    @FXML
    private AnchorPane main;

    @FXML
    private ListView<HBox> buttonTopic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Topic topic : LectureArrayList.getLectureList()) {

            Button button = new Button(topic.getName());
            HBox hBox = new HBox(button);

            BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            hBox.setBackground(background);

            hBox.setAlignment(Pos.CENTER);
            button.setOnAction(event -> {
                SharingData.setIdLecture(topic.getId());
                try {
                    new SwitchBetweenScreen(main, "topic-screen.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            buttonTopic.getItems().add(hBox);
        }
    }
}
