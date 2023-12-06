package org.tot.aoc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static class Race {
        final double time;
        final double distance;

        public Race(double time, double distance) {
            this.time = time;
            this.distance = distance;
        }
    }

    public long solvePuzzle1(Race[] races) {


        long waysToWin = 1;

        for (Race race : races) {

            double b = race.time;
            double c = race.distance;

            double x1 = (b - Math.sqrt(Math.pow(b, 2) - (4 * c))) / 2;
            double x2 = (b + Math.sqrt(Math.pow(b, 2) - (4 * c))) / 2;
            double span = Math.ceil(x2) - Math.floor(x1) - 1;

            waysToWin *= span;
        }


        return waysToWin;
    }


}
