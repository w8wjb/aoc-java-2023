package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day12Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample1.txt");

        Day12 day = new Day12();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(374, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/input.txt");

        Day12 day = new Day12();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(9563821, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample1.txt");

        Day12 day = new Day12();

        long result = day.solvePuzzle2(lines, 10);

        Assert.assertEquals(1030, result);
    }


    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/input.txt");

        Day12 day = new Day12();

        long result = day.solvePuzzle2(lines, 1000000);

        Assert.assertEquals(827009909817L, result);

    }

}
