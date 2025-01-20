package com.test;

public class MatrixToMarkdown {

    public static String convertToMarkdown(int[][] matrix) {
        StringBuilder markdown = new StringBuilder();

        int rows = matrix.length;
        int columns = matrix[0].length;

        markdown.append("| Игроки ");
        for (int j = 0; j < columns; j++) {
            markdown.append("| B").append(j + 1);
        }
        markdown.append("| a = min(Ai) |\n");

        // markdown.append("|");
        for (int j = 0; j < columns + 2; j++) {
            markdown.append("|---");
        }
        markdown.append("|\n");

        for (int i = 0; i < rows; i++) {
            markdown.append("| A").append(i + 1).append(" ");
            for (int j = 0; j < columns; j++) {
                markdown.append("| ").append(matrix[i][j]);
            }
            int minInRow = findMinInRow(matrix, i);
            markdown.append("| ").append(minInRow).append(" |\n");
        }

        markdown.append("| b = max(Bi) ");
        for (int j = 0; j < columns; j++) {
            int maxInColumn = findMaxInColumn(matrix, j);
            markdown.append("| ").append(maxInColumn);
        }
        markdown.append("|\n");

        return markdown.toString();
    }

    private static int findMinInRow(int[][] matrix, int row) {
        int min = matrix[row][0];
        for (int j = 1; j < matrix[row].length; j++) {
            if (matrix[row][j] < min) {
                min = matrix[row][j];
            }
        }
        return min;
    }

    private static int findMaxInColumn(int[][] matrix, int column) {
        int max = matrix[0][column];
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][column] > max) {
                max = matrix[i][column];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5,5},
                {3, 5,6}
        };

        String markdownTable = convertToMarkdown(matrix);
        System.out.println(markdownTable);
    }
}

