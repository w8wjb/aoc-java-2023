package org.tot.aoc;

import org.apache.commons.lang3.tuple.Pair;
import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;

import java.util.*;

public class Day12 {

    public List<StringBuilder> distributeSpaces(int spacesLeft, int springIndex, List<Integer> springCounts) {

        if (springIndex >= springCounts.size()) {
            return Collections.emptyList();
        }

        int springCount = springCounts.get(springIndex);
        String springs = "#".repeat(springCount);

        List<StringBuilder> result = new ArrayList<>();
        for (int i = spacesLeft; i >= 0; i--) {
            String spaces = ".".repeat(i);

            List<StringBuilder> downstream = distributeSpaces(spacesLeft - i, springIndex + 1, springCounts);
            if (downstream.isEmpty()) {
                result.add(new StringBuilder(spaces).append(springs));
            } else {
                for (var builder : downstream) {
                    builder.insert(0, springs);
                    builder.insert(0, spaces);
                    result.add(builder);
                }
            }


        }
        return result;
    }

    private boolean matchRecord(String springPattern, String arrangement) {
        return false;
    }

    public long solvePuzzle1(List<String> input) {

        for (String line : input) {

            Scanner scanner = new Scanner(line).useDelimiter("[\\s,]+");
            String damagedRecord = scanner.next();
            List<Integer> springCounts = new ArrayList<>();

            int totalSpringLength = 0;
            while (scanner.hasNextInt()) {
                int springCount = scanner.nextInt();
                totalSpringLength += springCount;
                springCounts.add(springCount);
            }

            int maxSpace = damagedRecord.length() - totalSpringLength - springCounts.size() + 1;

            var results = distributeSpaces(maxSpace, 0, springCounts);
            for (var r : results) {
                System.out.println(r);
            }

        }


        return 0;
    }

    public long solvePuzzle2(List<String> input, long distance) {


        return 0;

    }

}
