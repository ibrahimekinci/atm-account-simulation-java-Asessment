/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import exception.InvalidAmountException;
import exception.InsufficientFundsException;
import exception.CustomException;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author pc
 */
/**
 * Fixed account
 */
public class FixedAccount extends BaseAccount implements IInterestBearing {

    private final LocalDate accountOpenDate;
    private final Period termPeriod;
    private final boolean earlyWithdrawalAttempted;

    public FixedAccount(double initialBalance, LocalDate accountOpenDate, Period termPeriod, boolean earlyWithdrawalAttempted)
            throws InvalidAmountException {
        super(initialBalance);
        this.accountOpenDate = accountOpenDate;
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
            balance += balance * 0.05;
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
        LocalDate maturityDate = accountOpenDate.plus(termPeriod);
        return LocalDate.now().isBefore(maturityDate);
    }
}
