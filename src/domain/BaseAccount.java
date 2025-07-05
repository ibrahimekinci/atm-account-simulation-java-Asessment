/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author pc
 */
import exception.InvalidAmountException;
import exception.InsufficientFundsException;

/**
 * Abstract class for shared account logic.
 */
public abstract class BaseAccount implements IAccount {

    protected double balance;

    public BaseAccount(double initialBalance) throws InvalidAmountException {
        if (initialBalance <= 0) {
            throw new InvalidAmountException("Initial balance must be greater than zero.");
        }
        this.balance = initialBalance;
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
}
