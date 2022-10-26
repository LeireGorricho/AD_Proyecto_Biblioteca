package Clases;

import java.io.*;

public class CrearFichPrestamos {

    public static void main(String[] args) {

        File file = new File("Prestamos.dat");
        try {
            FileOutputStream fileout = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileout);

            int[] ids = {1, 2, 3, 4, 5};
            String[] fechas = {};
            int[] dias_prestamos = {};
            int[] ids_cliente = {};
            int[] ids_libro = {};

            for (int i = 0; i < ids.length; i++) {
                Prestamo nuevoPrestamo = new Prestamo(ids[i], fechas[i], dias_prestamos[i], ids_cliente[i], ids_libro[i]);
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
