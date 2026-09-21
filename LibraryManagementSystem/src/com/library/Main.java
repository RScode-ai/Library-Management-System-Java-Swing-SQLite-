package com.library;

import com.library.db.DBConnection;
import com.library.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Make sure the SQLite JDBC driver is on the classpath before we try to use it.
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "SQLite JDBC driver not found.\n" +
                            "Make sure sqlite-jdbc-*.jar is in the lib folder and included on the classpath.\n" +
                            "See README.md for the exact run command.",
                    "Missing Driver", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            DBConnection.initializeDatabase();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null,
                    "Could not set up the database:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Fall back to the default look and feel if the system one isn't available.
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
