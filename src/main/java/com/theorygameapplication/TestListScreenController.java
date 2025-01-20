package com.theorygameapplication;

import com.datastore.ExampleArrayList;
import com.datastore.TestArrayList;
import com.dto.Example;
import com.dto.SharingData;
import com.dto.Test;
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

public class TestListScreenController implements Initializable {

    @FXML
    private AnchorPane main;

    @FXML
    private ListView<HBox> buttonExample;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Test test : TestArrayList.getTestList()) {
            Button button = new Button(test.getName());
            HBox hBox = new HBox(button);

            BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            hBox.setBackground(background);
            System.out.println("name" + test.getName()+
                    "id" +test.getId()+
                    " "+test.getText());
            hBox.setAlignment(Pos.CENTER);
            button.setOnAction(event -> {
                SharingData.test.setId(test.getId());
                try {
                    new SwitchBetweenScreen(main, "test-screen.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            buttonExample.getItems().add(hBox);
        }

    }
}
