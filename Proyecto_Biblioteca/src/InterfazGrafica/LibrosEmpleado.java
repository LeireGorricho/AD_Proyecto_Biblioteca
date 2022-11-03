package InterfazGrafica;

import Clases.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibrosEmpleado extends JFrame{
    private JTable table1;
    private JButton volverAlMenúButton;
    private JButton eliminarButton;
    private JButton editarButton;
    private JButton anadirButton;
    private JPanel panelLibros;
    private JButton recargarTablaButton;

    public List<Libro> datos = new ArrayList<Libro>();

    public LibrosEmpleado() {
        setContentPane(panelLibros);
        modificarTabla();

        recargarTablaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarTabla();
            }
        });
        anadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
                JFrame frame = new AñadirLibro(datos);
                frame.setSize(600, 350);
                frame.setVisible(true);
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona en la tabla qué libro quieres eliminar");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    eliminarLibro(id);
                }
            }
        });
        volverAlMenúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new MenuEmpleado();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona en la tabla qué libro quieres editar");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    cargarDatos();
                    JFrame frame = new EditarLibro(id, datos);
                    frame.setSize(600, 350);
                    frame.setVisible(true);
                    dispose();
                }
            }
        });
    }

    public void cargarDatos() {
        try {
            File file = new File("Libros.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Libro libro;
            while ((libro = (Libro) fileobj.readObject()) != null) {
                datos.add(libro);
            }
            fileobj.close();
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar acceder a los datos.");
        }
    }

    public void modificarTabla() {
        cargarDatos();
        String[] nombreColumnas = {"id", "Nombre", "Autor", "Genero", "Editorial", "Idioma", "NumPaginas"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][7];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getNombre());
            d[i][2] = String.valueOf(datos.get(i).getAutor());
            d[i][3] = String.valueOf(datos.get(i).getGenero());
            d[i][4] = String.valueOf(datos.get(i).getEditorial());
            d[i][5] = String.valueOf(datos.get(i).getIdioma());
            d[i][6] = String.valueOf(datos.get(i).getNum_paginas());
        }
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    public void eliminarLibro(int id) {
        cargarDatos();
        List<Libro> nuevos = new ArrayList<>();
        for (Libro dato : datos) {
            if (dato.getId() != id) {
                nuevos.add(dato);
            }
        }

        try {
            ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream("Libros.dat"));

            for (Libro dato : nuevos) {
                fileobj.writeObject(dato);
            }

            fileobj.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo de datos.");
        } catch (IOException e) {
            System.out.println("");
        }

        modificarTabla();
    }
}
