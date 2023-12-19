package org.tot.aoc;

import org.tot.aoc.grid.Grid;
import org.tot.aoc.grid.StringGrid;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    static class TerrainGrid extends StringGrid {

        int oldMirrorIndex = -Integer.MAX_VALUE;
        char oldMirrorDir = ' ';

        public TerrainGrid(List<String> rows) {
            super(rows);
        }

        public boolean rowsEqual(int r1, int r2) {
            String row1 = row(r1);
            String row2 = row(r2);
            if (row1 == null || row2 == null) {
                return false;
            }
            return row1.equals(row2);
        }

        public boolean colsEqual(int c1, int c2) {
            String col1 = col(c1);
            String col2 = col(c2);
            if (col1 == null || col2 == null) {
                return false;
            }
            return col1.equals(col2);
        }

        private boolean isRowMirror(int r1) {
            int r2 = r1 + 1;
            if (rowsEqual(r1, r2)) {
                int horizon = Math.min(r1 - minY, maxY - r2);

                for (int offset = 1; offset <= horizon; offset++) {
                    int rowUp = r1 - offset;
                    int rowDown = r2 + offset;
                    if (!rowsEqual(rowUp, rowDown)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        private boolean isColMirror(int c1) {
            int c2 = c1 + 1;
            if (colsEqual(c1, c2)) {
                int horizon = Math.min(c1 - minX, maxX - c2);

                for (int offset = 1; offset <= horizon; offset++) {
                    int colLeft = c1 - offset;
                    int colRight = c2 + offset;
                    if (!colsEqual(colLeft, colRight)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

    }

    private List<TerrainGrid> separateGrids(List<String> input) {

        List<TerrainGrid> grids = new ArrayList<>();
        List<String> rows = new ArrayList<>();
        for (String row : input) {
            if (row.isBlank()) {
                grids.add(new TerrainGrid(rows));
                rows = new ArrayList<>();
                continue;
            }
            rows.add(row);
        }

        grids.add(new TerrainGrid(rows));
        return grids;
    }


    public long solvePuzzle1(List<String> input) {

        List<TerrainGrid> grids = separateGrids(input);

        int summary = 0;

        for (var grid : grids) {
            summary += findMirrorValue(grid);
        }

        return summary;
    }

    private int findMirrorValue(TerrainGrid grid) {
        for (int c = grid.minX; c <= grid.maxX - 1; c++) {
            if (grid.isColMirror(c)) {
                grid.oldMirrorDir = 'c';
                grid.oldMirrorIndex = c;
                return c + 1;
            }
        }

        for (int r = grid.minY; r <= grid.maxY - 1; r++) {
            if (grid.isRowMirror(r)) {
                grid.oldMirrorDir = 'r';
                grid.oldMirrorIndex = r;
                return (r + 1) * 100;
            }
        }
        throw new IllegalStateException("Should have found a mirror in grid");
    }

    private int findSmudgedMirrorValue(TerrainGrid grid) {

        for (var loc : grid) {
            if (loc.value == '.') {
                grid.put(loc.coordinate, '#');
            } else {
                grid.put(loc.coordinate, '.');
            }

            for (int c = grid.minX; c <= grid.maxX - 1; c++) {
                if (grid.oldMirrorDir == 'c' && c == grid.oldMirrorIndex) {
                    continue;
                }
                if (grid.isColMirror(c)) {
                    return (c + 1);
                }
            }

            for (int r = grid.minY; r <= grid.maxY - 1; r++) {
                if (grid.oldMirrorDir == 'r' && r == grid.oldMirrorIndex) {
                    continue;
                }
                if (grid.isRowMirror(r)) {
                    return ((r + 1) * 100);
                }
            }


            grid.put(loc.coordinate, loc.value);
        }

        throw new IllegalStateException("Should have found a mirror in grid");
    }

    public long solvePuzzle2(List<String> input) {

        List<TerrainGrid> grids = separateGrids(input);

        int summary = 0;

        for (var grid : grids) {
            findMirrorValue(grid);
            summary += findSmudgedMirrorValue(grid);
        }

        return summary;

    }

}
