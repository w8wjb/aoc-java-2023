package org.tot.aoc;

import java.util.*;
import java.util.stream.Collectors;

public class Day9 {

    private boolean isAllZeros(List<Long> sequence) {
        for (long num : sequence) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    private long predict(List<Long> sequence, boolean end) {

        if (isAllZeros(sequence)) {
            return 0;
        }

        List<Long> subsequence = new ArrayList<>();
        for (int i = 0; i < sequence.size() - 1; i++) {
            long diff = sequence.get(i + 1) - sequence.get(i);
            subsequence.add(diff);
        }

        long next = predict(subsequence, end);
        if (end) {
            long myLast = sequence.get(sequence.size() - 1);
            return myLast + next;
        } else {
            long myFirst = sequence.get(0);
            return myFirst - next;
        }
    }

    public long solvePuzzle1(List<String> input) {

        long sumPrediction = 0;
        for (var line : input) {
            List<Long> history = Arrays
                    .stream(line.split(" "))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            long prediction = predict(history, true);
            sumPrediction += prediction;
        }

        return sumPrediction;
    }

    public long solvePuzzle2(List<String> input) {

        long sumPrediction = 0;
        for (var line : input) {
            List<Long> history = Arrays
                    .stream(line.split(" "))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            long prediction = predict(history, false);
            sumPrediction += prediction;
        }

        return sumPrediction;

    }

}
