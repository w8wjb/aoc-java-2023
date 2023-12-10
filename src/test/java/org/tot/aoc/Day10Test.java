package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day10Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/sample1.txt");

        Day10 day = new Day10();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(8, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/input.txt");

        Day10 day = new Day10();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(6823, result);
    }

    @Test
    public void testSample2_1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/sample2.1.txt");

        Day10 day = new Day10();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(4, result);
    }

    @Test
    public void testSample2_2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/sample2.2.txt");

        Day10 day = new Day10();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(8, result);
    }


    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/input.txt");

        Day10 day = new Day10();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(415, result);

    }

}
