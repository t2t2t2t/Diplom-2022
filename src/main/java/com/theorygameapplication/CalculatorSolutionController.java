package com.theorygameapplication;


import com.dto.SharingData;
import com.dto.SharingText;
import com.sandec.mdfx.MarkdownView;
import com.screen.SwitchBetweenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CalculatorSolutionController implements Initializable  {

    @FXML
    private AnchorPane main;

    @FXML
    private javafx.scene.text.TextFlow TextFlow;

    @FXML
    private javafx.scene.control.ScrollPane ScrollPane;

    @FXML
    void back(ActionEvent event) throws IOException {
        new SwitchBetweenScreen(main, "calculator-matrix.fxml");
    }

    @FXML
    private Label topicName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topicName.setText(SharingData.calculator.getName());
        String text= SharingText.getText().toString();
        TextArea textArea = new TextArea(text);

        MarkdownView mdfx = new MarkdownView(text)
        {
            @Override
            protected List<String> getDefaultStylesheets() {
                return List.of(getClass().getResource("/css/mdfx-sample.css").toString());
            }
        }
        ;
        mdfx.setMaxWidth(785);

        BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);

        VBox box=new VBox();
        box.setMinWidth(785);
        box.getChildren().add(mdfx);
        box.setMinHeight(700);
        if(SharingText.getGraph()!=null){
        box.getChildren().add(SharingText.getGraph());}
        box.setBackground(background);
        box.setBackground(new Background(new BackgroundFill(Color.web( "#0c192a"), null, null)));
        ScrollPane.setBackground(background);
        ScrollPane.setContent(box);


    }

}
