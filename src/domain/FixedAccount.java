/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import exceptions.InvalidAmountException;
import exceptions.InsufficientFundsException;
import exceptions.CustomException;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author pc
 */
/**
 * Fixed account
 */
public class FixedAccount extends BaseAccount implements IHasTermInfo, IInterestBearing {

    private static final double INTEREST_RATE = 0.05; // 3% monthly
    private final LocalDate termBeginDate;
    private final Period termPeriod;
    private final boolean earlyWithdrawalAttempted;

    public FixedAccount(String accountId, String accountTitle, double initialBalance, LocalDate termBeginDate, Period termPeriod, boolean earlyWithdrawalAttempted)
            throws InvalidAmountException {
        super(accountId, accountTitle, AccountType.FIXED, initialBalance);
        this.termBeginDate = termBeginDate;
        this.termPeriod = termPeriod;
        this.earlyWithdrawalAttempted = earlyWithdrawalAttempted;
    }

    @Override
    public void withdraw(double amount) throws CustomException {
        validateWithdrawal(amount);
        if (amount > balance) {
            throw new InsufficientFundsException();
        }
        balance -= amount;
    }

    @Override
    public void addInterest() {
        if (!isItEarlyWithdrawal()) {
            balance += balance * INTEREST_RATE;
        }
    }

    /**
     * Determines if a withdrawal is considered early based on term maturity.
     *
     * @return true if withdrawal is early; false otherwise
     */
    private boolean isItEarlyWithdrawal() {
        if (earlyWithdrawalAttempted) {
            return true;
        }
        LocalDate maturityDate = termBeginDate.plus(termPeriod);
        return LocalDate.now().isBefore(maturityDate);
    }

    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }

    @Override
    public LocalDate getTermBeginDate() {
        return termBeginDate;
    }

    @Override
    public Period getTermPeriod() {
        return termPeriod;
    }

}
