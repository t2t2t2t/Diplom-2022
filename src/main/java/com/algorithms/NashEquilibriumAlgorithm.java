package com.algorithms;

public class NashEquilibriumAlgorithm {

    public static int[] findNashEquilibrium(double[][] payoffMatrix) {
        int numPlayers = payoffMatrix.length;
        int numStrategies = payoffMatrix[0].length;

        int[] bestResponses = new int[numPlayers];

        for (int player = 0; player < numPlayers; player++) {
            double maxPayoff = Integer.MIN_VALUE;

            for (int strategy = 0; strategy < numStrategies; strategy++) {
                double payoff = payoffMatrix[player][strategy];

                if (payoff > maxPayoff) {
                    maxPayoff = payoff;
                    bestResponses[player] = strategy;
                }
            }
        }

        boolean isNashEquilibrium = true;

        for (int player = 0; player < numPlayers; player++) {
            int bestResponse = bestResponses[player];

            for (int strategy = 0; strategy < numStrategies; strategy++) {
                if (strategy == bestResponse) continue;

                double payoff = payoffMatrix[player][strategy];

                if (payoff >= payoffMatrix[player][bestResponse]) {
                    isNashEquilibrium = false;
                    break;
                }
            }
        }

        if (isNashEquilibrium) {
            return bestResponses;
        } else {
            return null; // Равновесие Нэша не найдено
        }
    }

    public static void main(String[] args) {
        double[][] payoffMatrix = {
                {3, 2,5},
                {1, 4,0},
                {8, 4,0}
        };

        int[] nashEquilibrium = findNashEquilibrium(payoffMatrix);

        if (nashEquilibrium != null) {
            System.out.println("Fing:");
            for (int player = 0; player < nashEquilibrium.length; player++) {
                System.out.println("Igrok " + (player + 1) + ": Stratedg " + (nashEquilibrium[player] + 1));
            }
        } else {
            System.out.println("No nesh.");
        }
    }
}