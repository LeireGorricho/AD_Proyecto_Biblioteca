package InterfazGrafica;

import Clases.Cliente;
import Clases.Libro;
import Clases.Prestamo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AñadirPrestamo extends JFrame{
    private JPanel panelPrestamo;
    private JTextField fechatext;
    private JTextField dias_prestamo;
    private JComboBox cb_libro;
    private JComboBox cb_cliente;
    private JButton anadirButton;
    private JButton cancelarButton;

    public List<Libro> datosLibros = new ArrayList<Libro>();
    public List<Cliente> datosClientes = new ArrayList<Cliente>();

    public List<Prestamo> datosPrestamos = new ArrayList<Prestamo>();

    public AñadirPrestamo(List<Prestamo> p) {
        this.datosPrestamos = p;
        setContentPane(panelPrestamo);

        cargarDatosLibros();
        for (int i = 0; i < datosLibros.size(); i++) {
            cb_libro.addItem(datosLibros.get(i).getId() + " " + datosLibros.get(i).getNombre());
        }

        cargarDatosClientes();
        for (int i = 0; i < datosClientes.size(); i++) {
            cb_cliente.addItem(datosClientes.get(i).getId() + " " + datosClientes.get(i).getNombre() + " " + datosClientes.get(i).getApellido());
        }

        anadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirPrestamo();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Prestamos();
                frame.setSize(750, 400);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    public void cargarDatosLibros() {
        try {
            File file = new File("Libros.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datosLibros.clear();
            Libro libro;
            while ((libro = (Libro) fileobj.readObject()) != null) {
                datosLibros.add(libro);
            }
            fileobj.close();
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar acceder a los datos.");
        }
    }

    public void cargarDatosClientes() {
        try {
            File file = new File("Clientes.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datosClientes.clear();
            Cliente cliente;
            while ((cliente = (Cliente) fileobj.readObject()) != null) {
                datosClientes.add(cliente);
            }
            fileobj.close();
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar acceder a los datos.");
        }
    }

    public void anadirPrestamo() {
        try {
            String fecha = fechatext.getText();
            int dias = Integer.parseInt(dias_prestamo.getText());
            String libro = (String) cb_libro.getSelectedItem();
            String cliente = (String) cb_cliente.getSelectedItem();

            if (fecha.trim().equals("") || dias <= 0 ) {
                JOptionPane.showMessageDialog(null, "No se ha podido pedir el préstamo. Comprueba que los datos insertados son correctos");
            }else {
                // Esto es para controlar que no de error cuando no hay ningún préstamo
                int i = datosPrestamos.size();
                int id;
                if (i == 0){
                    id = 1;
                } else{
                    id = datosPrestamos.get(i - 1).getId() + 1;
                }
                datosPrestamos.add(new Prestamo(id, fecha, dias, libro, cliente));

                File file = new File("Prestamos.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                for (Prestamo dato : datosPrestamos) {
                    fileobj.writeObject(dato);
                }

                JOptionPane.showMessageDialog(null, "El préstamo se ha añadido correctamente.");

                fileobj.close();

                JFrame frame = new Prestamos();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduce valores correctos");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el archivo.");
        } catch (IOException e) {
            System.out.println("");
        }
    }
}
