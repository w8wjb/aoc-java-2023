package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day8Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/sample.txt");

        Day8 day = new Day8();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(6, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/input.txt");

        Day8 day = new Day8();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(18157, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/sample2.txt");

        Day8 day = new Day8();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(6, result);
    }

    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/input.txt");

        Day8 day = new Day8();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(14299763833181L, result);

    }

}
