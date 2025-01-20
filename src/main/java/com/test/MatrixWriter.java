package com.test;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MatrixWriter {
    public static void writeMatrixToMarkdown(String[] rowLabels, String[] columnLabels, int[][] matrix, String templateFilename, String outputFilename) {
        try {
            // Load the template Markdown file
            String template = new String(Files.readAllBytes(Paths.get(templateFilename)));

            // Replace placeholders with actual data
            StringBuilder markdownBuilder = new StringBuilder(template);
            int startIndex = markdownBuilder.indexOf("{{matrix_data}}");
            int endIndex = startIndex + "{{matrix_data}}".length();
            markdownBuilder.replace(startIndex, endIndex, generateMatrixMarkdown(rowLabels, columnLabels, matrix));

            // Use or save the updated Markdown as needed
            String updatedMarkdown = markdownBuilder.toString();
            FileWriter writer = new FileWriter(outputFilename);
            writer.write(updatedMarkdown);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateMatrixMarkdown(String[] rowLabels, String[] columnLabels, int[][] matrix) {
        StringBuilder markdownBuilder = new StringBuilder();

        // Запись заголовка таблицы
        markdownBuilder.append("| |");
        for (String columnLabel : columnLabels) {
            markdownBuilder.append(" ").append(columnLabel).append(" |");
        }
        markdownBuilder.append("\n");

          markdownBuilder.append("|");
        for (int i = 0; i <= columnLabels.length; i++) {
            markdownBuilder.append(" --- |");
        }
        markdownBuilder.append("\n");

        // Запись данных матрицы
        for (int i = 0; i < matrix.length; i++) {
            markdownBuilder.append("| ").append(rowLabels[i]).append(" |");
            for (int j = 0; j < matrix[i].length; j++) {
                markdownBuilder.append(" ").append(matrix[i][j]).append(" |");
            }
            markdownBuilder.append("\n");
        }

        return markdownBuilder.toString();
    }

    public static void main(String[] args) {
        String[] rowLabels = {"x", "y", "z"};
        String[] columnLabels = {"a", "b", "c"};
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        String templateFilename = "matrix.md";
        String outputFilename = "output.md";

        writeMatrixToMarkdown(rowLabels, columnLabels, matrix, templateFilename, outputFilename);
    }
}
