/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import exceptions.InvalidAmountException;
import exceptions.InsufficientFundsException;
import exceptions.DailyLimitExceededException;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author pc
 */
/**
 * Savings account
 */
public class SavingsAccount extends BaseAccount implements IHasTermInfo, IInterestBearing, IHasDailyLimit, IConfigurableLimit {

    private static final Period TERM_PERIOD = Period.ofDays(1); // Fixed 1-day term
    private static final double INTEREST_RATE = 0.02;     // e.g., 2% interest rate (per addInterest call)

    private double dailyLimit = 1000.0;
    private double remainingDailyLimit = 0;

    public SavingsAccount(String accountId, String accountTitle, double initialBalance, double dailyLimit, double remainingDailyLimit) throws InvalidAmountException {
        super(accountId, accountTitle, AccountType.SAVINGS, initialBalance);
        this.dailyLimit = dailyLimit;
        this.remainingDailyLimit = remainingDailyLimit;

    }

    @Override
    public void setDailyLimit(double newLimit) {
        if (newLimit == 0) {
            remainingDailyLimit = 0; // Set remaining daily limit to zero
            dailyLimit = 0; // Set daily limit to zero
        } else if (newLimit > dailyLimit) {
            double usedAmount = dailyLimit - remainingDailyLimit; // Calculate the amount already used
            dailyLimit = newLimit; // Update daily limit to new value
            remainingDailyLimit = newLimit - usedAmount; // Subtract used amount from new limit
        } else if (newLimit < dailyLimit) {
            double usedAmount = dailyLimit - remainingDailyLimit; // Calculate the amount already used
            dailyLimit = newLimit; // Update daily limit to new value
            remainingDailyLimit = newLimit - usedAmount; // Subtract used amount from new limit
            if (remainingDailyLimit < 0) {
                remainingDailyLimit = 0; // Ensure remaining limit does not go negative
            }
        }
    }

    @Override
    public double getRemainingWithdrawalLimit() {
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

    @Override
    public double getWithdrawalLimit() {
        return dailyLimit;
    }

    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }

    @Override
    public LocalDate getTermBeginDate() {
        return LocalDate.now();
    }

    @Override
    public Period getTermPeriod() {
        return TERM_PERIOD;
    }
}
