/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import domain.Customer;
import infrastructure.MockSessionManager;

import javax.swing.*;

/**
 *
 * @author pc
 */
public abstract class SecureFrame extends BaseFrame {

    protected final Customer loggedInCustomer;

    private Timer sessionCheckTimer;
    private MockSessionManager sessionManager;

    public SecureFrame(String title, Customer customer) {
        super(title);
        this.loggedInCustomer = customer;
        validateSession();
        sessionManager = new MockSessionManager();
        setupSessionTimer();
    }

    protected void logout() {
        dispose();
        new LoginFrame().setVisible(true);
    }

    private void validateSession() {
        if (loggedInCustomer == null) {
            JOptionPane.showMessageDialog(this, "Unauthorized access. Please login.");
            logout();
        }
    }

    private void setupSessionTimer() {
        sessionCheckTimer = new Timer(10000, e -> {
            if (sessionManager.isSessionExpired()) {
                sessionCheckTimer.stop();
                promptSessionContinuation();
            }
        });
        sessionCheckTimer.start();

    }

    private void promptSessionContinuation() {
        JOptionPane optionPane = new JOptionPane(
                "You have been inactive.\nDo you want to continue?",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION
        );

        JDialog dialog = optionPane.createDialog(this, "Session Timeout");

        // waıt for the answer for 10 seconds
        Timer autoClose = new Timer(10000, ev -> {
            optionPane.setValue(JOptionPane.NO_OPTION);
            dialog.setVisible(false);
        });
        autoClose.setRepeats(false);
        autoClose.start();
        dialog.setVisible(true);
        autoClose.stop();

        Object result = optionPane.getValue();
        if (result == null || result.equals(JOptionPane.NO_OPTION)) {
            JOptionPane.showMessageDialog(this, "You have been logged out due to inactivity.");
            logout();
        } else {
            sessionManager.refreshSession();
            sessionCheckTimer.restart();
        }
    }
}
