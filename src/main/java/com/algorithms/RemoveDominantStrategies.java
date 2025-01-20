package com.algorithms;

public class RemoveDominantStrategies {

    public static double[][] removeDominantStrategies(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Find maximum values in each row and column
        double[] rowMaxes = new double[rows];
        double[] colMaxes = new double[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double val = matrix[i][j];
                rowMaxes[i] = Math.max(rowMaxes[i], val);
                colMaxes[j] = Math.max(colMaxes[j], val);
            }
        }

        // Count the number of dominant strategies
        int dominantCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double val = matrix[i][j];
                if (val == rowMaxes[i] && val == colMaxes[j]) {
                    dominantCount++;
                }
            }
        }

        // Create a new matrix without dominant strategies
        double[][] newMatrix = new double[rows - dominantCount][cols - dominantCount];
        int newRow = 0;
        int newCol;
        for (int i = 0; i < rows; i++) {
            if (rowMaxes[i] != colMaxes[i]) {
                newCol = 0;
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] != rowMaxes[i]) {
                        newMatrix[newRow][newCol] = matrix[i][j];
                        newCol++;
                    }
                }
                newRow++;
            }
        }

        return newMatrix;
    }

    public static double [][] RemoveDominantStrategies(double [][] matrix) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;


        // remove dominant strategies
        boolean[] rowDominant = new boolean[numRows];
        boolean[] colDominant = new boolean[numCols];
        for (int i = 0; i < numRows; i++) {
            boolean isDominant = true;
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] < matrix[i][0]) {
                    isDominant = false;
                    break;
                }
            }
            rowDominant[i] = isDominant;
        }
        for (int j = 0; j < numCols; j++) {
            boolean isDominant = true;
            for (int i = 0; i < numRows; i++) {
                if (matrix[i][j] > matrix[0][j]) {
                    isDominant = false;
                    break;
                }
            }
            colDominant[j] = isDominant;
        }
        double [][]newMatrix = new double[numRows][numCols];
        int newNumRows = 0;
        int newNumCols = 0;
        if (rowDominant[0]) {
            // if first row is dominant, add it to the new matrix
            for (int j = 0; j < numCols; j++) {
                newMatrix[newNumRows][j] = matrix[0][j];
            }
            newNumRows++;
        } else if (colDominant[0]) {
            // if first column is dominant, add it to the new matrix
            for (int i = 0; i < numRows; i++) {
                newMatrix[i][newNumCols] = matrix[i][0];
            }
            newNumCols++;
        }
        for (int i = 1; i < numRows; i++) {
            if (!rowDominant[i]) {
                // if the row is not dominant, add it to the new matrix
                for (int j = 0; j < numCols; j++) {
                    newMatrix[newNumRows][j] = matrix[i][j];
                }
                newNumRows++;
            }
        }
        for (int j = 1; j < numCols; j++) {
            if (!colDominant[j]) {
                // if the column is not dominant, add it to the new matrix
                for (int i = 0; i < numRows; i++) {
                    newMatrix[i][newNumCols] = matrix[i][j];
                }
                newNumCols++;
            }
        }

        return newMatrix;
    }

}
