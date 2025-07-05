/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import exception.InvalidAmountException;
import exception.InsufficientFundsException;
import exception.DailyLimitExceededException;

/**
 *
 * @author pc
 */
/**
 * Savings account
 */
public class SavingsAccount extends BaseAccount implements IInterestBearing, IHasDailyLimit, IConfigurableLimit {

    private static final double INTEREST_RATE = 0.02;     // e.g., 2% interest rate (per addInterest call)
    private double remainingDailyLimit = 0;

    public SavingsAccount(double initialBalance, double remainingDailyLimit) throws InvalidAmountException {
        super(initialBalance);
        this.remainingDailyLimit = remainingDailyLimit;
    }

    @Override
    public void setDailyLimit(double newLimit) {
        if (newLimit > 0) {
            this.remainingDailyLimit = newLimit;
        }
    }

    @Override
    public double getDailyLimit() {
        return remainingDailyLimit;
    }

    @Override
    public boolean isWithinDailyLimit(double amount) {
        return amount <= remainingDailyLimit;
    }

    @Override
    public void addInterest() {
        balance += balance * INTEREST_RATE;
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException, DailyLimitExceededException {
        validateWithdrawal(amount);
        if (!isWithinDailyLimit(amount)) {
            throw new DailyLimitExceededException();
        }
        checkBalance(amount);
        balance -= amount;
        remainingDailyLimit -= amount;
    }
}
