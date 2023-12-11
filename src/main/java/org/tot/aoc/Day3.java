package org.tot.aoc;

import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.*;

public class Day3 {



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

    public int solvePuzzle1(List<String> input) {

        int sumPartNumbers = 0;

        var grid = new StringGrid(input);

        var numberBuffer = new StringBuilder();
        boolean isPartNum = false;

        // Iterate through each point in the grid
        // I'm using a trick/feature of the StringGrid class here to handle numbers that are sitting on the very right edge
        // Technically maxY/maxX + 1 is beyond the grid bounds, but the grid just returns a border of empty cells
        for (long y = grid.minY; y <= grid.maxY + 1; y++) {
            for (long x = grid.minX; x <= grid.maxX + 1; x++) {

                var p = new Point(x, y);
                char c = grid.get(p);

                if (Character.isDigit(c)) {
                    numberBuffer.append(c);

                    // Look at all the adjacent cells
                    for (var move : Vector.ADJACENT_MOVES) {
                        var neighbor = p.add(move);
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
     * <p>
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
        for (long y = grid.minY; y <= grid.maxY + 1; y++) {
            for (long x = grid.minX; x <= grid.maxX + 1; x++) {

                var p = new Point(x, y);
                char c = grid.get(p);

                if (Character.isDigit(c)) {
                    numberBuffer.append(c);

                    // Look at all the adjacent cells
                    for (var move : Vector.ADJACENT_MOVES) {
                        var neighbor = p.add(move);
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
