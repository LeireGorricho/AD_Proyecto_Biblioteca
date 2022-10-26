package Clases;

import java.io.*;

public class CrearFichEmpleados {

    public static void main(String[] args) {

        File file = new File("Empleados.dat");
        try {
            FileOutputStream fileout = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileout);

            int[] ids = {1, 2, 3, 4, 5};
            String[] usuario = {"Empleado1", "Empleado2", "Empleado3", "Empleado4", "Empleado5"};
            String[] contrasena = {"12345", "12345", "12345", "12345", "12345"};

            for (int i = 0; i < ids.length; i++) {
                Empleado nuevoEmpleado = new Empleado(ids[i], usuario[i], contrasena[i]);
                fileobj.writeObject(nuevoEmpleado);
            }

            fileobj.close();
            System.out.println("Se ha creado el fichero binario que almacena la información de los empleados.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
