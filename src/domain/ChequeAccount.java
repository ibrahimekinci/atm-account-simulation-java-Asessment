/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import exception.InvalidAmountException;
import exception.InsufficientFundsException;

/**
 *
 * @author pc
 */
/**
 * Cheque account
 */
public class ChequeAccount extends BaseAccount {

    public ChequeAccount(double initialBalance) throws InvalidAmountException {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        validateWithdrawal(amount);
        checkBalance(amount);
        balance -= amount;
    }
}
