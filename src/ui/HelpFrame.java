/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author pc
 */
/**
 * Displays static help content with contact details.
 */
public class HelpFrame extends BaseFrame {

    public HelpFrame() {
        super("Help & Support");

        JPanel panel = UIStyle.createPaddedPanel(new BorderLayout());

        JTextArea helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setFont(UIStyle.DEFAULT_FONT);
        helpText.setText("""
                🔒 Having trouble logging in?

                - Make sure you're using the correct test credentials:
                  Username: testuser
                  Password: 1234

                💡 Need more help?

                Please contact our support team:
                📧 Email: support@atm-sim.com
                ☎️ Phone: +61 3 9999 8888

                This application is a simulation and does not connect to real banking systems.
                """);

        panel.add(new JScrollPane(helpText), BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
