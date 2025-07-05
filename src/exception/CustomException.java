/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 * Abstract base class for all custom exceptions in the ATM system. Implements
 * ICustomException and provides a consistent structure for error handling.
 */
public abstract class CustomException extends Exception implements ICustomException {

    /**
     * Constructor with a custom message.
     *
     * @param message custom message to override the default
     */
    public CustomException(String message) {
        super(message);
    }

    /**
     * Returns the default error message for the exception.
     *
     * Must be implemented by each concrete subclass.
     */
    @Override
    public abstract String getDefaultMessage();

    /**
     * Returns the actual message.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
