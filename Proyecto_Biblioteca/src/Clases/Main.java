package Clases;

import InterfazGrafica.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new Login();
                frame.setSize(500, 300);
                frame.setVisible(true);
            }
        });
    }
}