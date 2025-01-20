package com.theorygameapplication;

import com.datastore.ExampleArrayList;
import com.dto.Example;
import com.dto.SharingData;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExampleListScreenController implements Initializable {

    @FXML
    private AnchorPane main;

    @FXML
    private ListView<HBox> buttonExample;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Example example : ExampleArrayList.getExampleList()) {
            Button button = new Button(example.getName());
            HBox hBox = new HBox(button);

            BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            hBox.setBackground(background);

            hBox.setAlignment(Pos.CENTER);
            button.setOnAction(event -> {
                SharingData.example.setId(example.getId());
                try {
                    new SwitchBetweenScreen(main, "example-screen.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            buttonExample.getItems().add(hBox);
        }
    }
}
