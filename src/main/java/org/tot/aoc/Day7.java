package org.tot.aoc;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class Day7 {

    private enum HandType {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND;
    }

    private static class Hand implements Comparable<Hand> {

        private static String STRENGTHS = "23456789TJQKA";
        private static String STRENGTHS_JOKERS = "J23456789TQKA";

        final String cards;
        final String sort;
        final int bid;
        final HandType type;
        final boolean jokersWild;

        public Hand(String cards, int bid, boolean jokersWild) {
            this.cards = cards;
            this.jokersWild = jokersWild;

            var buffer = new StringBuilder(cards.length());
            for (var c : cards.toCharArray()) {
                buffer.append(sortIndex(c));
            }
            this.sort = buffer.toString();

            this.bid = bid;
            this.type = classify(cards);
        }

        private char sortIndex(char c) {
            return (char) ('A' + ordinal(c));
        }

        private int ordinal(char c) {
            if (jokersWild) {
                return STRENGTHS_JOKERS.indexOf(c);
            }
            return STRENGTHS.indexOf(c);
        }

        private HandType classify(String cards) {

            var labelCounts = new int[91];
            for (char c : cards.toCharArray()) {
                labelCounts[c]++;
            }

            // Pull out the jokers
            int jokers = 0;
            if (jokersWild) {
                jokers = labelCounts['J'];
                labelCounts['J'] = 0;
            }

            Arrays.sort(labelCounts);

            // Add any jokers to the primary count
            int countPrimary = labelCounts[labelCounts.length - 1] + jokers;
            int countSecondary = labelCounts[labelCounts.length - 2];

            if (countPrimary == 5) {
                return HandType.FIVE_OF_A_KIND;
            } else if (countPrimary == 4) {
                return HandType.FOUR_OF_A_KIND;
            } else if (countPrimary == 3 && countSecondary == 2) {
                return HandType.FULL_HOUSE;
            } else if (countPrimary == 3) {
                return HandType.THREE_OF_A_KIND;
            } else if (countPrimary == 2 && countSecondary == 2) {
                return HandType.TWO_PAIR;
            } else if (countPrimary == 2) {
                return HandType.ONE_PAIR;
            } else {
                return HandType.HIGH_CARD;
            }
        }

        @Override
        public String toString() {
            return cards;
        }

        @Override
        public int compareTo(Hand other) {

            if (this.type == other.type) {
                return this.sort.compareTo(other.sort);
            } else {
                return this.type.compareTo(other.type);
            }
        }
    }

    private List<Hand> parseHands(List<String> input, boolean jokersWild) {
        List<Hand> hands = new ArrayList<>();
        for (String line : input) {
            var parts = line.split("\\s+");
            hands.add(new Hand(parts[0], Integer.parseInt(parts[1]), jokersWild));
        }
        return hands;
    }


    public int solvePuzzle1(List<String> input) {

        var hands = parseHands(input, false);
        Collections.sort(hands);

        int totalWinnings = 0;

        for (int i = 0; i < hands.size(); i++) {
            var hand = hands.get(i);
            int n = i + 1;
            int win = hand.bid * n;
            totalWinnings += win;
        }

        return totalWinnings;
    }

    public int solvePuzzle2(List<String> input) {

        var hands = parseHands(input, true);
        Collections.sort(hands);

        for (var hand : hands) {
        }

        int totalWinnings = 0;

        for (int i = 0; i < hands.size(); i++) {
            var hand = hands.get(i);
            int n = i + 1;
            int win = hand.bid * n;
            totalWinnings += win;
        }

        return totalWinnings;

    }


}
