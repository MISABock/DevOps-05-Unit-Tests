// StringCalculator.java
package org.example;

public class StringCalculator {


    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;
    
        if (numbers.contains(",")) {
            String[] parts = numbers.split(",");
            int sum = 0;
            for (String part : parts) {
                sum += Integer.parseInt(part);
            }
            return sum;
        }
    
        return Integer.parseInt(numbers);
    }
    
}
