package org.tot.aoc.grid;

import java.util.List;

/**
 * Wrapper class to make a list of Strings appear as a grid
 */
public class StringGrid {


    protected final List<String> rows;
    public final long minX = 0;
    public final long minY = 0;
    public final long maxX;
    public final long maxY;

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
        return rows.get((int) p.y).charAt((int) p.x);
    }

    public String row(int index) {
        if (index < minY || index > maxY) {
            return null;
        }
        return rows.get(index);
    }
}
