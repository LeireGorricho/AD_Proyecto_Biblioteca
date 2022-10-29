package InterfazGrafica;

import Clases.Prestamo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Prestamos extends JFrame{
    private JTable table1;
    private JButton volverAlMenúButton;
    private JButton eliminarButton;
    private JButton anadirButton;
    private JButton recargarTablaButton;
    private JPanel panelPrestamos;

    public List<Prestamo> datos = new ArrayList<Prestamo>();

    public Prestamos() {
        setContentPane(panelPrestamos);
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
                JFrame frame = new AñadirPrestamo(datos);
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona en la tabla qué préstamo quieres eliminar");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    eliminarPrestamo(id);
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
    }

    public void cargarDatos() {
        try {
            File file = new File("Prestamos.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Prestamo prestamo;
            while ((prestamo = (Prestamo) fileobj.readObject()) != null) {
                datos.add(prestamo);
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
        String[] nombreColumnas = {"Id", "Fecha", "Dias_prestamo", "Libro", "Cliente"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][5];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getFecha());
            d[i][2] = String.valueOf(datos.get(i).getDias_prestamo());
            d[i][3] = String.valueOf(datos.get(i).getLibro());
            d[i][4] = String.valueOf(datos.get(i).getCliente());
        }
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    public void eliminarPrestamo(int id) {
        cargarDatos();
        List<Prestamo> nuevos = new ArrayList<>();
        for (Prestamo dato : datos) {
            if (dato.getId() != id) {
                nuevos.add(dato);
            }
        }

        try {
            ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream("Prestamos.dat"));

            for (Prestamo dato : nuevos) {
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
