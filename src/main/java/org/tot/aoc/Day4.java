package org.tot.aoc;

import java.util.*;

public class Day4 {

    private static class Card {

        final int id;
        final Set<Integer> winningNumbers = new HashSet<>();
        final List<Integer> scratchedOff = new ArrayList<>();
        int copies = 1;

        public Card(String input) {

            var scanner = new Scanner(input).useDelimiter("[\\s:]+");

            scanner.next();
            this.id = scanner.nextInt();

            while (scanner.hasNextInt()) {
                winningNumbers.add(scanner.nextInt());
            }

            scanner.next();

            while (scanner.hasNextInt()) {
                scratchedOff.add(scanner.nextInt());
            }
        }

        public int getMatchCount() {

            int count = 0;
            for (int number : scratchedOff) {
                if (winningNumbers.contains(number)) {
                    count++;
                }
            }

            return count;
        }

        public int getPoints() {

            int points = 0;
            for (int number : scratchedOff) {
                if (winningNumbers.contains(number)) {
                    if (points == 0) {
                        points++;
                    } else {
                        points *= 2;
                    }
                }
            }

            return points;

        }

    }

    public int solvePuzzle1(List<String> input) {

        int sumPoints = 0;

        for (String line : input) {
            var card = new Card(line);
            sumPoints += card.getPoints();
        }

        return sumPoints;
    }

    public int solvePuzzle2(List<String> input) {

        int sumPoints = 0;

        List<Card> deck = new ArrayList<>();

        for (String line : input) {
            var card = new Card(line);
            deck.add(card);
        }

        int deckCount = deck.size();

        for (int c = 0; c < deckCount; c++) {

            Card card = deck.get(c);

            int matches = card.getMatchCount();
            int copies = card.copies;

            int end = Math.min(c + matches + 1, deckCount);
            for (int j = c + 1; j < end; j++) {
                Card target = deck.get(j);
                target.copies += copies;
                //System.out.println(String.format("Adding %d copies to card %s", copies, target.id));
            }


        }

        int cardCount = 0;

        for (var card : deck) {
            cardCount += card.copies;
        }

        return cardCount;

    }


}
