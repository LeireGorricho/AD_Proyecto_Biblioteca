package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCliente extends JFrame{
    private JLabel LabelMenu;
    private JButton salirButton;
    private JButton seleccionSemanalButton;
    private JButton verLibrosButton;
    private JPanel panel1;

    public MenuCliente() {
        setContentPane(panel1);
        verLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new LibrosCliente();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        seleccionSemanalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar selección semanal
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Login();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
