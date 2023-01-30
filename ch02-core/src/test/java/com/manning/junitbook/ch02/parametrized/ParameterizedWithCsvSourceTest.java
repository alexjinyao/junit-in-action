package com.manning.junitbook.ch02.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedWithCsvSourceTest {
    private WordCounter wordCounter = new WordCounter();

    /**
     * We can use **@CsvSource** to express argument lists as comma-separated values (CSV), such as **String** literals.
     * Each CSV line is parsed.
     * The first value is assigned to the **expected** parameter, and the second value is assigned to the **sentence** parameter.
     * @param expected
     * @param sentence
     */
    @ParameterizedTest
    @CsvSource({"2, Unt testing", "3, JUnit in Action", "4, Write solid Java Code"})
    void testWordsInSentence(int expected, String sentence) {
        assertEquals(expected, wordCounter.countWords(sentence));
    }
}
