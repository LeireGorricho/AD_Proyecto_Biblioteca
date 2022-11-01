package InterfazGrafica;

import Clases.*;
import com.thoughtworks.xstream.XStream;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuEmpleado extends JFrame{
    private JPanel panel1;
    private JLabel LabelMenu;
    private JButton librosButton;
    private JButton clientesButton;
    private JButton prestamosButton;
    private JButton salirButton;
    private JButton allFicherosButton;
    private JButton prestamosDOMButton;

    private List<Prestamo> datosPrestamos = new ArrayList<Prestamo>();

    public MenuEmpleado() {
        setContentPane(panel1);
        librosButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new LibrosEmpleado();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Clientes();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        prestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Prestamos();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Login();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });

        prestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    DOMImplementation implementation = builder.getDOMImplementation();
                    Document document = implementation.createDocument(null, "PrestamosDOM", null);
                    document.setXmlVersion("1.0");

                    for (int i = 0; i < datosPrestamos.size(); i++) {
                        Element raiz = document.createElement("prestamo");
                        document.getDocumentElement().appendChild(raiz);
                        crearElemento("id", String.valueOf(datosPrestamos.get(i).getId()), raiz, document);
                        crearElemento("fecha", datosPrestamos.get(i).getFecha(), raiz, document);
                        crearElemento("libro",datosPrestamos.get(i).getLibro(), raiz, document);
                        crearElemento("cliente",String.valueOf(datosPrestamos.get(i).getCliente()), raiz, document);
                    }

                    Source source = new DOMSource(document);
                    Result result = new StreamResult(new java.io.File("PrestamosDOM.xml"));
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(source, result);
                    Result console= new StreamResult(System.out);
                    transformer.transform(source, console);

                    JOptionPane.showMessageDialog(null, "Se ha exportado el fichero de préstamos a XML y se ha creado el fichero PrestamosDOM.xml");
                } catch (ParserConfigurationException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR al generar el XML");
                } catch (TransformerConfigurationException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR al generar el XML");
                } catch (TransformerException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR al generar el XML");
                }
            }
        });

        allFicherosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarLibros();
                exportarPrestamos();
                exportarClientes();
                exportarEmpleados();
            }
        });
    }

    static void crearElemento (String datoPlato, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoPlato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

    public void exportarEmpleados() {
        ObjectInputStream fileobj = null;
        ListaEmpleados listaEmpleados = null;
        try {
            //Se crea el flujo de salida
            File fichero = new File("Empleados.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);

            //Se leen todos los datos almacenados y se añaden a la clase ListaEmpleados
            Empleado empleado;
            listaEmpleados = new ListaEmpleados();
            while ((empleado = (Empleado) fileobj.readObject()) != null) {
                listaEmpleados.add(empleado);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase");
        }
        try {
            fileobj.close();
        } catch (IOException e) {
        }
        try {
            //Exportamos los datos con XStream
            XStream xstream = new XStream();
            xstream.alias("ListaEmpleados", ListaEmpleados.class);
            xstream.alias("DatosEmpleado", Empleado.class);
            xstream.addImplicitCollection(ListaEmpleados.class, "lista");
            xstream.toXML(listaEmpleados, new FileOutputStream("EmpleadosXML.xml"));
            JOptionPane.showMessageDialog(null, "Se han exportados los empleados y se ha creado el fichero EmpleadosXML.xml");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero");
        }
    }

    public void exportarClientes() {
        ObjectInputStream fileobj = null;
        ListaClientes listaClientes = null;
        try {
            //Se crea el flujo de salida
            File fichero = new File("Clientes.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            Cliente cliente;

            //Se leen todos los datos almacenados y se añaden a la clase listaClientes
            listaClientes = new ListaClientes();
            while ((cliente = (Cliente) fileobj.readObject()) != null) {
                listaClientes.add(cliente);
            }
        } catch (EOFException e) {
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase");
        }
        try {
            fileobj.close();
        } catch (IOException e) {
        }
        try {
            //Se usa XStream para exportar los datos
            XStream xstream = new XStream();
            xstream.alias("ListaClientes", ListaClientes.class);
            xstream.alias("DatosCliente", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            xstream.toXML(listaClientes, new FileOutputStream("ClientesXML.xml"));
            JOptionPane.showMessageDialog(null, "Se han exportados los clientes y se ha creado el fichero ClientesXML.xml");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        }
    }

    public void exportarLibros() {
        ObjectInputStream fileobj = null;
        ListaLibros listaLibros = null;
        try {
            //Se crea el flujo de salida
            File fichero = new File("Libros.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            Libro libro;

            //Se leen todos los datos almacenados y se añaden a la clase listaClientes
            listaLibros = new ListaLibros();
            while ((libro = (Libro) fileobj.readObject()) != null) {
                listaLibros.add(libro);
            }
        } catch (EOFException e) {
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase");
        }
        try {
            fileobj.close();
        } catch (IOException e) {
        }
        try {
            //Se usa XStream para exportar los datos
            XStream xstream = new XStream();
            xstream.alias("ListaLibros", ListaClientes.class);
            xstream.alias("DatosLibro", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            xstream.toXML(listaLibros, new FileOutputStream("LibrosXML.xml"));
            JOptionPane.showMessageDialog(null, "Se han exportados los libros y se ha creado el fichero LibrosXML.xml");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        }
    }

    public void exportarPrestamos() {
        ObjectInputStream fileobj = null;
        ListaPrestamos listaPrestamos = null;
        try {
            //Se crea el flujo de salida
            File fichero = new File("Prestamos.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            Prestamo prestamo;

            //Se leen todos los datos almacenados y se añaden a la clase listaClientes
            listaPrestamos = new ListaPrestamos();
            while ((prestamo = (Prestamo) fileobj.readObject()) != null) {
                listaPrestamos.add(prestamo);
            }
        } catch (EOFException e) {
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase");
        }
        try {
            fileobj.close();
        } catch (IOException e) {
        }
        try {
            //Se usa XStream para exportar los datos
            XStream xstream = new XStream();
            xstream.alias("ListaPrestamos", ListaClientes.class);
            xstream.alias("DatosPrestamo", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            xstream.toXML(listaPrestamos, new FileOutputStream("PrestamosXML.xml"));
            JOptionPane.showMessageDialog(null, "Se han exportados los prestamos y se ha creado el fichero PrestamosXML.xml");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        }
    }
}
