package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day2Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/sample1.txt");

        Day2 day = new Day2();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(8, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/input1.txt");

        Day2 day = new Day2();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(3099, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/sample1.txt");

        Day2 day = new Day2();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(2286, result);
    }

    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/input1.txt");

        Day2 day = new Day2();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(72970, result);

    }

}
