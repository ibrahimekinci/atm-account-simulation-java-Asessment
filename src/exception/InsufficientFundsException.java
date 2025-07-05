/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author pc /** Thrown when an attempted withdrawal exceeds the current
 * account balance.
 */
public class InsufficientFundsException extends CustomException {

    private static final String DEFAULT_MESSAGE
            = "Insufficient balance. Please check your available funds.";

    public InsufficientFundsException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}
