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
 * The IWithdrawalLimit interface marks accounts that enforce a daily withdrawal
 * limit.
 */
public interface IWithdrawalLimit {

    /**
     * Returns the maximum total amount that can be withdrawn from this account
     * in a single day.
     *
     * @return the daily withdrawal limit for this account.
     */
    double getDailyLimit();

    /**
     * Resets the record of withdrawals for the day. This should be called
     * (e.g., at end of day) to allow fresh withdrawals up to the limit the next
     * day.
     */
    void resetDailyWithdrawals();
}
