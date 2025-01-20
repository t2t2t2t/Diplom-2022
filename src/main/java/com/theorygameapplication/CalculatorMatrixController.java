package com.theorygameapplication;


import com.algorithms.*;
import com.dto.SharingData;
import com.dto.SharingText;
import com.screen.GetFile;
import com.screen.SwitchBetweenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorMatrixController implements Initializable  {
    @FXML
    private Label witchAlgoritm;

    public void displayNameAlgorithm(String algorithm){
        witchAlgoritm.setText(algorithm);
    }

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label algorithm;

    @FXML
    private TextField numColom;

    @FXML
    private TextField numRow;

    @FXML
    private TextField[][] matrixFields;

    @FXML
    private Button continueInput;

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane Pane;

    @FXML
    private BorderPane solution;

    @FXML
    void back(ActionEvent event) throws IOException  {
        new SwitchBetweenScreen(Pane, "calculator-screen.fxml");
    }


    private StringBuilder text=new StringBuilder();


    @FXML
    void solution(ActionEvent event) {
        SharingText.setGraph( null);
        SharingText.setText( null);
        System.out.println(SharingData.calculator.getId());
        switch (SharingData.calculator.getId()){
            case 0:
                writeMatrixNesh();
                findNesh();
                break;
            case 1:
                writeMatrixDomin();
                findDomin();
                break;
            case 2:
                if (SaddlePoint.hasSaddlePoint(getMatrix())==true)
                {
                    String template= GetFile.readFile(TopicScreenController.class,"/com/datastore/solution/"+"SaddlePoint.md");
                    text.append(new StringBuilder(template));
                    changeFile("{{matrix}}",SaddlePoint.Answer(getMatrix()));
                    changeFile("{{var1}}",Double.toString(SaddlePoint.var2));
                    changeFile("{{var2}}",Double.toString(SaddlePoint.var2));
                    changeFile("{{var3}}",SaddlePoint.var3);
                    changeFile("{{var4}}",SaddlePoint.var4);
                    changeFile("{{var6}}",Double.toString(SaddlePoint.var6));
                }
                else
                { String template= GetFile.readFile(TopicScreenController.class,"/com/datastore/solution/"+"SaddlePoint2.md");
                    text.append(new StringBuilder(template));
                    changeFile("{{matrix}}",SaddlePoint.Answer(getMatrix()));
                    changeFile("{{var1}}",Double.toString(SaddlePoint.var1));
                    changeFile("{{var2}}",Double.toString(SaddlePoint.var2));
                }


                try {
                    new SwitchBetweenScreen(Pane,"calculator-solution.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:

            SharingText.setGraph( GraphicMethod.getGraph(getMatrix()));
            SharingText.setText(GraphicMethod.getText(getMatrix()));
                try {
                    new SwitchBetweenScreen(Pane,"calculator-solution.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                StringBuilder stringBuilder = new StringBuilder();

// Заголовок таблицы
                stringBuilder.append("| k  | i | B1 | B2 | B3 | j | A1 | A2 | A3 | Vmin  | Vmax  | Vср    |\n");
                stringBuilder.append("|----|---|----|----|----|---|----|----|----|-------|-------|--------|\n");

// Данные таблицы
                stringBuilder.append("| 1  | 1 | 6  | 1  | 4  | 2 | 1  | 4  | 3  | 1     | 4     | 5/2    |\n");
                stringBuilder.append("| 2  | 2 | 8  | 5  | 6  | 2 | 2  | 8  | 6  | 5/2   | 4     | 13/4   |\n");
                stringBuilder.append("| 3  | 2 | 10 | 9  | 8  | 3 | 6  | 10 | 11 | 8/3   | 11/3  | 19/6   |\n");
                stringBuilder.append("| 4  | 3 | 14 | 12 | 13 | 2 | 7  | 14 | 14 | 3     | 7/2   | 13/4   |\n");
                stringBuilder.append("| 5  | 2 | 16 | 16 | 15 | 3 | 11 | 16 | 19 | 3     | 19/5  | 17/5   |\n");
                stringBuilder.append("| 6  | 3 | 20 | 19 | 20 | 2 | 12 | 20 | 22 | 19/6  | 11/3  | 41/12  |\n");
                stringBuilder.append("| 7  | 3 | 24 | 22 | 25 | 2 | 13 | 24 | 25 | 22/7  | 25/7  | 47/14  |\n");
                stringBuilder.append("| 8  | 3 | 28 | 25 | 30 | 2 | 14 | 28 | 28 | 25/8  | 7/2   | 53/16  |\n");
                stringBuilder.append("| 9  | 2 | 30 | 29 | 32 | 2 | 15 | 32 | 31 | 29/9  | 32/9  | 61/18  |\n");
                stringBuilder.append("| 10 | 2 | 32 | 33 | 34 | 1 | 21 | 34 | 35 | 16/5  | 7/2   | 67/20  |\n");
                stringBuilder.append("| 11 | 3 | 36 | 36 | 39 | 1 | 27 | 36 | 39 | 36/11 | 39/11 | 75/22  |\n");
                stringBuilder.append("| 12 | 3 | 40 | 39 | 44 | 2 | 28 | 40 | 42 | 13/4  | 7/2   | 27/8   |\n");
                stringBuilder.append("| 13 | 3 | 44 | 42 | 49 | 2 | 29 | 44 | 45 | 42/13 | 45/13 | 87/26  |\n");
                stringBuilder.append("| 14 | 3 | 48 | 45 | 54 | 2 | 30 | 48 | 48 | 45/14 | 24/7  | 93/28  |\n");
                stringBuilder.append("| 15 | 2 | 50 | 49 | 56 | 2 | 31 | 52 | 51 | 49/15 | 52/15 | 101/30 |\n");
                stringBuilder.append("| 16 | 2 | 52 | 53 | 58 | 1 | 37 | 54 | 55 | 13/4  | 55/16 | 107/32 |\n");
                stringBuilder.append("| 17 | 3 | 56 | 56 | 63 | 1 | 43 | 56 | 59 | 56/17 | 59/17 | 115/34 |\n");
                stringBuilder.append("| 18 | 3 | 60 | 59 | 68 | 2 | 44 | 60 | 62 | 59/18 | 31/9  | 121/36 |\n");
                stringBuilder.append("| 19 | 3 | 64 | 62 | 73 | 2 | 45 | 64 | 65 | 62/19 | 65/19 | 127/38 |\n");
                stringBuilder.append("| 20 | 3 | 68 | 65 | 78 | 2 | 46 | 68 | 68 | 13/4  | 17/5  | 133/40 |\n");

// Дополнительные строки
                stringBuilder.append("NA1 = 1  \n");
                stringBuilder.append("P(A1) = 1/20 = 1/20  \n");
                stringBuilder.append("NA2 = 7  \n");
                stringBuilder.append("P(A2) = 7/20 = 7/20  \n");
                stringBuilder.append("NA3 = 12  \n");
                stringBuilder.append("P(A3) = 12/20 = 3/5  \n");
                stringBuilder.append("NB1 = 4  \n");
                stringBuilder.append("P(B4) = 4/20 = 1/5  \n");
                stringBuilder.append("NB2 = 14  \n");
                stringBuilder.append("P(B4) = 14/20 = 7/10  \n");
                stringBuilder.append("NB3 = 2  \n");
                stringBuilder.append("P(B4) = 2/20 = 1/10  \n");
                stringBuilder.append("Цена игры, W = 133/40  \n");
                stringBuilder.append("p = (1/20, 7/20, 3/5)  \n");
                stringBuilder.append("q = (1/5, 7/10, 1/10)  \n");
                SharingText.setText(stringBuilder);

                try {
                    new SwitchBetweenScreen(Pane,"calculator-solution.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void changeFile(String variable,String textAdd){
        StringBuilder newText = new StringBuilder(text);
        int startIndex = newText.indexOf(variable);
        int endIndex = startIndex + variable.length();
        newText.replace(startIndex, endIndex, textAdd);
        text.setLength(0);
        text.append(newText);
        SharingText.setText(text);
    }

    private void findDomin(){

        double[][] matrix=RemoveDominantStrategies.RemoveDominantStrategies(getMatrix());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
                if (j < matrix[i].length - 1) {
                    sb.append("\t"); // Разделитель между элементами в строке
                }
            }
            sb.append("\n"); // Переход на новую строку
        }

        String textAdd = sb.toString();


        try {
            StringBuilder markdownBuilder = new StringBuilder(text);
            int startIndex = markdownBuilder.indexOf("{{variable2}}");
            int endIndex = startIndex + "{{variable2}}".length();
            markdownBuilder.replace(startIndex, endIndex, textAdd);
            text.setLength(0);
            text.append(markdownBuilder);
            SharingText.setText(text);
            System.out.println("22"+text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new SwitchBetweenScreen(Pane,"calculator-solution.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void writeMatrixDomin() {

        double[][] matrix = getMatrix();

        // Определение количества столбцов в матрице
        int maxColumns = 0;
        for (double[] row : matrix) {
            int rowLength = row.length;
            if (rowLength > maxColumns) {
                maxColumns = rowLength;
            }
        }

        // Создание таблицы Markdown
        StringBuilder tableBuilder = new StringBuilder();
        // Добавление заголовков столбцов
        for (int i = 1; i <= maxColumns; i++) {
            tableBuilder.append("| Столбец ").append(i).append(" ");
        }
        tableBuilder.append("|\n");

        // Добавление разделителя заголовков
        for (int i = 1; i <= maxColumns; i++) {
            tableBuilder.append("|-------------");
        }
        tableBuilder.append("|\n");

        // Добавление значений из матрицы
        for (double[] row : matrix) {
            for (double value : row) {
                tableBuilder.append("| ").append(value).append(" ");
            }
            // Добавление пустых ячеек для выравнивания строк
            for (int i = row.length; i < maxColumns; i++) {
                tableBuilder.append("| ");
            }
            tableBuilder.append("|\n");
        }
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current der " + currentDirectory+"/b.md");
        String table = tableBuilder.toString();
        try {
            String template= GetFile.readFile(CalculatorMatrixController.class,"/com/datastore/solution/"+"b.md");
            StringBuilder markdownBuilder = new StringBuilder(template);
            int startIndex = markdownBuilder.indexOf("{{variable1}}");
            int endIndex = startIndex + "{{variable1}}".length();
            markdownBuilder.replace(startIndex, endIndex, table);
            text.append(markdownBuilder);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Первый"+text);
    }


    private void findNesh(){
        int[] nashEquilibrium = NashEquilibriumAlgorithm.findNashEquilibrium(getMatrix());
        StringBuilder stringBuilder = new StringBuilder();

        if (nashEquilibrium != null) {

            for (int player = 0; player < nashEquilibrium.length; player++) {
                stringBuilder.append("Игрок ").append(player + 1).append(": Стратегия ").append(nashEquilibrium[player] + 1).append(System.lineSeparator());
            }
        } else {
            stringBuilder.append("Нет неравенства").append(System.lineSeparator());
        }

        String textAdd = stringBuilder.toString();


        try {
            StringBuilder markdownBuilder = new StringBuilder(text);
            int startIndex = markdownBuilder.indexOf("{{variable2}}");
            int endIndex = startIndex + "{{variable2}}".length();
            markdownBuilder.replace(startIndex, endIndex, textAdd);
            text.setLength(0);
            text.append(markdownBuilder);
            SharingText.setText(text);
            System.out.println("22"+text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new SwitchBetweenScreen(Pane,"calculator-solution.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void continueInput(ActionEvent event) {
        int rows = Integer.parseInt(numRow.getText());
        int cols = Integer.parseInt(numColom.getText());
        matrixFields = new TextField[rows][cols];

        grid.getChildren().clear();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

       for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TextField textField = new TextField();
                matrixFields[i][j] = textField;
                gridPane.add(textField, j, i);
            }
        }
        gridPane.setAlignment(Pos.CENTER);
        grid.getChildren().add(gridPane);

    }

    public  void writeMatrixNesh() {

        double[][] matrix = getMatrix();

        // Определение количества столбцов в матрице
        int maxColumns = 0;
        for (double[] row : matrix) {
            int rowLength = row.length;
            if (rowLength > maxColumns) {
                maxColumns = rowLength;
            }
        }

        // Создание таблицы Markdown
        StringBuilder tableBuilder = new StringBuilder();
        // Добавление заголовков столбцов
        for (int i = 1; i <= maxColumns; i++) {
            tableBuilder.append("| Столбец ").append(i).append(" ");
        }
        tableBuilder.append("|\n");

        // Добавление разделителя заголовков
        for (int i = 1; i <= maxColumns; i++) {
            tableBuilder.append("|-------------");
        }
        tableBuilder.append("|\n");

        // Добавление значений из матрицы
        for (double[] row : matrix) {
            for (double value : row) {
                tableBuilder.append("| ").append(value).append(" ");
            }
            // Добавление пустых ячеек для выравнивания строк
            for (int i = row.length; i < maxColumns; i++) {
                tableBuilder.append("| ");
            }
            tableBuilder.append("|\n");
        }
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current der " + currentDirectory+"/a.md");
        String table = tableBuilder.toString();
        try {
            String template= GetFile.readFile(TopicScreenController.class,"/com/datastore/solution/"+"a.md");
            StringBuilder markdownBuilder = new StringBuilder(template);
            int startIndex = markdownBuilder.indexOf("{{variable1}}");
            int endIndex = startIndex + "{{variable1}}".length();
            markdownBuilder.replace(startIndex, endIndex, table);
            text.append(markdownBuilder);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Первый"+text);
    }

    public double[][] getMatrix() {
        double[][] matrix = new double[matrixFields.length][matrixFields[0].length];
        boolean errorOccurred = false;

        for (int i = 0; i < matrixFields.length; i++) {
            for (int j = 0; j < matrixFields[i].length; j++) {
                String value = matrixFields[i][j].getText();
                try {
                    double parsedValue = Double.parseDouble(value);
                    matrix[i][j] = parsedValue;
                } catch (NumberFormatException e) {
                    showAlert("Ошибка", "Неверный формат числа",
                            "Пожалуйста, введите число в " + (i + 1) + " строку " + (j + 1) + " столбец");
                    errorOccurred = true;
                    break;
                }
            }
            if (errorOccurred) {
                break;
            }
        }

        if (errorOccurred) {
            return null; // Return null to indicate an error occurred
        } else {
            return matrix;
        }
    }
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        witchAlgoritm.setText(SharingData.calculator.getName());
    }
}
