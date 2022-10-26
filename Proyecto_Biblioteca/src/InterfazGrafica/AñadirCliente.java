package InterfazGrafica;

import Clases.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AñadirCliente extends JFrame{
    private JTextField textFieldusuario;
    private JTextField textFieldcontrasena;
    private JTextField textFieldnombre;
    private JTextField textFieldapellido;
    private JTextField textFieldtelefono;
    private JTextField textFieldemail;
    private JButton anadirButton;
    private JButton cancelarButton;
    private JPanel panelNuevoCliente;
    public List<Cliente> datos = new ArrayList<Cliente>();

    public AñadirCliente(List<Cliente> c) {
        this.datos = c;
        setContentPane(panelNuevoCliente);
        anadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirLibro();
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

    public void anadirLibro() {
        try {
            String usuario = textFieldusuario.getText();
            String contrasena = textFieldcontrasena.getText();
            String nombre = textFieldnombre.getText();
            String apellido = textFieldapellido.getText();
            int telefono = Integer.parseInt(textFieldtelefono.getText());
            String email = textFieldemail.getText();

            int id = datos.get(datos.size() - 1).getId() + 1;

            datos.add(new Cliente(id, usuario, contrasena, nombre, apellido, telefono, email));

            File file = new File("Clientes.dat");
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            for (Cliente dato : datos) {
                fileobj.writeObject(dato);
            }

            JOptionPane.showMessageDialog(null, "El cliente se ha añadido correctamente.");

            fileobj.close();

            JFrame frame = new Clientes();
            frame.setSize(500, 300);
            frame.setVisible(true);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduce valores correctos");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el archivo.");
        } catch (IOException e) {
            System.out.println("");
        }
    }
}
