package com.test;
import java.util.Arrays;

public class BrownRobinsonMethod {
    public static void main(String[] args) {
        // Пример входной таблицы
        int[][] table = {
                {6, 1, 4},
                {2, 4, 2},
                {4, 3, 5}
        };

        int iterations = 20; // Количество итераций

        // Вызов функции с заданной таблицей и количеством итераций
        iterateBrownRobinsonMethod(table, iterations);
    }

    public static void iterateBrownRobinsonMethod(int[][] table, int iterations) {
        int rows = table.length;
        int cols = table[0].length;

        double[] a = new double[rows]; // Стратегии первого игрока
        double[] b = new double[cols]; // Стратегии второго игрока

        // Инициализация начальных стратегий
        Arrays.fill(a, 1.0 / rows);
        Arrays.fill(b, 1.0 / cols);

        System.out.println("iter\tfirs\tsecond");

        // Итерационный процесс
        for (int iteration = 1; iteration <= iterations; iteration++) {
            double[] newA = new double[rows];
            double[] newB = new double[cols];

            // Вычисление новых стратегий для первого игрока
            for (int i = 0; i < rows; i++) {
                double sum = 0.0;
                for (int j = 0; j < cols; j++) {
                    sum += table[i][j] * b[j];
                }
                newA[i] = a[i] * sum;
            }

            // Вычисление новых стратегий для второго игрока
            for (int j = 0; j < cols; j++) {
                double sum = 0.0;
                for (int i = 0; i < rows; i++) {
                    sum += table[i][j] * a[i];
                }
                newB[j] = b[j] * sum;
            }

            // Нормализация стратегий первого игрока
            double sumA = Arrays.stream(newA).sum();
            for (int i = 0; i < rows; i++) {
                newA[i] /= sumA;
            }

            // Нормализация стратегий второго игрока
            double sumB = Arrays.stream(newB).sum();
            for (int j = 0; j < cols; j++) {
                newB[j] /= sumB;
            }

            // Обновление стратегий
            a = newA;
            b = newB;

            // Вывод текущих стратегий в виде таблицы
            System.out.print(iteration + "\t\t");
            for (double value : a) {
                System.out.print(String.format("%.4f", value) + "\t\t\t");
            }
            System.out.println();
            System.out.print("\t\t");
            for (double value : b) {
                System.out.print(String.format("%.4f", value) + "\t\t\t");
            }
            System.out.println();
        }
    }
}
