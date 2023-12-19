package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day19Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day19/sample.txt");

        Day19 day = new Day19();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(19114, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day19/input.txt");

        Day19 day = new Day19();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(263678, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day19/sample.txt");

        Day19 day = new Day19();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(167409079868000L, result);
    }


    @Test
    public void testSolution2() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day19/input.txt");

        Day19 day = new Day19();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(125455345557345L, result);

    }

}
