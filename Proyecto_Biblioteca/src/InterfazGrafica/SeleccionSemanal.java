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
import java.util.Random;

public class SeleccionSemanal extends JFrame{
    private JPanel seleccionpanel;
    private JButton volverAlMenuButton;

    private List<Libro> datos = new ArrayList<Libro>();

    public SeleccionSemanal (){
        setContentPane(seleccionpanel);
        modificarTabla();

        volverAlMenuButton.addActionListener(new ActionListener() {
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
            //creamos el flujo de salida
            File file = new File("Libros.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            //Limpiamos el array por si acaso
            datos.clear();
            //añadimos los objetos que hay en el .DAT al array uno a uno
            Plato plato;
            while ((plato = (Plato) fileobj.readObject()) != null) {
                datos.add(plato);
            }
            fileobj.close();
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error al intentar acceder al los datos.");
        }
    }

    //Funcion que genera la selección semanal
    public void generarMenu() {
        Random r = new Random();
        cargarDatos();
        if (datos.size() != 0) {
            List<Libro> seleccion = new ArrayList<Libro>();
            for (Libro dato : datos) {
                if (dato.getCategoria() == 1) {
                    primeros.add(dato);
                } else if (dato.getCategoria() == 2) {
                    segundos.add(dato);
                } else if (dato.getCategoria() == 3) {
                    postres.add(dato);
                }
            }

            //Elegimos uno de cada categoria
            int p = primeros.size();
            int s = segundos.size();
            int po = postres.size();
            //control por si no hay primeros generados
            if (primeros.size() == 0) {
                menu.add(new Plato(""));
            } else if (p == 1) {
                menu.add(primeros.get(0));
            } else {
                menu.add(primeros.get(r.nextInt(0, p)));
            }
            //control por si no hay segundos generados
            if (segundos.size() == 0) {
                menu.add(new Plato(""));
            } else if (s == 1) {
                menu.add(segundos.get(0));
            } else {
                menu.add(segundos.get(r.nextInt(0, s)));
            }
            //control por si no hay postres generados
            if (postres.size() == 0) {
                menu.add(new Plato(""));
            } else if (po == 1) {
                menu.add(postres.get(0));
            } else {
                menu.add(postres.get(r.nextInt(0, po)));
            }

            //Los mostramos en los TextField
            primeroField.setText(menu.get(0).getNombre());
            segundoField.setText(menu.get(1).getNombre());
            postreField.setText(menu.get(2).getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "No hay libros en la biblioteca");
        }
    }
}
