package ui;

import domain.*;
import exceptions.*;
import infrastructure.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Login screen for ATM Simulation.
 */
public class LoginFrame extends BaseFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;

    public LoginFrame() {
        super("AIT ATM Login");

        JPanel mainPanel = UIStyle.createPaddedPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Welcome to the ATM System");
        UIStyle.styleLabel(title, true);
        title.setFont(UIStyle.TITLE_FONT);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        mainPanel.add(new JLabel("Username:"), gbc);
        usernameField = new JTextField("testuser");
        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);
        UIStyle.styleTextField(usernameField);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField("test123");
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);
        UIStyle.styleTextField(passwordField);

        // Error Label
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(errorLabel, gbc);

        // Login Button
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        UIStyle.styleButton(loginButton);
        loginButton.addActionListener(e -> attemptLogin());
        mainPanel.add(loginButton, gbc);

        // Help Button
        gbc.gridx = 1;
        JButton helpButton = new JButton("Help");
        UIStyle.styleButton(helpButton);
        helpButton.addActionListener(e -> goToHelpPage());
        mainPanel.add(helpButton, gbc);
        gbc.gridy++;

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void goToHelpPage() {
        dispose();
        new HelpFrame();
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        Customer customer = null;
        try {
            MockBankingService mockBankingService = new MockBankingService();
            customer = mockBankingService.authenticate(username, password);
        } catch (Exception exception) {
            if (exception instanceof ICustomException customException) {
                errorLabel.setText(String.format("❌ Domain Error: %s%n", customException.getMessage()));
                System.out.printf("❌ Domain Error: %s%n", customException.getMessage());
            } else {
                errorLabel.setText("❌ Unexpected Error");
                System.out.printf("❌ Unexpected Error: %s%n", exception.getMessage());
            }
        }

        if (customer == null) {
            errorLabel.setText("Invalid username or password");
        } else {
            dispose();
            new MyAccountsFrame(customer).setVisible(true);
        }
    }
}
