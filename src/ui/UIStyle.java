/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

/**
 *
 * @author pc
 */
/**
 * Central style and theme manager for UI consistency across all screens.
 */
public class UIStyle {

    // 🎨 Color Palette
    public static final Color PRIMARY_COLOR = new Color(0, 120, 215);          // Modern blue
    public static final Color SECONDARY_COLOR = new Color(240, 240, 240);      // Light gray
    public static final Color TEXT_COLOR = new Color(64, 64, 64);              // Dark gray
    public static final Color BUTTON_HOVER = new Color(0, 90, 180);
    public static final Color BUTTON_PRESS = new Color(0, 60, 150);

    // 🖋 Font Settings
    public static final Font DEFAULT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BOLD_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);

    // 🧱 Standard Layout Constants
    public static final int STANDARD_PADDING = 10;
    public static final int BUTTON_HEIGHT = 34;
    public static final int FIELD_HEIGHT = 28;

    // 🧩 Utility: Style Button
    public static void styleButton(JButton button) {
        button.setFont(BOLD_FONT);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, BUTTON_HEIGHT));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_PRESS);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER);
            }
        });
    }

    // 🧩 Utility: Style Label
    public static void styleLabel(JLabel label, boolean bold) {
        label.setFont(bold ? BOLD_FONT : DEFAULT_FONT);
        label.setForeground(TEXT_COLOR);
    }

    // 🧩 Utility: Style TextField
    public static void styleTextField(JTextField field) {
        field.setFont(DEFAULT_FONT);
        field.setForeground(TEXT_COLOR);
        field.setPreferredSize(new Dimension(200, FIELD_HEIGHT));
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    // 🧩 Utility: Create a padded panel
    public static JPanel createPaddedPanel(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setBackground(SECONDARY_COLOR);
        panel.setBorder(new EmptyBorder(STANDARD_PADDING, STANDARD_PADDING, STANDARD_PADDING, STANDARD_PADDING));
        return panel;
    }

    // 🧩 Utility: Vertical spacer
    public static Component verticalSpace(int height) {
        return Box.createVerticalStrut(height);
    }

    // 🧩 Utility: Apply consistent look to all children (like recursive styling)
    public static void applyStyleRecursively(Container parent) {
        for (Component comp : parent.getComponents()) {
            if (comp instanceof JButton btn) {
                styleButton(btn);
            } else if (comp instanceof JLabel lbl) {
                styleLabel(lbl, false);
            } else if (comp instanceof JTextField txt) {
                styleTextField(txt);
            } else if (comp instanceof JPanel pnl) {
                applyStyleRecursively(pnl);
            }
        }
    }

    public static JPanel createHelpLinkPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        JLabel helpLink = new JLabel("<HTML><U>Help</U></HTML>");
        helpLink.setForeground(Color.BLUE.darker());
        helpLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        helpLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new HelpFrame().setVisible(true);

            }
        });

        panel.add(helpLink);
        return panel;
    }
}
