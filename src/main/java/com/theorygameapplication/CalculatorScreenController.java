package com.theorygameapplication;


import com.dto.Calculator;
import com.dto.SharingData;
import com.screen.SwitchBetweenScreen;
import io.github.palexdev.materialfx.controls.MFXListView;
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

public class CalculatorScreenController implements Initializable {

    @FXML
    private AnchorPane main;
    @FXML
    private ListView<Button> taskList;
    @FXML
    private MFXListView<Button> MFXLIST;
    @FXML
    private ListView<HBox> ListView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Calculator> calculators = new ArrayList<>();

        //  calculators.add(new Calculator("Равновесие нэша ", 0));

        calculators.add(new Calculator("Удаление доминирующий стратегий ", 1));

        calculators.add(new Calculator("Седловая точка ", 2));

        calculators.add(new Calculator("Графический метод ", 3));

        calculators.add(new Calculator("Итерационный метод Брауна-Робинсона ", 4));
        calculators.add(new Calculator("Симплекс-метод  ", 5));
        calculators.add(new Calculator("Биматричная игра   ", 6));
        calculators.add(new Calculator("Игры с природой  ", 7));
        calculators.add(new Calculator("Аддитивный критерий оптимальности  ", 8));

        for (Calculator calculator : calculators) {
            Button button = new Button(calculator.getName());
            HBox hBox = new HBox(button);

            BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            hBox.setBackground(background);

            hBox.setAlignment(Pos.CENTER);
            button.setOnAction(event -> {
                SharingData.calculator.setName(calculator.getName());
                SharingData.calculator.setId(calculator.getId());
                System.out.println(SharingData.topic.getLectureFile());
                try {
                   // System.out.println(task.getTaskFxml());
                    new SwitchBetweenScreen(main,"calculator-matrix.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            ListView.getItems().add(hBox);
        }
    }

}
