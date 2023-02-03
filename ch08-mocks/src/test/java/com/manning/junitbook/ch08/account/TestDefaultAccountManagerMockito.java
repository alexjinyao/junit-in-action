package com.manning.junitbook.ch08.account;


import com.manning.junitbook.ch08.configurations.Configuration;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

/**
 * This is another test-case for the DefaultAccountManager class.
 * We use here the Mockito library to mock the logger and the configuration.
 */
@ExtendWith(MockitoExtension.class)
public class TestDefaultAccountManagerMockito {

    @Mock
    private Configuration configuration;

    @Mock
    private Log logger;

    @Test
    void testFindAccountByUser() {
        when(configuration.getSQL("FIND_ACCOUNT_FOR_USER"))
                .thenReturn("Getting account for user [1234]");

        DefaultAccountManager2 accountManager = new DefaultAccountManager2(logger, configuration);

        @SuppressWarnings("unused")
        Account account = accountManager.findAccountForUser("1234");

        // Perform asserts here

    }
}
