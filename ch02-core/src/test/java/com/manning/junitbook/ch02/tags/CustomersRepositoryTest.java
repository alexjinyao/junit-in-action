package com.manning.junitbook.ch02.tags;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("repository")
public class CustomersRepositoryTest {

    private String CUSTOMER_NAME = "John Smith";
    private CustomersRepository repository = new CustomersRepository();

    @Test
    void testNonExistence() {
        final boolean exists = repository.contains("John Smith");

        Assertions.assertFalse(exists);
    }

    @Test
    void testCustomerPersistence() {
        repository.persist(new Customer(CUSTOMER_NAME));

        Assertions.assertTrue(repository.contains("John Smith"));
    }
}
