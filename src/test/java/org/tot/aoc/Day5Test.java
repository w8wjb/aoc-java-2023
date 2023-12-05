package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day5Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day5/sample.txt");

        Day5 day = new Day5();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(35, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day5/input.txt");

        Day5 day = new Day5();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(309796150, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day5/sample.txt");

        Day5 day = new Day5();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(46, result);
    }

    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day5/input.txt");

        Day5 day = new Day5();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(50716416, result);

    }

}
