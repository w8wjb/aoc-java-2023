package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day9Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/sample.txt");

        Day9 day = new Day9();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(114, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/input.txt");

        Day9 day = new Day9();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(1995001648, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/sample.txt");

        Day9 day = new Day9();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(2, result);
    }

    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/input.txt");

        Day9 day = new Day9();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(988, result);

    }

}
