package com.manning.junitbook.ch04.suites;

import com.manning.junitbook.ch04.categories.IndividualTests;
import com.manning.junitbook.ch04.categories.JUnit4CustomerTest;
import com.manning.junitbook.ch04.categories.JUnit4CustomersRepositoryTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(IndividualTests.class)
@Suite.SuiteClasses({JUnit4CustomerTest.class, JUnit4CustomersRepositoryTest.class})
public class JUnit4IndividualTestsSuite {
}
