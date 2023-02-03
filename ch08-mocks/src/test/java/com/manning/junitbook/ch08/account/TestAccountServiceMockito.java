package com.manning.junitbook.ch08.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test-case to test the AccountService class by means of the Mockito framework
 */

/**
 * We extend this test by using MockitoExtension.
 * @ExtendWith is a repeatable annotation that is used to register extensions for the annotated test class or test method.
 * For this Mockito example, we only note that this extension is needed to create mock objects through annotation.
 */
@ExtendWith(MockitoExtension.class)
public class TestAccountServiceMockito {

    /**
     * This code tells Mockito to creat a mock object of type AccountManager
     */
    @Mock
    private AccountManager mockAccountManager;

    @Test
    void testTransferOk() {

        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        /**
         * We start declaring the expectations by using the when method.
         * Additionally, we use the lenient method to modify the strictness of objet mockng.
         * Without this method, only one expectation declaration is allowed for the same
         * findAccountForUser method, whereas we need two.
         */

        Mockito.lenient()
                .when(mockAccountManager.findAccountForUser("1"))
                .thenReturn(senderAccount);
        Mockito.lenient()
                .when(mockAccountManager.findAccountForUser("2"))
                .thenReturn(beneficiaryAccount);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);

        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }



}
