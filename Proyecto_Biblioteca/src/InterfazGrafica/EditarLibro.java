package InterfazGrafica;

import Clases.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class EditarLibro extends JFrame{
    private JPanel panelEditarLibro;
    private JTextField textFieldnombre;
    private JTextField textFieldautor;
    private JTextField textFieldgenero;
    private JTextField textFieldeditorial;
    private JTextField textFieldidioma;
    private JTextField textFieldnumpaginas;
    private JButton editarButton;
    private JButton cancelarButton;

    public EditarLibro(int id, List<Libro> datos) {
        setContentPane(panelEditarLibro);
        for (Libro dato : datos) {
            if (dato.getId() == id) {
                textFieldnombre.setText(dato.getNombre());
                textFieldautor.setText(dato.getAutor());
                textFieldgenero.setText(dato.getGenero());
                textFieldeditorial.setText(dato.getEditorial());
                textFieldidioma.setText(dato.getIdioma());
                textFieldnumpaginas.setText(String.valueOf(dato.getNum_paginas()));
            }
        }
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (Libro dato : datos) {
                        if (dato.getId() == id) {
                            dato.setNombre(textFieldnombre.getText());
                            dato.setAutor(textFieldautor.getText());
                            dato.setGenero(textFieldgenero.getText());
                            dato.setEditorial(textFieldeditorial.getText());
                            dato.setIdioma(textFieldidioma.getText());
                            dato.setNum_paginas(Integer.parseInt(textFieldnumpaginas.getText()));
                        }
                    }

                    File file = new File("Libros.dat");
                    FileOutputStream fileo = new FileOutputStream(file);
                    ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                    for (Libro dato : datos) {
                        fileobj.writeObject(dato);
                    }

                    fileobj.close();

                    JOptionPane.showMessageDialog(null, "Se ha editado el libro correctamente.");

                    JFrame frame = new LibrosEmpleado();
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
                JFrame frame = new LibrosEmpleado();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
