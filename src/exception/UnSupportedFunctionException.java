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
 * Thrown when an account or class does not support a requested function.
 */
public class UnSupportedFunctionException extends CustomException {

    private static final String DEFAULT_MESSAGE = "This function is not supported by this class.";

    public UnSupportedFunctionException(Class<?> clazz) {
        super(String.format("This function is not supported by class: %s", clazz.getSimpleName()));
    }

    public UnSupportedFunctionException(String message) {
        super(message);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}
