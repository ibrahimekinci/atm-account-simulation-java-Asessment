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
 * NetSaverAccount
 */
public class NetSaverAccount extends BaseAccount implements IHasTermInfo, IInterestBearing, IHasDailyLimit {

    private static final double INTEREST_RATE = 0.03; // 3% monthly
    private static final Period TERM_PERIOD = Period.ofMonths(1); // Fixed 1-month term
    private static final double DEFAULT_WITHDRAWAL_LIMIT = 1000.00;
    private double remainingWithdrawalLimit = 0;
    private final LocalDate termBeginDate;

    public NetSaverAccount(String accountId, String accountTitle, double initialBalance, LocalDate termBeginDate) throws InvalidAmountException {
        super(accountId, accountTitle, AccountType.NET_SAVER, initialBalance);
        this.termBeginDate = termBeginDate;
        remainingWithdrawalLimit = DEFAULT_WITHDRAWAL_LIMIT;
    }

    public NetSaverAccount(String accountId, String accountTitle, double initialBalance, LocalDate termBeginDate, double remainingWithdrawalLimit) throws InvalidAmountException {
        super(accountId, accountTitle, AccountType.NET_SAVER, initialBalance);
        this.termBeginDate = termBeginDate;
        this.remainingWithdrawalLimit = remainingWithdrawalLimit;
    }

    @Override
    public double getRemainingWithdrawalLimit() {
        return remainingWithdrawalLimit;
    }

    @Override
    public boolean isWithinDailyLimit(double amount) {
        return amount <= remainingWithdrawalLimit;
    }

    @Override
    public void addInterest() {
        // Add interest only if a month has passed
        LocalDate termEnd = termBeginDate.plus(TERM_PERIOD);
        if (!LocalDate.now().isBefore(termEnd)) {
            balance += balance * INTEREST_RATE;
        }
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException, DailyLimitExceededException {
        validateWithdrawal(amount);
        if (!isWithinDailyLimit(amount)) {
            throw new DailyLimitExceededException("NetSaver accounts have a strict $1000 daily limit.");
        }
        checkBalance(amount);
        balance -= amount;
        remainingWithdrawalLimit -= amount;
    }

    @Override
    public LocalDate getTermBeginDate() {
        return termBeginDate;
    }

    @Override
    public Period getTermPeriod() {
        return TERM_PERIOD;
    }

    @Override
    public double getWithdrawalLimit() {
        return DEFAULT_WITHDRAWAL_LIMIT;
    }

    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }
}
