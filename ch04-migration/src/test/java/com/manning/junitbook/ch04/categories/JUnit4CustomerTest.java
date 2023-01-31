package com.manning.junitbook.ch04.categories;


import com.manning.junitbook.ch04.tags.Customer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

public class JUnit4CustomerTest {

    private String CUSTOMER_NAME = "John Smith";

    @Category(IndividualTests.class)
    @Test
    public void testCustomer() {
        Customer customer = new Customer(CUSTOMER_NAME);
        assertEquals("John Smith", customer.getName());
    }


}
