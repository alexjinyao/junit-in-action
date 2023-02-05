package com.manning.junitbook.ch03.rule;

import com.manning.junitbook.ch03.rules.CustomRule;
import org.junit.Rule;
import org.junit.Test;

public class CustomRuleTester {

    @Rule
    public CustomRule myRule = new CustomRule();

    @Test
    public void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
