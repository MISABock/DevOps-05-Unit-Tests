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
            String customDelimiter = numbers.substring(2, delimiterIndex);
            delimiter = Pattern.quote(customDelimiter);
            numbers = numbers.substring(delimiterIndex + 1);
        }

        String[] parts = numbers.split(delimiter);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String part : parts) {
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
