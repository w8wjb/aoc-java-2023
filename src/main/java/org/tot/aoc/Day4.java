package org.tot.aoc;

import java.util.*;

public class Day4 {

    private static class Card {

        /** Number of matches on the scratchoff card */
        int matches;
        /** Numbers of copies of this card */
        int copies = 1;

        public Card(String input) {

            // Use a Scanner to parse the input, treating spaces and colons as delimiters
            // This is in a try-with-resources block so that the scanner is closed properly at the end
            try (var scanner = new Scanner(input).useDelimiter("[\\s:]+")) {
                scanner.next(); // This eats the 'Card' prefix
                scanner.nextInt(); // This eats the card id, which actually isn't used for anything

                // Put all of the winning numbers into a Set
                Set<Integer> winningNumbers = new HashSet<>();
                while (scanner.hasNextInt()) {
                    winningNumbers.add(scanner.nextInt());
                }

                // I didn't put the pipe | into the delimiters above so that the hasNextInt() above
                // would stop when it hit the pipe, signaling the end of the first list of numbers
                scanner.next(); // Eat the pipe character

                // Put all of the scratched off numbers into a set
                List<Integer> scratchedOff = new ArrayList<>();
                while (scanner.hasNextInt()) {
                    scratchedOff.add(scanner.nextInt());
                }

                // This is Java's version of the 'intersect' operation on sets.
                // Only the numbers that are common to both sets will remain
                scratchedOff.retainAll(winningNumbers);
                matches = scratchedOff.size();
            }
        }

        public int getPoints() {
            // The instructions say that 1 match equals 1 point.
            // But 2^1 is 2, so we subtract 1, such that 2^0 = 1
            // This is also using a trick to get zero:
            // 2^-1 is actually 0.5, but once truncated from a double to an int, it becomes 0
            return (int) Math.pow(2, matches - 1.0);
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

        // Parse the input into an array of Cards.
        var deck = input.stream()
                .map(Card::new)
                .toArray(Card[]::new);

        // Overall number of cards, including both original and copies
        int cardCount = 0;

        // Work through the list one at a time, whre 'c' is the index of the current card
        for (int c = 0; c < deck.length; c++) {
            Card card = deck[c];

            // Start from next card after this one
            int start = c + 1;
            // End at the next card plus the number of matches on the current card
            // This bounds checking is technically unnecessary because the input was specially crafted
            // to avoid running off the end, but this is good practice anyway
            int end = Math.min(start + card.matches, deck.length);

            // Loop through the cards down the line
            for (int j = start; j < end; j++) {
                Card target = deck[j];
                // Duplicate the target cards the same amount as the number of copies of the current card
                target.copies += card.copies;
            }

            // Once we've processed a card, we can add it, because we don't look behind us.
            cardCount += card.copies;
        }

        return cardCount;

    }


}
