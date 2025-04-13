package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            String delimiterPart = numbers.substring(2, delimiterIndex);
            numbers = numbers.substring(delimiterIndex + 1);

            List<String> delimiters = new ArrayList<>();

            if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
                // Mehrere (auch lange) Delimiter
                int i = 0;
                while (i < delimiterPart.length()) {
                    int start = delimiterPart.indexOf("[", i);
                    int end = delimiterPart.indexOf("]", start);
                    if (start == -1 || end == -1) break;
                    String del = delimiterPart.substring(start + 1, end);
                    delimiters.add(Pattern.quote(del));
                    i = end + 1;
                }
            } else {
                // Ein einzelner kurzer Delimiter
                delimiters.add(Pattern.quote(delimiterPart));
            }

            delimiter = String.join("|", delimiters);
        }

        String[] parts = numbers.split(delimiter);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String part : parts) {
            if (part.isEmpty()) continue; // Sicherheit gegen leere Teile
            int number = Integer.parseInt(part);
            if (number < 0) {
                negatives.add(number);
            } else if (number <= 1000) {
                sum += number;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " +
                    String.join(",", negatives.stream().map(String::valueOf).toList()));
        }

        return sum;
    }
}
