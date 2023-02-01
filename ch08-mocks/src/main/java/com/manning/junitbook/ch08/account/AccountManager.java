package com.manning.junitbook.ch08.account;

/**
 * An interface for all the manager implementations.
 */
public interface AccountManager {

    /**
     * A method to find an account by the given userId.
     * @param userId
     * @return
     */
    Account findAccountForUser(String userId);

    /**
     * A method to update the given account.
     * @param account
     */
    void updateAccount(Account account);
}
