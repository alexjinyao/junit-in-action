package com.manning.junitbook.ch08.account;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock implementation of the AccountManager interface
 */
public class MockAccountManager implements AccountManager{

    /**
     * A Map to hold all the <userId, account> values.
     */
    private Map<String, Account> accounts = new HashMap<>();

    /**
     * A method to add an account to the manager.
     * @param userId
     * @param account
     */
    public void addAccount(String userId, Account account) {
        this.accounts.put(userId, account);
    }


    /**
     * A method to find an account by the given userId.
     *
     * @param userId
     * @return
     */
    @Override
    public Account findAccountForUser(String userId) {
        return this.accounts.get(userId);
    }

    /**
     * A method to update the given account.
     * Notice that we don't need this method and that's why we leave it with
     * a blank implementation
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {

    }
}
