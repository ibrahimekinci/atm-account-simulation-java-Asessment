/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import domain.*;
import exceptions.*;
import infrastructure.*;

/**
 *
 * @author pc
 */
public class MyAccountsFrame extends SecureFrame {

    private final Customer customer;
    private JTable accountsTable;

    public MyAccountsFrame(Customer customer) {
        super("My Accounts", customer);
        this.customer = customer;
        
        resetSession();
        setTitle("My Accounts - " + customer.getFullName());
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initializeUI();
    }

    private void initializeUI() {
        // Top welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + customer.getFullName(), JLabel.CENTER);
        welcomeLabel.setFont(UIStyle.TITLE_FONT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(welcomeLabel, BorderLayout.NORTH);

        // Table of accounts
        String[] columns = {"Account Title", "Account Type", "Account ID", "Current Balance"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (IAccount account : customer.getAccounts()) {
            model.addRow(new Object[]{
                account.getAccountTitle(),
                account.getAccountType(),
                account.getAccountId(),
                String.format("$%.2f", account.getBalance())
            });
        }

        accountsTable = new JTable(model);
        accountsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountsTable.setFont(UIStyle.DEFAULT_FONT);
        accountsTable.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(accountsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Left = Help Link
        bottomPanel.add(UIStyle.createHelpLinkPanel(), BorderLayout.WEST);

        // Right = Action Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton selectButton = new JButton("Select Account");
        JButton logoutButton = new JButton("Logout");

        UIStyle.styleButton(selectButton);
        UIStyle.styleButton(logoutButton);

        selectButton.addActionListener(this::handleSelectAccount);
        logoutButton.addActionListener(e -> {
            logout();
        });

        buttonPanel.add(selectButton);
        buttonPanel.add(logoutButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handleSelectAccount(ActionEvent e) {
        int selectedRow = accountsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an account.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String accountId = accountsTable.getValueAt(selectedRow, 2).toString();
        IAccount selectedAccount = customer.getAccountById(accountId);

        if (selectedAccount != null) {
            dispose();
            new AccountOperationFrame(customer, selectedAccount).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
