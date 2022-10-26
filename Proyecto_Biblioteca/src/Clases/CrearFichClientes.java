package Clases;

import java.io.*;

public class CrearFichClientes {

    public static void main(String[] args) {

        File file = new File("Clientes.dat");
        try {
            FileOutputStream fileout = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileout);

            int[] ids = {1, 2, 3, 4, 5};
            String[] usuarios = {"PepeGarcia", "MariaPerez"};
            String[] contrasenas = {"pepe12345", "maria12345", };
            String[] nombres = {"Pepe", "Maria", };
            String[] apellidos = {"Garcia", "Perez", };
            int[] telefonos = {678123456, 654891243, };
            String[] emails = {"pepegarcia@gmail.com", "mariaperez@gmail.com", };

            for (int i = 0; i < ids.length; i++) {
                Cliente nuevoCliente = new Cliente(ids[i], usuarios[i], contrasenas[i], nombres[i], apellidos[i], telefonos[i], emails[i]);
                fileobj.writeObject(nuevoCliente);
            }

            fileobj.close();
            System.out.println("Se ha creado el fichero binario que almacena la información de los clientes.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
