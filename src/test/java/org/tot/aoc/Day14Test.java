package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day14Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day14/sample.txt");

        Day14 day = new Day14();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(405, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day14/input.txt");

        Day14 day = new Day14();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(35210, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day14/sample.txt");

        Day14 day = new Day14();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(400, result);
    }


    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day14/input.txt");

        Day14 day = new Day14();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(31974, result);

    }

}
