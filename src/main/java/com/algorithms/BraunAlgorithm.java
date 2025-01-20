package com.algorithms;


import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class BraunAlgorithm {

    public static StringBuilder braun(double[][] matrix, double eps) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Double> B = new ArrayList<>();
        List<Double> cB = new ArrayList<>();
        List<Double> A = new ArrayList<>();
        List<Double> cA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            B.add(0.0);
            cB.add(0.0);
        }
        for (int j = 0; j < m; j++) {
            A.add(0.0);
            cA.add(0.0);
        }
        Random rand = new Random();
        int f = rand.nextInt(m);
        int min = 0;
        double Vn = 0.0, Vv = 0.0, Vs = 0.0, Vold = 0.0;
        int count = 0;
        int width = 6;

        StringBuilder markdownTable = new StringBuilder();
        markdownTable.append("| K | i |");
        for (int i = 0; i < B.size(); i++) {
            markdownTable.append(" B:" + i + " |");
        }
        markdownTable.append(" j |");
        for (int i = 0; i < A.size(); i++) {
            markdownTable.append(" A:" + i + " |");
        }
        markdownTable.append(" Vn | Vv | Vs |\n");

        markdownTable.append("|----|---|------|------|------|---|------|------|------|----|----|-----|\n");
        DecimalFormat decimalFormat = new DecimalFormat("#.000");
        while (true) {
            count++;
            cA.set(f, cA.get(f) + 1);
            for (int i = 0; i < B.size(); i++) {
                B.set(i, B.get(i) + matrix[f][i]);
            }
            min = B.indexOf(Collections.min(B));
            cB.set(min, cB.get(min) + 1);
            for (int j = 0; j < A.size(); j++) {
                A.set(j, A.get(j) + matrix[j][min]);
            }
            markdownTable.append("| " + count + " | " + (f + 1) + " |");
            for (int i = 0; i < B.size(); i++) {
                markdownTable.append(" " + decimalFormat.format(B.get(i)) + " |");
            }
            markdownTable.append(" " + (min + 1) + " |");
            for (int j = 0; j < A.size(); j++) {
                markdownTable.append(" " + decimalFormat.format(A.get(j)) + " |");
            }
            markdownTable.append(" " + decimalFormat.format(Vn) + " | " + decimalFormat.format(Vv) + " | " + decimalFormat.format(Vs) + " |\n");

            Vn = Collections.min(B) / count;
            Vv = Collections.max(A) / count;
            Vs = (Vv + Vn) / 2;
            if (eps < 1) {
                break;
            }
            eps--;
            Vold = Vs;
            f = A.indexOf(Collections.max(A));
        }


        markdownTable.append("\n");
        markdownTable.append("V*= ").append(decimalFormat.format(Vs)).append("\n");
        double[] p = new double[A.size()];
        for (int i = 0; i < p.length; i++) {
            p[i] = cA.get(i) / count;
        }
        markdownTable.append("p*= ( ");
        for (int i = 0; i < p.length; i++) {
            markdownTable.append(decimalFormat.format(p[i])).append("; ");
        }
        markdownTable.append(")\n");
        double[] q = new double[B.size()];
        for (int i = 0; i < q.length; i++) {
            q[i] = cB.get(i) / count;
        }
        markdownTable.append("q*= ( ");
        for (int i = 0; i < q.length; i++) {
            markdownTable.append(decimalFormat.format(q[i])).append("; ");
        }
        markdownTable.append(")\n");
        return markdownTable;
    }

    public static void main(String[] args) {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double eps = 10;
        braun(matrix, eps);
    }
}
