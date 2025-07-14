/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author pc
 */
/**
 * Thrown when a withdrawal attempt exceeds the allowed daily withdrawal limit
 * for the account.
 */
public class DailyLimitExceededException extends CustomException {

    private static final String DEFAULT_MESSAGE
            = "This withdrawal exceeds your daily limit. Try a smaller amount or wait until tomorrow.";

    public DailyLimitExceededException() {
        super(DEFAULT_MESSAGE);
    }

    public DailyLimitExceededException(String message) {
        super(message);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}
