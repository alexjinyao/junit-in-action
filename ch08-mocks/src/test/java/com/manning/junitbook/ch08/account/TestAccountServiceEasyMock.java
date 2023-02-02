package com.manning.junitbook.ch08.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test-case to test the AccountService class by means of the EasyMock framework
 */
public class TestAccountServiceEasyMock {

    /**
     * We declare the object that we would like to mock.
     * Notice that our AccountManager is an interface.
     * The reason is simple: the core EasyMock framework can mock only interface objects.
     */
    private AccountManager mockAccountManager;

    @BeforeEach
    void setUp() {
        /**
         * We call the createMock method to create a mock of the class we want.
         */
        mockAccountManager = createMock("mockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

        /**
         * We start declaring our expectations.
         * With EasyMock, we declare the expectations in two ways.
         * When the method return type is void, we call it on the mock object.
         * When the method returns any kind of object, we use the expect and andReturn methods from the EasyMock API.
         */
        expect(mockAccountManager.findAccountForUser("1")).andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiaryAccount);

        /**
         * When we finish defining the expectations, we call the replay method.
         * The replay method passes the mock from the phase where we record the method we expect to be called to where we test.
         * Before, we simply recorded the behavior, but the object was not working as a mock.
         * After calling replay, the object works as expected.
         */
        replay(mockAccountManager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }

    @AfterEach
    void tearDown() {
        /**
         * With EasyMock, we can call the verify method with any mock object to verify that the method-call
         * expectations we declared were triggered.
         * Including the verification in the @AfterEach method allows us to introduce new tests easily,
         *
         */
        verify(mockAccountManager);
    }
}
