package ui;

import javax.swing.*;
import java.awt.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author pc
 */
/**
 * Abstract base class for all frames that require login. Sets common look and
 * feel and window settings.
 */
public abstract class BaseFrame extends JFrame {

    public BaseFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());
        setResizable(false);
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/static/icon-57x57.png"));
        setIconImage(icon.getImage());
        UIManager.put("Label.font", UIStyle.DEFAULT_FONT);
        UIManager.put("Button.font", UIStyle.DEFAULT_FONT);
        UIManager.put("TextField.font", UIStyle.DEFAULT_FONT);
    }

    protected void closeAllOpenForms() {
        for (Window window : Window.getWindows()) {
            if (window instanceof JFrame) {
                window.dispose(); // Close each JFrame
            }
        }
    }
}
