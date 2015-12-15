package openapi.bank.api.mvc;

import java.io.Serializable;

/**
 * @author chanwook
 */
public class Account implements Serializable {
    
    private final String accountNumber;
    private final double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
