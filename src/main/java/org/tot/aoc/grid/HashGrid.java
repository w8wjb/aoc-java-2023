package org.tot.aoc.grid;

import java.util.*;

public class HashGrid<V> extends HashMap<Point, V> implements Grid<V> {

    public long minX = 0;
    public long minY = 0;
    public long maxX = 0;
    public long maxY = 0;

    public HashGrid() {

    }

    public HashGrid(Map<Point, V> points) {
        this.putAll(points);
    }

    @Override
    public V put(Point p, V v) {
        minX = Math.min(p.x, minX);
        maxX = Math.max(p.x, maxX);
        minY = Math.min(p.y, minY);
        maxY = Math.max(p.y, maxY);
        return super.put(p, v);
    }

    @Override
    public V get(Point p) {
        return super.get(p);
    }


    public void print() {
        for (long y = minY; y <= maxY; y++) {
            for (long x = minX; x <= maxX; x++) {
                Point p = new Point(x, y);
                V value = get(p);
                if (value == null) {
                    System.out.print(".");
                } else {
                    System.out.print(value);
                }
            }
            System.out.println();
        }

    }

    class RowWiseIterator implements Iterator<Location<V>> {

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
        public Location<V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Point p = new Point(currX, currY);
            V value = get(p);
            currX++;
            if (currX > maxX) {
                currX = 0;
                currY++;
            }
            return new Location<>(p, value);
        }
    }

    @Override
    public Iterator<Location<V>> iterator() {
        return new RowWiseIterator();
    }
}
