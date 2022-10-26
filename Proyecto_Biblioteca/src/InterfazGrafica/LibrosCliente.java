package InterfazGrafica;

import Clases.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LibrosCliente extends JFrame{
    private JTable table1;
    private JButton volverAlMenúButton;
    private JPanel panelLibros;

    public List<Libro> datos = new ArrayList<Libro>();

    public LibrosCliente() {
        setContentPane(panelLibros);
        modificarTabla();

        volverAlMenúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new MenuCliente();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
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
}
