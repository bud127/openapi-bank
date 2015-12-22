package openapi.bank.api.mvc;

import java.io.Serializable;

/**
 * @author chanwook
 */
public class Account implements Serializable {
    
    private String accountNumber;
    private double balance;

    public Account() {
    }

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
