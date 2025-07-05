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
 * The IInterestBearing interface marks accounts that can accrue interest.
 */
public interface IInterestBearing {

    /**
     * Accrues interest to the account. Typically increases the balance by an
     * amount based on the current balance and a defined interest rate.
     */
    void addInterest();
}
