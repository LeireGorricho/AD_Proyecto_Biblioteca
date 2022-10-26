package InterfazGrafica;

import Clases.Cliente;

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
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (Cliente dato : datos) {
                        if (dato.getId() == id) {
                            dato.setUsuario(textFieldusuario.getText());
                            dato.setContrasenia(textFieldcontrasena.getText());
                            dato.setNombre(textFieldnombre.getText());
                            dato.setApellido(textFieldapellido.getText());
                            dato.setTelefono(Integer.parseInt(textFieldtelefono.getText()));
                            dato.setEmail(textFieldemail.getText());
                        }
                    }

                    File file = new File("Cliente.dat");
                    FileOutputStream fileo = new FileOutputStream(file);
                    ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                    for (Cliente dato : datos) {
                        fileobj.writeObject(dato);
                    }

                    fileobj.close();

                    JOptionPane.showMessageDialog(null, "Se ha editado el cliente correctamente.");

                    JFrame frame = new Clientes();
                    frame.setSize(500, 300);
                    frame.setVisible(true);
                    dispose();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha podido encontrar el archivo.");
                } catch (IOException ex) {
                    System.out.println("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce valores correctos");
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Clientes();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}