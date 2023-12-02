package org.tot.aoc;

import java.util.List;
import java.util.Scanner;

public class Day2 {

    /**
     * A data structure to hold information about a particular game
     */
    static class Game {
        final int id;

        int maxRed;
        int maxGreen;
        int maxBlue;

        public Game(String input) {

            // All of the string parsing is in here.
            // I decided to use a Scanner, because I find String.split() to be a bit ugly to work with
            // Notice that commas and semicolons are treated the same. For both stars, the distinction isn't meaningful

            var scanner = new Scanner(input).useDelimiter("[\\s:;,]+");

            scanner.next(); // Eat the "Game suffix
            this.id = scanner.nextInt();

            // The number of cubes comes first, before the color, so we can tell there is another pair by looking ahead
            while (scanner.hasNextInt()) {

                // This is what makes Scanner nice; it handles the conversion from String into more useful types
                int cubes = scanner.nextInt();
                String color = scanner.next();
                // Old timer alert: Back in my day, Java didn't support Strings in switch statments
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
                        // Always provide a default
                        throw new RuntimeException("Unexpected color " + color);
                }
            }
        }

        boolean isValid(int redLimit, int greenLimit, int blueLimit) {
            // These are the rules for what makes a valid game from part 1
            // I avoided hard coding the color limits because I didn't know whether I would need different ones for part 2
            return (maxRed <= redLimit && maxGreen <= greenLimit && maxBlue <= blueLimit);
        }

        int getPower() {
            // Part 2 defines "power" this way
            return maxRed * maxGreen * maxBlue;
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
