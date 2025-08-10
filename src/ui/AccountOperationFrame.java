/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import domain.*;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author pc
 */
/**
 * Screen to perform operations on the selected account.
 */
public class AccountOperationFrame extends SecureFrame {

    private final Customer customer;
    private final IAccount account;

    private JLabel balanceLabel;
    private JLabel remainingWithdrawalLimitLabel;

    private JTextField inputField;

    public AccountOperationFrame(Customer customer, IAccount account) {
        super("Account Operations", customer);
        this.customer = customer;
        this.account = account;

        resetSession();
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeUI();
    }

    private void initializeUI() {
        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Account Info"));

        infoPanel.add(new JLabel("Account Title: " + account.getAccountTitle()));
        infoPanel.add(new JLabel("Account ID: " + account.getAccountId()));
        infoPanel.add(new JLabel("Account Type: " + account.getAccountType()));

        if (account instanceof IHasDailyLimit limitInfo) {
            infoPanel.add(new JLabel("Daily Limit: $" + limitInfo.getWithdrawalLimit()));
            remainingWithdrawalLimitLabel = new JLabel("Remaining Daily Limit: $" + limitInfo.getRemainingWithdrawalLimit());
            infoPanel.add(remainingWithdrawalLimitLabel);
        }

        if (account instanceof IInterestBearing interestInfo) {
            infoPanel.add(new JLabel("Interest Rate: " + interestInfo.getInterestRateDisplayText()));
        }

        if (account instanceof IHasTermInfo termInfo) {
            infoPanel.add(new JLabel("Term Begin Date: " + termInfo.getTermBeginDate()));
            infoPanel.add(new JLabel("Term Period: " + termInfo.getTermPeriodDisplayText()));
        }

        balanceLabel = new JLabel("Current Balance: $" + String.format("%.2f", account.getBalance()));
        infoPanel.add(balanceLabel);

        add(infoPanel, BorderLayout.NORTH);

        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setFont(new Font("Arial", Font.BOLD, 18));
        add(inputField, BorderLayout.CENTER);

        JPanel keypadPanel = createKeypadPanel();
        add(keypadPanel, BorderLayout.SOUTH);
    }

    private JPanel createKeypadPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 3, 5, 5));
        String[] buttons = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "CLR"};

        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.addActionListener(e -> handleKeypadInput(label));
            panel.add(btn);
        }

        JButton depositBtn = new JButton("Deposit");
        depositBtn.addActionListener(e -> performTransaction(true));

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.addActionListener(e -> performTransaction(false));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> {
            dispose();
            new MyAccountsFrame(customer).setVisible(true);
        });

        JButton switchBtn = new JButton("Switch Account");
        switchBtn.addActionListener(e -> {
            dispose();
            new MyAccountsFrame(customer).setVisible(true);
        });

        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(switchBtn);
        panel.add(cancelBtn);

        return panel;
    }

    private void handleKeypadInput(String key) {
        resetSession();
        if (key.equals("CLR")) {
            inputField.setText("");
        } else {
            inputField.setText(inputField.getText() + key);
        }
    }

    private void performTransaction(boolean isDeposit) {
        resetSession();

        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            showMessage("Please enter an amount.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(input);

            if (isDeposit) {
                account.deposit(amount);
                showMessage("Deposit successful!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                account.withdraw(amount);
                showMessage("Withdrawal successful!", JOptionPane.INFORMATION_MESSAGE);
            }

            balanceLabel.setText("Current Balance: $" + String.format("%.2f", account.getBalance()));

            if (account instanceof IHasDailyLimit limitInfo) {
                remainingWithdrawalLimitLabel.setText("Remaining Daily Limit: $" + limitInfo.getRemainingWithdrawalLimit());
            }

            inputField.setText("");

        } catch (Exception ex) {
            showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMessage(String message, int messageType) {
        JOptionPane.showMessageDialog(this, message, "Notice", messageType);
    }
}
