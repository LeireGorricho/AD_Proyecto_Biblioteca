package InterfazGrafica;

import Clases.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Clientes extends JFrame{
    private JTable table1;
    private JButton volverAlMenúButton;
    private JButton eliminarButton;
    private JButton anadirButton;
    private JButton editarButton;
    private JButton recargarTablaButton;
    private JPanel panelCliente;

    public List<Cliente> datos = new ArrayList<Cliente>();

    public Clientes() {
        setContentPane(panelCliente);
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
                JFrame frame = new AñadirCliente(datos);
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                eliminarCliente(id);
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
                int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                cargarDatos();
                JFrame frame = new EditarCliente(id, datos);
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    public void cargarDatos() {
        try {
            File file = new File("Clientes.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Cliente cliente;
            while ((cliente = (Cliente) fileobj.readObject()) != null) {
                datos.add(cliente);
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
        String[] nombreColumnas = {"id", "Usuario", "Contraseña", "Nombre", "Apellido", "Telefono", "Email"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][7];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getUsuario());
            d[i][2] = String.valueOf(datos.get(i).getContrasenia());
            d[i][3] = String.valueOf(datos.get(i).getNombre());
            d[i][4] = String.valueOf(datos.get(i).getApellido());
            d[i][5] = String.valueOf(datos.get(i).getTelefono());
            d[i][6] = String.valueOf(datos.get(i).getEmail());
        }
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    public void eliminarCliente(int id) {
        cargarDatos();
        List<Cliente> nuevos = new ArrayList<>();
        for (Cliente dato : datos) {
            if (dato.getId() != id) {
                nuevos.add(dato);
            }
        }

        try {
            ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream("Clientes.dat"));

            for (Cliente dato : nuevos) {
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
