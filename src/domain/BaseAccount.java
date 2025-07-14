/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author pc
 */
import exceptions.CustomException;
import exceptions.InvalidAmountException;
import exceptions.InsufficientFundsException;

/**
 * Abstract class for shared account logic.
 */
public abstract class BaseAccount implements IAccount {

    protected final String accountId;
    protected final String accountTitle;
    protected final AccountType accountType;
    protected double balance;

    public BaseAccount(String accountId, String accountTitle, AccountType accountType, double initialBalance) throws InvalidAmountException {
        if (initialBalance <= 0) {
            throw new InvalidAmountException("Initial balance must be greater than zero.");
        }
        this.balance = initialBalance;
        this.accountId = accountId;
        this.accountTitle = accountTitle;
        this.accountType = accountType;
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * Validates the amount for withdrawal based on ATM note denominations.
     */
    protected void validateWithdrawal(double amount) throws InvalidAmountException {
        if (amount <= 0 || (amount % 20 != 0 && amount % 50 != 0 && amount % 100 != 0)) {
            throw new InvalidAmountException();
        }
    }

    protected void checkBalance(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException();
        }
    }

    @Override
    public void withdraw(double amount) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public String getAccountTitle() {
        return accountTitle;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String getAccountTypeDisplayName() {
        return switch (accountType) {
            case SAVINGS ->
                "Savings Account";
            case CHEQUE ->
                "Cheque Account";
            case NET_SAVER ->
                "Net Saver Account";
            case FIXED ->
                "Fixed Account";
            default ->
                "Unknown Account";
        }; // Assuming accountType is a field of type AccountType
    }
}
