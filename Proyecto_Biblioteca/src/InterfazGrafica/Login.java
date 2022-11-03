package InterfazGrafica;

import Clases.Cliente;
import Clases.Empleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Login extends JFrame{
    private JTextField tfUsuario;
    private JTextField tfContrasena;
    private JLabel LabelInicioSesion;
    private JLabel LabelUsuario;
    private JLabel LabelContrasena;
    private JButton buttonEmpleado;
    private JButton buttonCliente;
    private JPanel panelLogin;

    public List<Empleado> datosEmpleados = new ArrayList<Empleado>();

    public List<Cliente> datosClientes = new ArrayList<Cliente>();

    public Login() {
        setContentPane(panelLogin);

        buttonEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObjectInputStream fileobj = null;
                boolean correcto = false;
                try {
                    File file = new File("Empleados.dat");
                    FileInputStream fileo = new FileInputStream(file);
                    fileobj = new ObjectInputStream(fileo);

                    Empleado usuario;
                    while ((usuario = (Empleado) fileobj.readObject()) != null) {
                        if (usuario.getUsuario().equals(tfUsuario.getText()) && usuario.getContrasenia().equals(tfContrasena.getText())) {
                           correcto = true;
                        }
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo de login.");
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la clase necesaria.");
                }

                if (correcto){
                    JFrame frame = new MenuEmpleado();
                    frame.setSize(500, 300);
                    frame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No existe un empleado con este usuario y contraseña");
                }

                try {
                    if (fileobj != null) {
                        fileobj.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado");
                }
            }
        });

        buttonCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObjectInputStream fileobj = null;
                boolean correcto = false;
                try {
                    File file = new File("Clientes.dat");
                    FileInputStream fileo = new FileInputStream(file);
                    fileobj = new ObjectInputStream(fileo);

                    Cliente usuario;
                    while ((usuario = (Cliente) fileobj.readObject()) != null) {
                        if (usuario.getUsuario().equals(tfUsuario.getText()) && usuario.getContrasenia().equals(tfContrasena.getText())) {
                            correcto = true;
                        }
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo de login.");
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la clase necesaria.");
                }

                if (correcto){
                    JFrame frame = new MenuCliente();
                    frame.setSize(300, 200);
                    frame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No existe un cliente con este usuario y contraseña");
                }

                try {
                    if (fileobj != null) {
                        fileobj.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado");
                }
            }
        });
    }
}
