package com.manning.junitbook.ch06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        double sum = calculator.add(10, 50);
        assertEquals(60, sum, 0);
    }
}
