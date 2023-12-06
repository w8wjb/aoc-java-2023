package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Day6Test {

    @Test
    public void testSample1() {

        Day6 day = new Day6();

        // Time:      7  15   30
        // Distance:  9  40  200

        Day6.Race[] races = {
                new Day6.Race(7, 9),
                new Day6.Race(15, 40),
                new Day6.Race(30, 200)
        };

        long result = day.solvePuzzle1(races);

        Assert.assertEquals(288, result);
    }

    @Test
    public void testSolution1() {
        Day6 day = new Day6();

        // Time:        40     81     77     72
        // Distance:   219   1012   1365   1089

        Day6.Race[] races = {
                new Day6.Race(40, 219),
                new Day6.Race(81, 1012),
                new Day6.Race(77, 1365),
                new Day6.Race(72, 1089)
        };

        long result = day.solvePuzzle1(races);

        Assert.assertEquals(861300, result);
    }

    @Test
    public void testSample2() {

        Day6 day = new Day6();

        Day6.Race[] races = {
                new Day6.Race(71530, 940200)
        };

        long result = day.solvePuzzle1(races);
        Assert.assertEquals(71503, result);
    }

    @Test
    public void testSolution2() {

        // Time:        40     81     77     72
        // Distance:   219   1012   1365   1089

        Day6 day = new Day6();

        Day6.Race[] races = {
                new Day6.Race(40817772, 219101213651089L)
        };

        long result = day.solvePuzzle1(races);
        Assert.assertEquals(28101347, result);

    }


}
