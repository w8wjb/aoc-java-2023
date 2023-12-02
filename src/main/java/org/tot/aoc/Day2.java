package org.tot.aoc;

import java.util.List;
import java.util.Scanner;

public class Day2 {

    static class Game {
        final int id;

        int maxRed;
        int maxGreen;
        int maxBlue;

        public Game(String input) {

            var scanner = new Scanner(input).useDelimiter("[\\s:;,]+");

            scanner.next(); // Eat the "Game suffix
            this.id = scanner.nextInt();

            while (scanner.hasNextInt()) {
                int cubes = scanner.nextInt();
                String color = scanner.next();
                switch (color) {
                    case "red":
                        maxRed = Math.max(maxRed, cubes);
                        break;
                    case "green":
                        maxGreen = Math.max(maxGreen, cubes);
                        break;
                    case "blue":
                        maxBlue = Math.max(maxBlue, cubes);
                        break;
                    default:
                        throw new RuntimeException("Unexpected color " + color);
                }
            }
        }

        boolean isValid(int redLimit, int greenLimit, int blueLimit) {
            return (maxRed <= redLimit && maxGreen <= greenLimit && maxBlue <= blueLimit);
        }

        int getPower() {
            return maxRed * maxGreen  * maxBlue;
        }

    }

    public int solvePuzzle1(List<String> input) {

        int gameIDSum = 0;

        for (String line : input) {

            var game = new Game(line);

            if (game.isValid(12, 13, 14)) {
                gameIDSum += game.id;
            }

        }


        return gameIDSum;
    }

    public int solvePuzzle2(List<String> input) {

        int powerSum = 0;

        for (String line : input) {
            var game = new Game(line);
            powerSum += game.getPower();
        }

        return powerSum;
    }

}
