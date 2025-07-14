/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

/**
 *
 * @author pc
 */
/**
 * Interface for accounts that have a daily withdrawal limit.
 */
public interface IHasDailyLimit {

    double getWithdrawalLimit();

    double getRemainingWithdrawalLimit();

    boolean isWithinDailyLimit(double amount);

}
