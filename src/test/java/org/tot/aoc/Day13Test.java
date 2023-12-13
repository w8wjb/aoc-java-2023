package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day13Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day13/sample.txt");

        Day13 day = new Day13();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(405, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day13/input.txt");

        Day13 day = new Day13();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(9563821, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day13/sample1.txt");

        Day13 day = new Day13();

        long result = day.solvePuzzle2(lines, 10);

        Assert.assertEquals(1030, result);
    }


    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day13/input.txt");

        Day13 day = new Day13();

        long result = day.solvePuzzle2(lines, 1000000);

        Assert.assertEquals(827009909817L, result);

    }

}
