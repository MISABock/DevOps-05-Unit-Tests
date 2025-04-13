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

    @Test
    void testNewlinesAsSeparators() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1\n2,3");
        assertEquals(6, result);
    }
    @Test
        void testCustomDelimiter() {
            StringCalculator calculator = new StringCalculator();
            int result = calculator.add("//;\n1;2");
            assertEquals(3, result);
}

    @Test
    void testNumbersGreaterThan1000AreIgnored() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("2,1001");
        assertEquals(2, result);
    }
    @Test
    void testNegativeNumbersThrowException() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add("1,-2,3"),
            "Expected exception for negative numbers"
        );
        assertTrue(thrown.getMessage().contains("Negatives not allowed: -2"));
    }
    
    @Test
    void testMultipleNegativeNumbersThrowExceptionWithAllNumbers() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add("1,-2,-5"),
            "Expected exception for multiple negative numbers"
        );
        assertEquals("Negatives not allowed: -2,-5", thrown.getMessage());
    }
    @Test
    void testMultipleDelimiters() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("//[*][%]\n1*2%3");
        assertEquals(6, result);
    }
    @Test
    void testDelimiterWithoutClosingBracketFails() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("//[***[%%%]\n1***2%%%3");
        assertEquals(6, result); // Sollte fehlschlagen: fehlende schließende Klammer bei [***[%%%]
    }
    
    
    @Test
    void testUnsupportedDelimiterFormatFails() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("//***\n1***2***3"); // Kein []-Block für den Delimiter!
        assertEquals(6, result); // Sollte fe
    


}
}