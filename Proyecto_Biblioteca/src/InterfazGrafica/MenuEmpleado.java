package InterfazGrafica;

import Clases.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEmpleado extends JFrame{
    private JPanel panel1;
    private JLabel LabelMenu;
    private JButton librosButton;
    private JButton clientesButton;
    private JButton prestamosButton;
    private JButton salirButton;

    public MenuEmpleado() {
        setContentPane(panel1);
        librosButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new LibrosEmpleado();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Clientes();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        prestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Prestamos();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }
}
