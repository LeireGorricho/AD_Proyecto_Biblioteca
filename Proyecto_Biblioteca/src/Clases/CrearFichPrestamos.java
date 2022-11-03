package Clases;

import java.io.*;

public class CrearFichPrestamos {

    public static void main(String[] args) {

        File file = new File("Prestamos.dat");
        try {
            FileOutputStream fileout = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileout);

            int[] ids = {1, 2, 3};
            String[] fechas = {"15/10/2022", "10/10/2022", "20/10/2022"};
            int[] dias_prestamos = {10, 15, 7};
            String[] libros = {"El principito", "La celestina", "Orgullo y prejuicio"};
            String[] clientes = {"cliente", "MariaPerez", "PepeGarcia"};


            for (int i = 0; i < ids.length; i++) {
                Prestamo nuevoPrestamo = new Prestamo(ids[i], fechas[i], dias_prestamos[i], libros[i], clientes[i]);
                fileobj.writeObject(nuevoPrestamo);
            }

            fileobj.close();
            System.out.println("Se ha creado el fichero binario que almacena la información de los préstamos.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
