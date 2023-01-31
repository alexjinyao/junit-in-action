package com.manning.junitbook.ch04.rules;

import org.junit.Rule;
import org.junit.Test;

public class JUnit4CustomRuleTester {

    @Rule
    public CustomRule customRule = new CustomRule();

    @Test
    public void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
