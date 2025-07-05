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
 * Interface for accounts where the withdrawal limit can be configured.
 */
public interface IConfigurableLimit {

    void setDailyLimit(double newLimit);
}
