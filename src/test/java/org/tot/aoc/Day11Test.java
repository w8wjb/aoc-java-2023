package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day11Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day11/sample1.txt");

        Day11 day = new Day11();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(374, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day11/input.txt");

        Day11 day = new Day11();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(9563821, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day11/sample1.txt");

        Day11 day = new Day11();

        long result = day.solvePuzzle2(lines, 10);

        Assert.assertEquals(1030, result);
    }


    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day11/input.txt");

        Day11 day = new Day11();

        long result = day.solvePuzzle2(lines, 1000000);

        Assert.assertEquals(827009909817L, result);

    }

}
