package com.manning.junitbook.ch02.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedWithEnumSourceTest {

    private WordCounter wordCounter = new WordCounter();

    /**
     * @EnumSource enables us to use enum instances.
     * The annotation provides an optional names parameter that lets us specify which instances must be used or excluded.
     * By default, all instances of an enum are used.
     */

    /**
     * So this test is executed three times, once for each instance of the Sentences enum
     * @param sentences
     */
    @ParameterizedTest
    @EnumSource(Sentences.class)
    void testWordsInSentence(Sentences sentences) {
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }

    /**
     * We restrict the instances to be passed to the test to **JUNIT_IN_ACTION** and **THREE_PARAMETERS**.
     * So, this test will be executed twice.
     * @param sentences
     */
    @ParameterizedTest
    @EnumSource(value = Sentences.class, names = {"JUNIT_IN_ACTION", "THREE_PARAMETERS"})
    void testSelectedWordsInSentence(Sentences sentences) {
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }

    /**
     * we exclude the **THREE_PARAMETERS** instance.
     * So, this test is executed twice: for **JUST_IN_ACTION** and **SOME_PARAMETERS**.
     * @param sentences
     */
    @ParameterizedTest
    @EnumSource(value = Sentences.class, mode = EnumSource.Mode.EXCLUDE, names = {"THREE_PARAMETERS"})
    void testExcludedWordsInSentence(Sentences sentences) {
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }


    enum Sentences {
        JUNIT_IN_ACTION("Junit in Action"),
        SOME_PARAMETERS("Check some parameters"),
        THREE_PARAMETERS("Check three parameters");
        private final String sentence;

        Sentences(String sentence) {
            this.sentence = sentence;
        }

        public String value() {
            return sentence;
        }
    }
}
