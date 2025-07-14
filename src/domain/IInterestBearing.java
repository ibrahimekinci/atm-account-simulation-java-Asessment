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

    double getInterestRate();

    /**
     * Returns a formatted interest rate string for display. Examples: - 0.03 ->
     * "3.00% per month" - 0.05 -> "5.00% per term"
     *
     * @return
     */
    default String getInterestRateDisplayText() {
        double rate = getInterestRate();
        return String.format("%.2f%% per term", rate * 100);
    }
}
