package com.screen;


import com.theorygameapplication.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.util.Objects;

public class SwitchBetweenScreen {

    public SwitchBetweenScreen(AnchorPane anchorPane, String fxml)  throws IOException  {
        System.out.println(fxml);
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().setAll(root);
    }
}
