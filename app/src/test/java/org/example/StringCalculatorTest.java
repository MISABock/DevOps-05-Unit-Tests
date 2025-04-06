// StringCalculatorTest.java
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    void testEmptyStringReturnsZero() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("");
        assertEquals(0, result);
    }

    @Test
    void testSingleNumberReturnsThatNumber() {
    StringCalculator calculator = new StringCalculator();
    int result = calculator.add("1");
    assertEquals(1, result);
}
    @Test
    void testTwoNumbersAreSummed() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1,2");
        assertEquals(3, result);
    }


}
