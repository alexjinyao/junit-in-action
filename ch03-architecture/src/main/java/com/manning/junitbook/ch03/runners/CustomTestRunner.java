package com.manning.junitbook.ch03.runners;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * We would like to enrich the behavior of tests that use this class by introducing an additional action before executing the test suite.
 * We will create a custom runner and use it as an argument of the @RunWith annotation to add custom features to the original JUnit functionality.
 */
public class CustomTestRunner extends Runner {

    private Class<?> testedClass;

    public CustomTestRunner(Class<?> testedClass) {
        this.testedClass = testedClass;
    }

    /**
     * This method contains information that is later exported and may be used by various tools.
     * @return
     */
    @Override
    public Description getDescription() {
        return Description.createTestDescription(testedClass, this.getClass().getSimpleName() + " description");
    }

    @Override
    public void run(RunNotifier runNotifier) {
        System.out.println("Running tests with " + this.getClass().getSimpleName() + ": " + testedClass);

        try {
            Object testObject = testedClass.newInstance();
            for (Method method : testedClass.getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    runNotifier.fireTestStarted(Description.createTestDescription(testedClass, method.getName()));
                    method.invoke(testObject);
                    runNotifier.fireTestFinished(Description.createTestDescription(testedClass, method.getName()));
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
