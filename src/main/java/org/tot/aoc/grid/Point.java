package org.tot.aoc.grid;

/**
 * I made my own Point class, because I didn't really like the built-in options
 */
public class Point extends Vector {

    public Point(int x, int y) {
        super(x, y);
    }


    public Point add(Vector other) {
        return add(other.x, other.y);
    }

    public Point add(int dx, int dy) {
        return new Point(this.x + dx, this.y + dy);
    }


    public Point subtract(Vector other) {
        return subtract(other.x, other.y);
    }

    public Point subtract(int dx, int dy) {
        return new Point(this.x - dx, this.y - dy);
    }
}
