package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JTextField tfUsuario;
    private JTextField tfContrasena;
    private JLabel LabelInicioSesion;
    private JLabel LabelUsuario;
    private JLabel LabelContrasena;
    private JButton buttonEmpleado;
    private JButton buttonCliente;
    private JPanel panelLogin;

    public Login() {
        setContentPane(panelLogin);

        buttonEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new MenuEmpleado();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });

        buttonCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new MenuCliente();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }

}
