package com.manning.junitbook.ch08.account;


/**
 * A service that has different methods that we can use.
 * Currently, it holds only the transfer method, which transfers money from one account to others.
 */
public class AccountService {
    /**
     * The account manager implementation to use.
     */
    private AccountManager accountManager;

    /**
     * A setter method to set the account manager implementation.
     * @param accountManager
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * A transfer method which transfers the amount of money
     * from the account with the senderId to the account of beneficiaryId.
     * @param senderId
     * @param beneficiaryId
     * @param amount
     */
    public void transfer(String senderId, String beneficiaryId, long amount) {
        Account sender = accountManager.findAccountForUser(senderId);
        Account beneficiary = accountManager.findAccountForUser(beneficiaryId);

        sender.debit(amount);
        beneficiary.credit(amount);

        this.accountManager.updateAccount(sender);
        this.accountManager.updateAccount(beneficiary);
    }
}
