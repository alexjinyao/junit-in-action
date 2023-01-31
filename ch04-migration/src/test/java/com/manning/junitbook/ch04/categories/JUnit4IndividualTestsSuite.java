package com.manning.junitbook.ch04.categories;


import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Annotated with @RunWith(Categories.class), informing JUnit that it has to execute the tests with this particular runner
 *
 * Includes the category of tests annotated with **IndividualTests**
 *
 * Looks for these annotated tests in the **JUnit4CustomerTest** and **JUnit4CustomerRepositoryTest** class
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(IndividualTests.class)
@Suite.SuiteClasses({JUnit4CustomerTest.class, JUnit4CustomersRepositoryTest.class})
public class JUnit4IndividualTestsSuite {
}
