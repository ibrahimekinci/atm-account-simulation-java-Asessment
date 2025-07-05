/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author pc
 */
/**
 * Thrown when an invalid (negative or incompatible) amount is provided for a
 * transaction.
 */
public class InvalidAmountException extends CustomException {

    private static final String DEFAULT_MESSAGE
            = "Amount must be greater than zero and match ATM note denominations (e.g., 20, 50, 100)";

    public InvalidAmountException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidAmountException(String message) {
        super(message);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}
