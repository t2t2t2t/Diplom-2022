package com.theorygameapplication;

import com.datastore.TaskArrayList;
import com.dto.SharingData;
import com.dto.Task;
import com.screen.SwitchBetweenScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskListScreenController implements Initializable {


    @FXML
    private AnchorPane main;

    @FXML
    private ListView<HBox> buttontTask;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Task task : TaskArrayList.getTaskList()) {
            Button button = new Button(task.getName());
            HBox hBox = new HBox(button);

            BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            hBox.setBackground(background);

            hBox.setAlignment(Pos.CENTER);
            button.setOnAction(event -> {
                SharingData.task.setId(task.getId());
                try {
                    new SwitchBetweenScreen(main, "task-screen.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            buttontTask.getItems().add(hBox);
        }
    }
}
