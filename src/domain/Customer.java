/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.List;

/**
 *
 * @author pc
 */
public class Customer {

    private final String customerId;
    private final String userId;
    private final String username;
    private final String password;
    private final String fullName;
    private final List<IAccount> accounts;

    public Customer(String customerId, String userId, String username, String password, String fullName, List<IAccount> accounts) {
        this.customerId = customerId;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.accounts = accounts;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public List<IAccount> getAccounts() {
        return accounts;
    }

    public IAccount getAccountById(String accountId) {
        for (IAccount account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }
}
