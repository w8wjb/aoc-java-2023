package org.tot.aoc;

import org.apache.commons.lang3.tuple.Pair;
import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;

import java.util.*;

public class Day11 {

    public static <T> List<Pair<T, T>> combinations(List<T> list1) {
        List<Pair<T, T>> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            for (int j = i + 1; j < list1.size(); j++) {
                result.add(Pair.of(list1.get(i), list1.get(j)));
            }
        }
        return result;
    }

    private HashGrid<Character> expandUniverse(List<String> input, long distance) {
        var universe = new HashGrid<Character>();

        Set<Integer> emptyRows = new TreeSet<>();
        Set<Integer> emptyCols = new TreeSet<>();

        for (int y = 0; y < input.size(); y++) {
            emptyRows.add(y);
        }

        for (int x = 0; x < input.get(0).length(); x++) {
            emptyCols.add(x);
        }


        for (int y = 0; y < input.size(); y++) {
            String row = input.get(y);
            for (int x = 0; x < row.length(); x++) {
                char c = row.charAt(x);
                if (c != '.') {
                    universe.put(new Point(x, y), c);
                    emptyCols.remove(x);
                    emptyRows.remove(y);
                }
            }
        }

        HashGrid<Character> expandedUniverse = new HashGrid<>();


        for (Map.Entry<Point, Character> entry : universe.entrySet()) {
            Point p = entry.getKey();
            long dx = 0;
            for (int x : emptyCols) {
                if (x < p.x) {
                    dx += distance;
                }
            }

            long dy = 0;
            for (int y : emptyRows) {
                if (y < p.y) {
                    dy += distance;
                }
            }

            expandedUniverse.put(p.add(dx, dy), entry.getValue());
        }
        return expandedUniverse;
    }

    public long solvePuzzle1(List<String> input) {

        var universe = expandUniverse(input, 1);

        List<Point> galaxies = new ArrayList<>(universe.keySet());
        Collections.sort(galaxies);

        var combinations = combinations(galaxies);


        long sumDistance = 0;
        for (var pair : combinations) {
            long dist = pair.getLeft().chessboardStepDistance(pair.getRight());
            sumDistance += dist;
        }


        return sumDistance;
    }

    public long solvePuzzle2(List<String> input, long distance) {


        var universe = expandUniverse(input, distance - 1);

        List<Point> galaxies = new ArrayList<>(universe.keySet());
        Collections.sort(galaxies);

        var combinations = combinations(galaxies);


        long sumDistance = 0;
        for (var pair : combinations) {
            long dist = pair.getLeft().chessboardStepDistance(pair.getRight());
            sumDistance += dist;
        }


        return sumDistance;

    }

}
