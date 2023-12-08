package org.tot.aoc;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.regex.Pattern;

public class Day8 {

    private static final Pattern regexEdges = Pattern.compile("^(\\w+)\\s+=\\s+\\((\\w+),\\s+(\\w+)\\)");

    public int solvePuzzle1(List<String> input) {


        var path = input.remove(0);
        input.remove(0);

        Map<String, Pair<String, String>> nodes = new HashMap<>();
        for (var line : input) {
            var matcher = regexEdges.matcher(line);
            if (matcher.find()) {
                var key = matcher.group(1);
                var left = matcher.group(2);
                var right = matcher.group(3);
                nodes.put(key, Pair.of(left, right));
            }
        }

        var current = "AAA";

        int steps = 0;
        while (!"ZZZ".equals(current)) {
            var fork = nodes.get(current);

            var choose = path.charAt(steps % path.length());
            if (choose == 'L') {
                current = fork.getLeft();
            } else {
                current = fork.getRight();
            }
            steps++;
        }


        return steps;
    }

    public long solvePuzzle2(List<String> input) {

        var path = input.remove(0);
        input.remove(0);

        Map<String, Pair<String, String>> nodes = new HashMap<>();
        var startingPositions = new ArrayList<String>();

        for (var line : input) {
            var matcher = regexEdges.matcher(line);
            if (matcher.find()) {
                var key = matcher.group(1);
                var left = matcher.group(2);
                var right = matcher.group(3);
                nodes.put(key, Pair.of(left, right));

                if (key.endsWith("A")) {
                    startingPositions.add(key);
                }

            }
        }

        long[] allSteps = new long[startingPositions.size()];
        for (int i = 0; i < startingPositions.size(); i++) {

            var current = startingPositions.get(i);
            int steps = 0;
            while (!current.endsWith("Z")) {
                var fork = nodes.get(current);

                var choose = path.charAt(steps % path.length());
                if (choose == 'L') {
                    current = fork.getLeft();
                } else {
                    current = fork.getRight();
                }

                steps++;
            }

            allSteps[i] = steps;
        }

        return lcm(allSteps);

    }

    private static long lcm(final long[] array) {
        long lcm = 1;
        for (long value : array) {
            lcm = lcm(lcm, value);
        }
        return lcm;
    }

    private static long lcm(final long a, final long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return ((a * b) / gcd(a, b));
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return 0;
        } else if (a < b) {
            return gcd(b, a);
        } else {
            while (b > 0) {
                long tempA = a;
                a = b;
                b = tempA % b;
            }
            return a;
        }
    }


}
