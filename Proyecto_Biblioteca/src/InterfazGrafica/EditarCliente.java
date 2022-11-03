package InterfazGrafica;

import Clases.Cliente;
import Clases.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class EditarCliente extends JFrame {

    private JPanel panelCliente;
    private JTextField textFieldusuario;
    private JTextField textFieldcontrasena;
    private JTextField textFieldnombre;
    private JTextField textFieldapellido;
    private JTextField textFieldtelefono;
    private JTextField textFieldemail;
    private JButton editarButton;
    private JButton cancelarButton;

    public EditarCliente(int id, List<Cliente> datos) {
        setContentPane(panelCliente);
        for (Cliente dato : datos) {
            if (dato.getId() == id) {
                textFieldusuario.setText(dato.getUsuario());
                textFieldcontrasena.setText(dato.getContrasenia());
                textFieldnombre.setText(dato.getNombre());
                textFieldapellido.setText(dato.getApellido());
                textFieldtelefono.setText(String.valueOf(dato.getTelefono()));
                textFieldemail.setText(dato.getEmail());
            }
        }


        String usuario = textFieldusuario.getText();
        String contrasena = textFieldcontrasena.getText();
        String nombre = textFieldnombre.getText();
        String apellido = textFieldapellido.getText();
        int telefono = Integer.parseInt(textFieldtelefono.getText());
        String email = textFieldemail.getText();

        if (usuario.trim().equals("") || contrasena.trim().equals("") || nombre.trim().equals("") || apellido.trim().equals("")|| String.valueOf(telefono).length() != 9 || email.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "No se ha podido editar el cliente. Comprueba que los datos insertados son correctos");
        }else {
            for (Cliente dato : datos) {
                if (dato.getId() == id) {
                    dato.setUsuario(usuario);
                    dato.setContrasenia(dato.getContrasenia());
                    dato.setNombre(nombre);
                    dato.setApellido(apellido);
                    dato.setTelefono(telefono);
                    dato.setEmail(email);
                }
            }
        }
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String usuario = textFieldusuario.getText();
                    String contrasena = textFieldcontrasena.getText();
                    String nombre = textFieldnombre.getText();
                    String apellido = textFieldapellido.getText();
                    int telefono = Integer.parseInt(textFieldtelefono.getText());
                    String email = textFieldemail.getText();

                    if (usuario.trim().equals("") || contrasena.trim().equals("") || nombre.trim().equals("") || String.valueOf(telefono).length() != 9 || email.trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "No se ha podido editar el libro. Comprueba que los datos insertados son correctos");
                    }else {
                        for (Cliente dato : datos) {
                            if (dato.getId() == id) {
                                dato.setUsuario(usuario);
                                dato.setContrasenia(contrasena);
                                dato.setNombre(nombre);
                                dato.setApellido(apellido);
                                dato.setTelefono(telefono);
                                dato.setEmail(email);
                            }
                        }

                        File file = new File("Clientes.dat");
                        FileOutputStream fileo = new FileOutputStream(file);
                        ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                        for (Cliente dato : datos) {
                            fileobj.writeObject(dato);
                        }

                        fileobj.close();

                        JOptionPane.showMessageDialog(null, "Se ha editado el cliente correctamente.");

                        JFrame frame = new Clientes();
                        frame.setSize(750, 400);
                        frame.setVisible(true);
                        dispose();
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha podido encontrar el archivo.");
                } catch (IOException ex) {
                    System.out.println("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce valores numéricos correctos");
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Clientes();
                frame.setSize(750, 400);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
