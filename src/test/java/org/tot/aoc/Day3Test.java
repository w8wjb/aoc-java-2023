package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day3Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/sample1.txt");

        Day3 day = new Day3();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(4361, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/input.txt");

        Day3 day = new Day3();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(539637, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/sample1.txt");

        Day3 day = new Day3();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(467835, result);
    }

    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/input.txt");

        Day3 day = new Day3();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(82818007, result);

    }

}
