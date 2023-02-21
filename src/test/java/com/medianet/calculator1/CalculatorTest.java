package com.medianet.calculator1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CalculatorTest {
    private Calculator calculator = new Calculator();
    @Test
    public void testSum() {
        assertEquals(5, calculator.sum(2, 3));
    }
}