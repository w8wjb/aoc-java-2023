package org.tot.aoc.grid;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Wrapper class to make a list of Strings appear as a grid
 */
public class StringGrid implements Grid<Character> {


    protected final char[][] grid;
    public final int minX = 0;
    public final int minY = 0;
    public final int maxX;
    public final int maxY;

    public StringGrid(List<String> rows) {


        int rowCount = rows.size();
        int colCount = rows.get(0).length();
        this.maxY = rowCount - 1;
        this.maxX = colCount - 1;

        this.grid = new char[rowCount][colCount];

        for (int y = 0; y < rowCount; y++) {
            this.grid[y] = rows.get(y).toCharArray();
        }

    }

    /**
     * This is 'safe' coordinate access.
     * If the target point lies outside the bounds of the 2D array, it will return the 'empty' character, '.'
     *
     * @param p target point
     * @return character at the grid point
     */
    public Character get(Point p) {
        // Bounds checking
        if (p.x < minX || p.y < minY || p.x > maxX || p.y > maxY) {
            return '.';
        }
        return grid[(int) p.y][(int) p.x];
    }

    @Override
    public Character put(Point p, Character c) {
        if (p.x < minX || p.y < minY || p.x > maxX || p.y > maxY) {
            throw new IndexOutOfBoundsException();
        }
        int x = (int) p.x;
        int y = (int) p.y;

        char old = this.grid[y][x];
        this.grid[y][x] = c;
        return old;
    }

    public String row(int index) {
        if (index < minY || index > maxY) {
            return null;
        }
        return String.valueOf(grid[index]);
    }

    public String col(int index) {
        if (index < minX || index > maxX) {
            return null;
        }
        var col = new StringBuilder();
        for (int y = minY; y <= maxY; y++) {
            col.append(grid[y][index]);
        }

        return col.toString();
    }

    class RowWiseIterator implements Iterator<Location<Character>> {

        int currY = 0;
        int currX = 0;

        @Override
        public boolean hasNext() {
            return currX >= minX
                    && currX <= maxX
                    && currY >= minY
                    && currY <= maxY;
        }

        @Override
        public Location<Character> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Point p = new Point(currX, currY);
            char c = get(p);
            currX++;
            if (currX > maxX) {
                currX = 0;
                currY++;
            }
            return new Location<>(p, c);
        }
    }

    @Override
    public Iterator<Location<Character>> iterator() {
        return new RowWiseIterator();
    }
}
