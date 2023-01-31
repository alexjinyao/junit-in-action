package com.manning.junitbook.ch04.categories;

import com.manning.junitbook.ch04.tags.Customer;
import com.manning.junitbook.ch04.tags.CustomerRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Category({IndividualTests.class, RepositoryTests.class})
public class JUnit4CustomersRepositoryTest {

    private String CUSTOMER_NAME = "John Smith";
    private CustomerRepository repository = new CustomerRepository();

    @Test
    public void testNonExistence() {
        boolean exists = repository.contains(CUSTOMER_NAME);
        assertFalse(exists);
    }

    @Test
    public void testCustomerPersistence() {
        repository.persist(new Customer(CUSTOMER_NAME));
        assertTrue(repository.contains("John Smith"));
    }

}
