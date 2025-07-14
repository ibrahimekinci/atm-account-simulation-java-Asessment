package exceptions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author pc
 */
/**
 * Marker interface for all custom exceptions with default messaging.
 */
/**
 * Contract for all custom exceptions used in the ATM system. Ensures consistent
 * access to default and actual error messages.
 */
public interface ICustomException {

    /**
     * Returns a default error message string specific to the exception type.
     */
    String getDefaultMessage();

    /**
     * Returns the actual message (inherited from Exception). Included here for
     * interface contract clarity.
     */
    String getMessage();
}
