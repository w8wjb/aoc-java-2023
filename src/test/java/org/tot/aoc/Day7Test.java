package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day7Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/sample1.txt");

        Day7 day = new Day7();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(6440, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/input.txt");

        Day7 day = new Day7();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(251545216, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/sample1.txt");

        Day7 day = new Day7();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(5905, result);
    }

    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/input.txt");

        Day7 day = new Day7();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(250384185, result);

    }

}
