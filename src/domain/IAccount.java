/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import exception.InvalidAmountException;
import exception.CustomException;

/**
 *
 * @author pc
 */
/**
 * Interface for common account operations.
 */
public interface IAccount {

    void deposit(double amount) throws InvalidAmountException;

    void withdraw(double amount) throws CustomException;

    double getBalance();
}
