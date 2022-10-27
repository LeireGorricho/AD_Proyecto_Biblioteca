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
                    String nombre = textFieldnombre.getText();
                    String autor = textFieldautor.getText();
                    String genero = textFieldgenero.getText();
                    String editorial = textFieldeditorial.getText();
                    String idioma = textFieldidioma.getText();
                    int num_paginas = Integer.parseInt(textFieldnumpaginas.getText());

                    if (nombre.trim().equals("") || autor.trim().equals("") || genero.trim().equals("") || editorial.trim().equals("") || idioma.trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "No se ha podido editar el libro. Comprueba que los datos insertados son correctos");
                    }else {
                        for (Libro dato : datos) {
                            if (dato.getId() == id) {
                                dato.setNombre(nombre);
                                dato.setAutor(autor);
                                dato.setGenero(genero);
                                dato.setEditorial(editorial);
                                dato.setIdioma(idioma);
                                dato.setNum_paginas(num_paginas);
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
                JFrame frame = new LibrosEmpleado();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
