package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day4Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/sample1.txt");

        Day4 day = new Day4();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(13, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/input.txt");

        Day4 day = new Day4();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(23750, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/sample1.txt");

        Day4 day = new Day4();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(30, result);
    }

    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/input.txt");

        Day4 day = new Day4();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(13261850, result);

    }

}
