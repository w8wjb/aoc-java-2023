package org.tot.aoc;

import org.apache.commons.lang3.Range;

import java.util.*;

public class Day19 {

    private static class Rule {

        final char field;
        final char comparison;
        final long threshold;
        final String dest;

        Rule(char field, char comparison, int threshold, String dest) {
            this.field = field;
            this.comparison = comparison;
            this.threshold = threshold;
            this.dest = dest;
        }

        public String eval(Part part) {
            int val = part.get(field);
            switch (comparison) {
                case '<':
                    if (val < threshold) {
                        return dest;
                    }
                    break;
                case '>':
                    if (val > threshold) {
                        return dest;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + comparison);
            }

            return null;
        }

        public Region slice(Region fromRegion) {

            Range<Long> range = fromRegion.get(field);
            if (range.contains(threshold)) {
                Region piece = new Region(fromRegion.dimensions, this.dest);
                if (comparison == '<') {
                    fromRegion.put(field, Range.between(threshold, range.getMaximum()));
                    piece.put(field, Range.between(range.getMinimum(), threshold - 1));
                } else {
                    piece.put(field, Range.between(threshold + 1, range.getMaximum()));
                    fromRegion.put(field, Range.between(range.getMinimum(), threshold));
                }
                return piece;
            }
            return null;
        }

    }

    private class Workflow {

        public String name = "";

        public final List<Rule> rules = new ArrayList<>();

        public String defaultDest;


        public Workflow(String line) {

            char field = ' ';
            char comparison = ' ';
            int threshold = 0;
            String dest = "";

            StringBuilder buffer = new StringBuilder();
            for (char c : line.toCharArray()) {

                switch (c) {
                    case '{':
                        name = buffer.toString();
                        buffer.setLength(0);
                        break;

                    case '<':
                    case '>':
                        field = buffer.charAt(0);
                        buffer.setLength(0);
                        comparison = c;
                        break;

                    case ':':
                        threshold = Integer.parseInt(buffer.toString());
                        buffer.setLength(0);
                        break;

                    case ',':
                        dest = buffer.toString();
                        buffer.setLength(0);
                        rules.add(new Rule(field, comparison, threshold, dest));
                        break;

                    case '}':
                        defaultDest = buffer.toString();
                        break;

                    default:
                        buffer.append(c);
                }

            }

        }

        public String run(Part part) {

            String dest = defaultDest;

            for (Rule rule : rules) {
                String ruleDest = rule.eval(part);
                if (ruleDest != null) {
                    dest = ruleDest;
                    break;
                }
            }
            if ("R".equals(dest) || "A".equals(dest)) {
                return dest;
            }

            var nextWorkflow = workflows.get(dest);
            return nextWorkflow.run(part);
        }

        public List<Region> run(Region region) {

            List<Region> result = new ArrayList<>();
            result.add(region);

            for (Rule rule : rules) {
                Region sliced = rule.slice(region);
                if (sliced != null) {
                    result.add(sliced);
                }
            }
            region.nextWorkflow = defaultDest;
            return result;
        }

    }

    private static class Part {

        public final int x;
        public final int m;
        public final int a;
        public final int s;

        public Part(String line) {
            String[] nums = line.split("\\D+");
            this.x = Integer.parseInt(nums[1]);
            this.m = Integer.parseInt(nums[2]);
            this.a = Integer.parseInt(nums[3]);
            this.s = Integer.parseInt(nums[4]);
        }

        public int get(char key) {
            switch (key) {
                case 'x':
                    return x;
                case 'm':
                    return m;
                case 'a':
                    return a;
                default:
                    return s;
            }
        }

        public int combinedRating() {
            return x + m + a + s;
        }

        @Override
        public String toString() {
            return String.format("{x=%d,m=%d,a=%d,s=%d}", x, m, a, s);
        }
    }

    private final Map<String, Workflow> workflows = new HashMap<>();
    private final List<Part> parts = new ArrayList<>();

    private void parse(List<String> input) {

        for (String line : input) {

            if (line.startsWith("{")) {
                var part = new Part(line);
                parts.add(part);

            } else if (!line.isBlank()) {
                var workflow = new Workflow(line);
                workflows.put(workflow.name, workflow);
            }

        }

    }

    public long solvePuzzle1(List<String> input) {

        parse(input);

        long sumRatings = 0;
        for (Part part : parts) {

            Workflow start = workflows.get("in");
            String dest = start.run(part);
            if ("A".equals(dest)) {
                sumRatings += part.combinedRating();
            }


        }

        return sumRatings;
    }


    private static class Region {

        public final List<Range<Long>> dimensions;
        private String nextWorkflow;

        public Region(List<Range<Long>> dimensions, String nextWorkflow) {
            this.dimensions = new ArrayList<>(dimensions);
            this.nextWorkflow = nextWorkflow;
        }

        public Region() {
            this(new ArrayList<>(), "in");
            for (int i = 0; i < 4; i++) {
                dimensions.add(Range.between(1L, 4000L));
            }
        }

        private static int key2index(char key) {
            switch (key) {
                case 'x':
                    return 0;
                case 'm':
                    return 1;
                case 'a':
                    return 2;
                case 's':
                    return 3;
                default:
                    throw new IllegalStateException("Unexpected value: " + key);
            }
        }

        public Range<Long> get(char key) {
            return dimensions.get(key2index(key));
        }


        public void put(char key, Range<Long> newRange) {
            dimensions.set(key2index(key), newRange);
        }


        public long combinations() {
            long combinations = 1;
            for (Range<Long> r : dimensions) {
                long delta = r.getMaximum() - r.getMinimum() + 1;
                combinations *= delta;
            }
            return combinations;
        }

    }

    public long solvePuzzle2(List<String> input) {

        parse(input);

        Queue<Region> regionQueue = new ArrayDeque<>();
        regionQueue.add(new Region());

        long sumCombinations = 0;

        while (!regionQueue.isEmpty()) {
            Region region = regionQueue.poll();

            if ("R".equals(region.nextWorkflow)) {
                continue;
            }

            if ("A".equals(region.nextWorkflow)) {
                sumCombinations += region.combinations();
                continue;
            }

            Workflow workflow = workflows.get(region.nextWorkflow);
            List<Region> results = workflow.run(region);
            regionQueue.addAll(results);

        }

        return sumCombinations;

    }

}
