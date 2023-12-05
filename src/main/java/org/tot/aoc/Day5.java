package org.tot.aoc;

import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;

public class Day5 {

    List<Long> seeds = new ArrayList<>();
    List<MapEntry> seedToSoil = new ArrayList<>();
    List<MapEntry> soilToFertilizer = new ArrayList<>();
    List<MapEntry> fertilizerToWater = new ArrayList<>();
    List<MapEntry> waterToLight = new ArrayList<>();
    List<MapEntry> lightToTemperature = new ArrayList<>();
    List<MapEntry> temperatureToHumidity = new ArrayList<>();
    List<MapEntry> humidityToLocation = new ArrayList<>();


    private static class MapEntry {

        final Range<Long> sourceRange;
        final Range<Long> destRange;

        public MapEntry(long destStart, long sourceStart, long length) {
            this.destRange = Range.between(destStart, destStart + length - 1);
            this.sourceRange = Range.between(sourceStart, sourceStart + length - 1);
        }

        public boolean containsSource(long value) {
            return sourceRange.contains(value);
        }

        public long convertSource(long value) {
            long offset = value - sourceRange.getMinimum();
            return destRange.getMinimum() + offset;
        }

    }

    private void parse(List<String> input) {


        List<MapEntry> targetMap = seedToSoil;

        for (String line : input) {

            if (line.isBlank()) {
                continue;

            } else if (line.startsWith("seeds: ")) {

                String[] nums = line.replace("seeds: ", "").split("\\s+");
                for (var n : nums) {
                    this.seeds.add(Long.parseLong(n));
                }

            } else if (line.startsWith("seed-to-soil")) {
                targetMap = seedToSoil;
            } else if (line.startsWith("soil-to-fertilizer")) {
                targetMap = soilToFertilizer;
            } else if (line.startsWith("fertilizer-to-water")) {
                targetMap = fertilizerToWater;
            } else if (line.startsWith("water-to-light")) {
                targetMap = waterToLight;
            } else if (line.startsWith("light-to-temperature")) {
                targetMap = lightToTemperature;
            } else if (line.startsWith("temperature-to-humidity")) {
                targetMap = temperatureToHumidity;
            } else if (line.startsWith("humidity-to-location")) {
                targetMap = humidityToLocation;
            } else {

                String[] nums = line.split("\\s+");
                var entry = new MapEntry(
                        Long.parseLong(nums[0]),
                        Long.parseLong(nums[1]),
                        Long.parseLong(nums[2])
                );
                targetMap.add(entry);

            }

        }


    }

    private long followMap(List<MapEntry> map, long source) {
        for (var entry : map) {
            if (entry.containsSource(source)) {
                return entry.convertSource(source);
            }
        }
        return source;
    }


    public long solvePuzzle1(List<String> input) {

        parse(input);

        long lowestLocation = Long.MAX_VALUE;

        for (long seed : seeds) {

            long soil = followMap(seedToSoil, seed);
            long fertilizer = followMap(soilToFertilizer, soil);
            long water = followMap(fertilizerToWater, fertilizer);
            long light = followMap(waterToLight, water);
            long temperature = followMap(lightToTemperature, light);
            long humidity = followMap(temperatureToHumidity, temperature);
            long location = followMap(humidityToLocation, humidity);

            lowestLocation = Math.min(lowestLocation, location);

        }


        return lowestLocation;
    }

    public long solvePuzzle2(List<String> input) {

        parse(input);

        var transformOrder = List.of(
                seedToSoil,
                soilToFertilizer,
                fertilizerToWater,
                waterToLight,
                lightToTemperature,
                temperatureToHumidity,
                humidityToLocation
        );

        List<Range<Long>> seedRanges = new ArrayList<>();


        // The parsing was set up for individual seeds; but for part 2, convert them to ranges
        for (int i = 0; i < seeds.size(); i += 2) {
            long start = seeds.get(i);
            long len = seeds.get(i + 1);
            long end = start + len - 1; // Inclusive
            var range = Range.between(start, end);
            seedRanges.add(range);
        }

        // Perform the transformations one at a time, seed-to-soil, soil-to-fertilizer, etc
        for (var transform : transformOrder) {

            // Process each group of seeds
            // I'm using an index here and a dynamic call to size() because the list may grow
            for (int i = 0; i < seedRanges.size(); i++) {
                var seedRange = seedRanges.get(i);

                // Check each transformation to see if it applies
                for (var entry : transform) {
                    var sourceRange = entry.sourceRange;

                    // Check to see if the left seed bound falls withing the transform bounds
                    if (!sourceRange.contains(seedRange.getMinimum())) {
                        // @ [...]
                        continue;
                    }

                    // Check to see if the right seed bound is completely contained
                    if (!sourceRange.containsRange(seedRange)) {
                        // [,,,{;;;]...}

                        // If it extends beyond, we'll have to split it up
                        // First, truncate the range
                        var smallerRange = seedRange.intersectionWith(sourceRange);
                        // And update it in the list. Ranges are immutable, so it has to be replaced
                        seedRanges.set(i, smallerRange);

                        // Next, put the part we chopped off back into the list so that we'll process it later
                        var remainderRange = Range.between(smallerRange.getMaximum() + 1, seedRange.getMaximum());
                        seedRanges.add(remainderRange);


                        seedRange = smallerRange;

                    }

                    // Now we need to shift the seed range based on the delta between source and dest
                    long offset = entry.destRange.getMinimum() - entry.sourceRange.getMinimum();
                    seedRange = Range.between(
                            seedRange.getMinimum() + offset,
                            seedRange.getMaximum() + offset
                    );

                    // And replace the element in the list
                    seedRanges.set(i, seedRange);

                    // We found a transform that applies to this seed group; this group is done
                    break;
                }

            }

        }

        long minLocation = Long.MAX_VALUE;

        for (var r : seedRanges) {
            minLocation = Math.min(r.getMinimum(), minLocation);
        }

        return minLocation;

    }


}
