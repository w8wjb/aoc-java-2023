package org.tot.aoc;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Day3 {

    private static final Point NW = new Point(-1, -1);
    private static final Point N = new Point(0, -1);
    private static final Point NE = new Point(1, -1);
    private static final Point W = new Point(-1, 0);
    private static final Point E = new Point(1, 0);
    private static final Point SW = new Point(-1, 1);
    private static final Point S = new Point(0, 1);
    private static final Point SE = new Point(1, 1);

    private static final Point[] adjacentMoves = {NW, W, SW, NE, E, SE, N, S};

    private static final String symbols;


    static {
        // This creates a list of symbols from all ASCII characters that are neither letters, numbers, or a dot .
        var tmpSymbols = new StringBuilder();
        for (char c = 0; c < 255; c++) {
            if (Character.isLetterOrDigit(c) || c == '.') {
                continue;
            }
            tmpSymbols.append(c);
        }
        symbols = tmpSymbols.toString();
    }

    /**
     * I made my own Point class, because I didn't really like the built-in options
     */
    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point offset(Point delta) {
            return offset(delta.x, delta.y);
        }

        public Point offset(int dx, int dy) {
            return new Point(this.x + dx, this.y + dy);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point that = (Point) obj;
                return this.x == that.x && this.y == that.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + x;
            hash = 31 * hash + y;
            return hash;
        }

        @Override
        public String toString() {
            return String.format("%d,%d", x, y);
        }
    }

    /**
     * Wrapper class to make a list of Strings appear as a grid
     */
    private static class StringGrid {

        private final List<String> rows;
        private final int minX = 0;
        private final int minY = 0;
        private final int maxX;
        private final int maxY;

        public StringGrid(List<String> rows) {
            this.rows = rows;
            this.maxY = rows.size() - 1;
            this.maxX = rows.get(0).length() - 1;
        }

        /**
         * This is 'safe' coordinate access.
         * If the target point lies outside the bounds of the 2D array, it will return the 'empty' character, '.'
         *
         * @param p target point
         * @return character at the grid point
         */
        public char get(Point p) {
            // Bounds checking
            if (p.x < minX || p.y < minY || p.x > maxX || p.y > maxY) {
                return '.';
            }
            return rows.get(p.y).charAt(p.x);
        }
    }

    public int solvePuzzle1(List<String> input) {

        int sumPartNumbers = 0;

        var grid = new StringGrid(input);

        var numberBuffer = new StringBuilder();
        boolean isPartNum = false;

        // Iterate through each point in the grid
        // I'm using a trick/feature of the StringGrid class here to handle numbers that are sitting on the very right edge
        // Technically maxY/maxX + 1 is beyond the grid bounds, but the grid just returns a border of empty cells
        for (int y = grid.minY; y <= grid.maxY + 1; y++) {
            for (int x = grid.minX; x <= grid.maxX + 1; x++) {

                var p = new Point(x, y);
                char c = grid.get(p);

                if (Character.isDigit(c)) {
                    numberBuffer.append(c);

                    // Look at all the adjacent cells
                    for (var move : adjacentMoves) {
                        var neighbor = p.offset(move);
                        char adjacent = grid.get(neighbor);
                        // Check to see if the cell contains a symbol
                        if (symbols.indexOf(adjacent) > -1) {
                            isPartNum = true;
                            break;
                        }
                    }

                } else {
                    // We hit something that isn't a digit, so let's process the part number if we have one waiting
                    if (numberBuffer.length() > 0) {
                        if (isPartNum) {
                            int partNum = Integer.parseInt(numberBuffer.toString());
                            sumPartNumbers += partNum;
                        }

                        // Reset buffer and flag
                        numberBuffer = new StringBuilder();
                        isPartNum = false;
                    }

                }


            }
        }

        return sumPartNumbers;
    }

    /**
     * A structure to hold the association between a part number and one or more adjacent gear symbols
     *
     * This came about when I first tried to store these in a Map of number -> gears.
     * I discovered that there are duplicate part numbers in the grid
     */
    private static final class GearLink {
        int partNumber;
        Set<Point> nearbyGears;

        public GearLink(int partNumber, Set<Point> nearbyGears) {
            this.partNumber = partNumber;
            this.nearbyGears = nearbyGears;
        }

        public boolean contains(Point p) {
            return nearbyGears.contains(p);
        }
    }

    public int solvePuzzle2(List<String> input) {

        var grid = new StringGrid(input);
        var numberBuffer = new StringBuilder();
        Set<Point> nearbyAsterisks = new HashSet<>();
        Set<Point> possibleGears = new LinkedHashSet<>();
        List<GearLink> gearLinks = new ArrayList<>();

        // Phase 1: Seek through the grid and find all part numbers with a nearby gear and record the linkage

        // Iterate through each point in the grid
        // I'm using a trick/feature of the StringGrid class here to handle numbers that are sitting on the very right edge
        // Technically maxY/maxX + 1 is beyond the grid bounds, but the grid just returns a border of empty cells
        for (int y = grid.minY; y <= grid.maxY + 1; y++) {
            for (int x = grid.minX; x <= grid.maxX + 1; x++) {

                var p = new Point(x, y);
                char c = grid.get(p);

                if (Character.isDigit(c)) {
                    numberBuffer.append(c);

                    // Look at all the adjacent cells
                    for (var move : adjacentMoves) {
                        var neighbor = p.offset(move);
                        char adjacent = grid.get(neighbor);

                        // This is a specialized form of part 1, since we only care about asterisks,
                        // not the rest of the symbols
                        if (adjacent == '*') {
                            // Keep track of potential gear candidates
                            possibleGears.add(neighbor);
                            // Collect the symbols for later
                            nearbyAsterisks.add(neighbor);
                        }
                    }

                } else {

                    // We hit something that isn't a digit, so let's process the part number if we have one waiting
                    if (numberBuffer.length() > 0) {
                        if (!nearbyAsterisks.isEmpty()) { // Only record part numbers that have nearby gears
                            int partNum = Integer.parseInt(numberBuffer.toString());
                            gearLinks.add(new GearLink(partNum, nearbyAsterisks));
                        }

                        // Reset buffer and nearby
                        numberBuffer = new StringBuilder();
                        nearbyAsterisks = new HashSet<>();
                    }

                }

            }
        }

        // Phase 2: Look through all the linkages from part numbers to gears and find the gears that appear twice.

        int sumGearRatio = 0;
        // Loop through each location that *might* be a gear.
        for (Point gearLoc : possibleGears) {

            // Now loop through the linkages and find the linked part numbers
            List<Integer> nearbyPartNumbers = new ArrayList<>();
            for (var link : gearLinks) {
                if (link.contains(gearLoc)) {
                    nearbyPartNumbers.add(link.partNumber);
                }
            }

            // Only consider gears with 2 part number links
            if (nearbyPartNumbers.size() == 2) {
                // Calculate gear ratio
                int gearRatio = 1;
                for (int partNum : nearbyPartNumbers) {
                    gearRatio *= partNum;
                }
                sumGearRatio += gearRatio;
            }

        }

        return sumGearRatio;

    }


}
