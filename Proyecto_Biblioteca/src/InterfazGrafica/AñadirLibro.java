package InterfazGrafica;

import Clases.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AñadirLibro extends JFrame{
    private JPanel panelNuevoLibro;
    private JTextField textFieldnombre;
    private JTextField textFieldautor;
    private JTextField textFieldgenero;
    private JTextField textFieldeditorial;
    private JTextField textFieldidioma;
    private JLabel labelNombre;
    private JLabel labelAutor;
    private JLabel labelGenero;
    private JLabel labelEditorial;
    private JLabel labelIdioma;
    private JLabel labelNumPaginas;
    private JTextField textFieldnumpaginas;
    private JPanel panelBotones;
    private JButton anadirButton;
    private JButton cancelarButton;
    public List<Libro> datos = new ArrayList<Libro>();

    public AñadirLibro(List<Libro> l) {
        this.datos = l;
        setContentPane(panelNuevoLibro);
        anadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirLibro();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new LibrosEmpleado();
                frame.setSize(750, 400);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    public void anadirLibro() {
        try {
            String nombre = textFieldnombre.getText();
            String autor = textFieldautor.getText();
            String genero = textFieldgenero.getText();
            String editorial = textFieldeditorial.getText();
            String idioma = textFieldidioma.getText();
            int num_paginas = Integer.parseInt(textFieldnumpaginas.getText());

            if (nombre.trim().equals("") || autor.trim().equals("") || genero.trim().equals("") || editorial.trim().equals("") || idioma.trim().equals("") || String.valueOf(num_paginas).length() != 9) {
                JOptionPane.showMessageDialog(null, "No se ha podido añadir el libro. Comprueba que los datos insertados son correctos");
            }else {
                // Esto es para controlar que no de error cuando no hay ningún libro
                int i = datos.size();
                int id;
                if (i == 0){
                    id = 1;
                } else{
                    id = datos.get(i - 1).getId() + 1;
                }

                datos.add(new Libro(id, nombre, autor, genero, editorial, idioma, num_paginas));

                File file = new File("Libros.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                for (Libro dato : datos) {
                    fileobj.writeObject(dato);
                }

                JOptionPane.showMessageDialog(null, "El libro se ha añadido correctamente.");

                fileobj.close();

                JFrame frame = new LibrosEmpleado();
                frame.setSize(750, 400);
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
