package com.manning.junitbook.ch08.account;

import com.manning.junitbook.ch08.configurations.MockConfiguration;
import org.junit.jupiter.api.Test;

/**
 * A test-case for the DefaultAccountManager class.
 */
public class TestDefaultAccountManager {

    @Test
    void testFindAccountByUser() {
        MockLog logger = new MockLog();
        MockConfiguration configuration = new MockConfiguration();
        configuration.setSQL("SELECT * [...]");
        DefaultAccountManager2 accountManager = new DefaultAccountManager2(logger, configuration);

        Account account = accountManager.findAccountForUser("1234");
    }
}
