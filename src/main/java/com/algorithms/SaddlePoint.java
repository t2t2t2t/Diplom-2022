package com.algorithms;

public class SaddlePoint {

    public static double var1;
    public static double var2;
    public static String var3;
    public static String var4;
    public static double var6;

    public static String Answer(double[][] matrix) {
        StringBuilder markdown = new StringBuilder();

        int rows = matrix.length;
        int columns = matrix[0].length;

        markdown.append("| Игроки ");
        for (int j = 0; j < columns; j++) {
            markdown.append("| B").append(j + 1);
        }
        markdown.append("| a = min(Ai) |\n");

        for (int j = 0; j < columns + 2; j++) {
            markdown.append("|---");
        }
        markdown.append("|\n");

        double maxInLastColumn = findMaxInColumn(matrix, columns - 1);
        int rowIndex = -1;
        int columnIndex = -1;

        for (int i = 0; i < rows; i++) {
            markdown.append("| A").append(i + 1).append(" ");
            for (int j = 0; j < columns; j++) {
                markdown.append("| ").append(matrix[i][j]);
                if (matrix[i][j] == maxInLastColumn) {
                    rowIndex = i;
                    columnIndex = j;
                }
            }
            double minInRow = findMinInRow(matrix, i);
            markdown.append("| ").append(minInRow).append(" |\n");
        }

        markdown.append("| b = max(Bi) ");
        for (int j = 0; j < columns; j++) {
            double maxInColumn = findMaxInColumn(matrix, j);
            markdown.append("| ").append(maxInColumn);
        }
        markdown.append("|\n");

        if (rowIndex != -1 && columnIndex != -1) {
            var4 = "(A" + (rowIndex + 1) + ", B" + (columnIndex + 1) + ")";
            var3 = "(" + (rowIndex + 1) + ", " + (columnIndex + 1) + ")";
        } else {
            var4 = "(?, ?)";
            var3 = "";
        }

        var1 = findMaxInColumn(matrix, columns - 1);
        var2 = findMinInRow(matrix, rows - 1);
        var6 = calculateGamePrice(matrix);

        return markdown.toString();
    }
    private static double calculateGamePrice(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        double maxInLastColumn = findMaxInColumn(matrix, columns - 1);
        int rowIndex = -1;
        int columnIndex = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == maxInLastColumn) {
                    rowIndex = i;
                    columnIndex = j;
                    break;
                }
            }
        }

        return matrix[rowIndex][columnIndex];
    }
    public static boolean hasSaddlePoint(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            double maxInRow = findMaxInRow(matrix, i);
            for (int j = 0; j < columns; j++) {
                double minInColumn = findMinInColumn(matrix, j);
                if (maxInRow == minInColumn) {
                    return true;
                }
            }
        }

        return false;
    }

    public static double findMaxInRow(double[][] matrix, int rowIndex) {
        double max = Double.NEGATIVE_INFINITY;
        for (int j = 0; j < matrix[rowIndex].length; j++) {
            if (matrix[rowIndex][j] > max) {
                max = matrix[rowIndex][j];
            }
        }
        return max;
    }

    public static double findMinInColumn(double[][] matrix, int columnIndex) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][columnIndex] < min) {
                min = matrix[i][columnIndex];
            }
        }
        return min;
    }

    private static double findMinInRow(double[][] matrix, int row) {
        double min = matrix[row][0];
        for (int j = 1; j < matrix[row].length; j++) {
            if (matrix[row][j] < min) {
                min = matrix[row][j];
            }
        }
        return min;
    }

    private static double findMaxInColumn(double[][] matrix, int column) {
        double max = matrix[0][column];
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][column] > max) {
                max = matrix[i][column];
            }
        }
        return max;
    }


}
