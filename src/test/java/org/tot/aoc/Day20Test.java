package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day20Test {

    @Test
    public void testSample1_1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample1.txt");

        Day20 day = new Day20();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(32000000L, result);
    }

    @Test
    public void testSample1_2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample2.txt");

        Day20 day = new Day20();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(11687500L, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/input.txt");

        Day20 day = new Day20();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(1020211150, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample.txt");

        Day20 day = new Day20();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(400, result);
    }


    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/input.txt");

        Day20 day = new Day20();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(31974, result);

    }

}
