package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiterRegex = "[,\n]";
        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf("\n");
            String delimiterPart = numbers.substring(2, newlineIndex);
            numbers = numbers.substring(newlineIndex + 1);

            List<String> delimiters = new ArrayList<>();

            if (delimiterPart.startsWith("[")) {
                // Tolerant gegenüber fehlenden schließenden Klammern
                int i = 0;
                while (i < delimiterPart.length()) {
                    if (delimiterPart.charAt(i) == '[') {
                        int start = i + 1;
                        int nextBracket = delimiterPart.indexOf('[', start);
                        int end = delimiterPart.indexOf(']', start);

                        if (end == -1 || (nextBracket != -1 && nextBracket < end)) {
                            // Kein schließendes ] oder ein neuer [ davor
                            end = nextBracket == -1 ? delimiterPart.length() : nextBracket;
                        }

                        String del = delimiterPart.substring(start, end);
                        if (!del.isEmpty()) {
                            delimiters.add(Pattern.quote(del));
                        }
                        i = end;
                    } else {
                        i++;
                    }
                }
            } else {
                delimiters.add(Pattern.quote(delimiterPart));
            }

            delimiterRegex = String.join("|", delimiters);
        }

        String[] parts = numbers.split(delimiterRegex);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String part : parts) {
            if (part.isEmpty()) continue;
            try {
                int number = Integer.parseInt(part);
                if (number < 0) {
                    negatives.add(number);
                } else if (number <= 1000) {
                    sum += number;
                }
            } catch (NumberFormatException e) {
                // Ignoriere unparsebare Teile
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " +
                String.join(",", negatives.stream().map(String::valueOf).toList()));
        }

        return sum;
    }
}
