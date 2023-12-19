package org.tot.aoc.grid;

import java.util.Objects;

public interface Grid<V> extends Iterable<Grid.Location<V>> {

    V get(Point p);

    V put(Point p, V v);

    class Location<V> {

        public final Point coordinate;
        public final V value;

        public Location(Point coordinate, V value) {
            this.coordinate = coordinate;
            this.value = value;
        }

        @Override
        public String toString() {
            return coordinate + " -> " + value;
        }
    }
}
