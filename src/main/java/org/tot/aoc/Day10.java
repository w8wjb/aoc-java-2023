package org.tot.aoc;

import org.apache.commons.lang3.ArrayUtils;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.*;

public class Day10 {

    private static class PipeMap extends StringGrid {

        public PipeMap(List<String> rows) {
            super(rows);
        }

        public Point findStart() {

            for (int y = 0; y < grid.length; y++) {
                var row = grid[y];

                int x = ArrayUtils.indexOf(row, 'S');
                if (x > -1) {
                    return new Point(x, y);
                }
            }

            throw new IllegalStateException("No start point in grid");
        }

        public Point findExit(Point p, Set<Point> visited) {
            List<Vector> availableDirections = listAvailableDirections(p);
            for (var dir : availableDirections) {
                var dest = p.add(dir);
                if (!visited.contains(dest)) {
                    return dest;
                }
            }
            return null;
        }

        private final List<Vector> checkStart = List.of(Vector.N, Vector.E, Vector.S, Vector.W);

        public List<Vector> listAvailableDirections(Point p) {

            char pipe = get(p);

            switch (pipe) {

                case '|':
                    return List.of(Vector.N, Vector.S);
                case '-':
                    return List.of(Vector.E, Vector.W);
                case 'L':
                    return List.of(Vector.N, Vector.E);
                case 'J':
                    return List.of(Vector.N, Vector.W);
                case '7':
                    return List.of(Vector.S, Vector.W);
                case 'F':
                    return List.of(Vector.S, Vector.E);
                case 'S':
                    List<Vector> available = new ArrayList<>();
                    for (var checkVector : checkStart) {
                        Point neighbor = p.add(checkVector);
                        List<Vector> neighborDirs = listAvailableDirections(neighbor);
                        if (neighborDirs.contains(checkVector.inverted())) {
                            available.add(checkVector);
                        }
                    }
                    return available;

                default:
                    return Collections.emptyList();

            }

        }

    }

    private long walkLoop(PipeMap map, Set<Point> visited) {

        var start = map.findStart();

        visited.add(start);

        List<Vector> availDirs = map.listAvailableDirections(start);
        assert availDirs.size() == 2;

        // Pick one to travel
        Point current = start.add(availDirs.get(0));

        int steps = 1;
        while (true) {
            visited.add(current);
            Point next = map.findExit(current, visited);
            if (next == null) {
                break;
            }
            current = next;
            steps++;
        }

        return steps;
    }

    public long solvePuzzle1(List<String> input) {
        var map = new PipeMap(input);
        Set<Point> visited = new HashSet<>();
        return (walkLoop(map, visited) + 1) / 2;
    }

    public long solvePuzzle2(List<String> input) {

        var map = new PipeMap(input);
        Set<Point> visited = new HashSet<>();
        walkLoop(map, visited);

        int containedPoints = 0;
        for (long y = map.minY; y <= map.maxY; y++) {

            int pathCount = 0;

            char lastCorner = '.';

            for (long x = map.minX; x <= map.maxX; x++) {
                Point p = new Point(x, y);
                char c = map.get(p);

                if (visited.contains(p)) {

                    switch (c) {
                        case 'S':
                        case 'F':
                        case 'L':
                            pathCount++;
                            lastCorner = c;
                            break;
                        case 'J':
                            if (lastCorner == 'F') {
                                lastCorner = c;
                            } else {
                                pathCount++;
                                lastCorner = '.';
                            }
                            break;
                        case '7':
                            if (lastCorner == 'L') {
                                lastCorner = c;
                            } else {
                                pathCount++;
                                lastCorner = '.';
                            }
                            break;
                        default:
                            break;
                    }

                    if (c != '-') {
                        pathCount++;
                    }

                } else {
                    if (pathCount % 2 == 1) {
                        containedPoints++;
                    }
                }
            }
        }

        return containedPoints;

    }

}
