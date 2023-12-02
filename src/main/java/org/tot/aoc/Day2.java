package org.tot.aoc;

import java.util.ArrayList;
import java.util.List;

public class Day2 {

    static class Game {
        final int id;

        List<Round> rounds = new ArrayList<>();

        public Game(String input) {

            String[] parts = input.split(":");
            String gameHeader = parts[0];
            String roundList = parts[1];

            parts = gameHeader.split(" ");
            this.id = Integer.parseInt(parts[1]);

            for (String roundStr : roundList.split(";")) {
                String[] pairs = roundStr.split(",");

                Round round = new Round();

                for (String pair : pairs) {
                    parts = pair.trim().split(" ");
                    int cubes = Integer.parseInt(parts[0]);
                    String color = parts[1].trim();
                    round.setColorCount(color, cubes);
                }

                this.rounds.add(round);
            }
        }

        boolean isValid(int redLimit, int greenLimit, int blueLimit) {
            for (var round : rounds) {
                if (round.red > redLimit || round.green > greenLimit || round.blue > blueLimit) {
                    return false;
                }
            }
            return true;
        }

        int getMaxRed() {
            int max = 0;
            for (var round : rounds) {
                max = Math.max(max, round.red);
            }
            return max;
        }

        int getMaxGreen() {
            int max = 0;
            for (var round : rounds) {
                max = Math.max(max, round.green);
            }
            return max;
        }

        int getMaxBlue() {
            int max = 0;
            for (var round : rounds) {
                max = Math.max(max, round.blue);
            }
            return max;
        }

        int getPower() {
            int r = Math.max(getMaxRed(), 1);
            int g = Math.max(getMaxGreen(), 1);
            int b = Math.max(getMaxBlue(), 1);
            return r * g  * b;
        }

    }

    static class Round {
        int red;
        int green;
        int blue;

        void setColorCount(String color, int count) {
            switch (color) {
                case "red":
                    red = count;
                    break;
                case "green":
                    green = count;
                    break;
                case "blue":
                    blue = count;
                    break;
                default:
                    throw new RuntimeException("Unexpected color " + color);
            }
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
