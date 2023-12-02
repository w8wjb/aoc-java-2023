package org.tot.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day1 {

    public int solvePuzzle1(List<String> input) {

        int calibrationSum = 0;

        for (String line : input) {

            // Turn the String 'line' into an array of characters.
            var chars = line.toCharArray();

            // This is a String onto which we're going to concatenate the characters we find
            var calVal = "";

            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                // These are ASCII characters. We want the ones between '0' and '9' because those are numeric digits
                if (c >= '0' && c <= '9') {
                    calVal += c;
                    // Once we find the first digit, we stop looking further
                    break;
                }
            }

            // Here we're looping *backwards* through the line. This is so that we can stop at the first digit found
            for (int i = chars.length - 1; i >= 0; i--) {
                char c = chars[i];
                // Again, we want the ones between '0' and '9' because those are numeric digits
                if (c >= '0' && c <= '9') {
                    calVal += c;
                    // Stop when we found one
                    break;
                }
            }

            // Ask Java to parse the two characters into a real int.
            // Finally, we add this int to the running sum.
            calibrationSum += Integer.parseInt(calVal);
        }

        return calibrationSum;
    }

    public int solvePuzzle2(List<String> input) {

        // Create a list of numbers as Strings
        var numbers = new ArrayList<String>();
        for (int i = 0; i <= 9; i++) {
            numbers.add(String.valueOf(i));
        }

        // Create a list of numbers as words. This is a separate list because we'll use it later to turn these back into digits
        var numberWords = List.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        // Combine these two lists
        var numberSymbols = new ArrayList<String>();
        numberSymbols.addAll(numbers);
        numberSymbols.addAll(numberWords);

        // Overall calibration sum
        int calibrationSum = 0;

        // Loop throght each line in the input
        for (String line : input) {


            // Initializae a few marker variables

            // The index of the *first* number that was found in the line
            // It's defaulted to the end of the line because each the first number found will be before this
            int firstIndex = line.length();
            // This will end up being the first actual number/symbol we found
            String firstSymbol = "";
            // The index of the *last* that was found in the line
            int lastIndex = -1;
            // This will end up being the last actual number/symbol we found
            String lastSymbol = "";

            // Go through each of the possible symbols.
            // We have to do all of them each time because of lines like 'xtwone3four'.
            // In this case, if we stopped at 'one', we might miss 'two', which occurs sooner
            for (String symbol : numberSymbols) {
                // Find the first index of the symbol
                int i = line.indexOf(symbol);
                // If we didn't find anything, the index will be -1
                if (i > -1 && i < firstIndex) {
                    // If we found something that occurs *earlier* in the line than the previous "record-holder",
                    // we make this the new first symol
                    firstIndex = i;
                    firstSymbol = symbol;
                }

                // Do the same thin as before, except now we want the index of the symbol that is the greatest,
                // or furthest down the line
                int j = line.lastIndexOf(symbol);
                if (j > -1 && j > lastIndex) {
                    lastIndex = j;
                    lastSymbol = symbol;
                }

            }

            // Once we've gotten here, we have the first and last "numbers", but they could still be words.
            // We need to converts them to digits. i.e. "one" -> 1, "two" -> 2, etc.

            // This makes use of the fact that the position of the word in the 'numberWords' list is the actual digit
            // we need, so "zero" is at index 0 and "nine" is at index 9.
            int wordIndex = numberWords.indexOf(firstSymbol);
            if (wordIndex > -1) {
                firstSymbol = String.valueOf(wordIndex);
            }

            // Same thing, but for the last symbol
            wordIndex = numberWords.indexOf(lastSymbol);
            if (wordIndex > -1) {
                lastSymbol = String.valueOf(wordIndex);
            }

            // Now that we have two Strings that contain two digits, we can concatenate them together
            // and have Java parse this into a real int.
            // Finally, we add this int to the running sum.
            calibrationSum += Integer.parseInt(firstSymbol + lastSymbol);
        }

        return calibrationSum;
    }

}
