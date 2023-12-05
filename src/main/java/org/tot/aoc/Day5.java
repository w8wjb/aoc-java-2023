package org.tot.aoc;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

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

        long sourceStart;
        long destStart;
        long length;

        public MapEntry(long destStart, long sourceStart, long length) {
            this.destStart = destStart;
            this.sourceStart = sourceStart;
            this.length = length;
        }

        public boolean containsSource(long value) {
            return value >= sourceStart && value < (sourceStart + length);
        }

        public long convertSource(long value) {
            long offset = value - sourceStart;
            return destStart + offset;
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

        long lowestLocation = Long.MAX_VALUE;

        List<Pair<Long, Long>> pairs = new ArrayList<>();

        for (int i = 0; i < seeds.size(); i += 2) {

            long start = seeds.get(i);
            long len = seeds.get(i + 1);
            long end = start + len;
            System.out.println(String.format("Testing %d seede", len));

            for (long seed = start; seed < end; seed++) {

                long soil = followMap(seedToSoil, seed);
                long fertilizer = followMap(soilToFertilizer, soil);
                long water = followMap(fertilizerToWater, fertilizer);
                long light = followMap(waterToLight, water);
                long temperature = followMap(lightToTemperature, light);
                long humidity = followMap(temperatureToHumidity, temperature);
                long location = followMap(humidityToLocation, humidity);

                lowestLocation = Math.min(lowestLocation, location);

            }

        }


        return lowestLocation;

    }


}
