package com.theorygameapplication;

import com.database.DBConnect;
import com.datastore.ExampleArrayList;
import com.dto.Example;
import com.dto.SharingData;
import com.dto.SharingUser;
import com.dto.Topic;
import com.sandec.mdfx.MarkdownView;
import com.screen.GetFile;
import com.screen.SwitchBetweenScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;

public class ExampleScreenController  implements Initializable {

    @FXML
    private Label exampleName;

    @FXML
    void back(ActionEvent event) throws IOException {
        new SwitchBetweenScreen(main, "example-list-screen.fxml");
    }

    @FXML
    private AnchorPane main;


    @FXML
    private javafx.scene.control.ScrollPane ScrollPane;

    @FXML
    void lastExample(ActionEvent event) throws IOException {
        SharingData.example.setId(SharingData.example.getId()-1);
        new SwitchBetweenScreen(main, "example-screen.fxml");
    }


    @FXML
    void nextExample(ActionEvent event) throws IOException {
        SharingData.example.setId(SharingData.example.getId()+1);

        new SwitchBetweenScreen(main, "example-screen.fxml");
    }
    @FXML
    private Button lastExample;


    @FXML
    private CheckBox isReady;

    @FXML
    private Button nextExample;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DBConnect connect=new DBConnect();
        System.out.println();
        System.out.println(SharingUser.getUserId()+" "+SharingData.example.getId());
       if( connect.checkExampleExistence(SharingUser.getUserId(),SharingData.example.getId())){
           isReady.setSelected(true);
       };

        int targetId = SharingData.example.getId();
        Example example = null;

        for (Example e : ExampleArrayList.getExampleList()) {
            if (e.getId() == targetId) {
                example = e;
                break;
            }
        }

        if(ExampleArrayList.getExampleList().get(0)==example){
            lastExample.setDisable(true);
        }

        if(ExampleArrayList.getExampleList().get(ExampleArrayList.getExampleList().size()-1)==example){
            nextExample.setDisable(true);
        }


        exampleName.setText(example.getName());
        System.out.println(example.getName());
        String text="";
        System.out.println(example.getMd());

        text= GetFile.readFile(ExampleScreenController.class,"/com/datastore/"+example.getMd());
        System.out.println("text"+text);
        MarkdownView mdfx = new MarkdownView(text)
        {
            @Override
            protected List<String> getDefaultStylesheets() {
                return List.of(getClass().getResource("/css/mdfx-sample.css").toString());
            }
        }
        ;
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


    @FXML
    void isReady(ActionEvent event) {
        System.out.println("user id "+SharingUser.getUserId());
        DBConnect dbConnect=new DBConnect();

        System.out.println(SharingData.example.getId());
        if (isReady.isSelected()) {

            dbConnect.addStatisticExample(SharingUser.getUserId(),SharingData.example.getId());
        }
        else {
            dbConnect.deleteStatisticExample(SharingUser.getUserId(),SharingData.example.getId());
        }
    }
}
