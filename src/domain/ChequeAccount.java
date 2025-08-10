/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import exceptions.InvalidAmountException;
import exceptions.InsufficientFundsException;

/**
 *
 * @author pc
 */
/**
 * Cheque account
 */
public class ChequeAccount extends BaseAccount {

    public ChequeAccount(String accountId, String accountTitle, double initialBalance) throws InvalidAmountException {
        super(accountId, accountTitle, AccountType.CHEQUE, initialBalance);
    }
}
