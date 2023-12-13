package org.tot.aoc;

import org.tot.aoc.grid.StringGrid;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    static class TerrainGrid extends StringGrid {

        public TerrainGrid(List<String> rows) {
            super(rows);
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


        return 0;
    }

    public long solvePuzzle2(List<String> input, long distance) {


        return 0;

    }

}
