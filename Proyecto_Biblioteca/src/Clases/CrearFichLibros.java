package Clases;

import java.io.*;

public class CrearFichLibros {

    public static void main(String[] args) {

        File file = new File("Libros.dat");
        try {
            FileOutputStream fileout = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileout);

            int[] ids = {1, 2, 3, 4, 5};
            String[] nombres = {"El principito", "Ciudad en la niebla", "La celestina", "La casa de Bernarda Alba", "Orgullo y prejuicio"};
            String[] autores = {"Antoine de Saint Exupery", "Agustin de Foxa", "Fernando de Rojas", "Federico Garcia Lorca", "Jane Austen"};
            String[] generos = {"Infantil", "Poesia", "Novela, Tragicomedia", "Teatro", "Novela contemporanea"};
            String[] editoriales = {"Salamandra", "Renacimiento", "Catedra", "Catedra", "Ediciones Invisibles"};
            String[] idiomas = {"Castellano", "Castellano", "Castellano", "Castellano", "Castellano, Ingles"};
            int[] num_paginas = {96, 280, 368, 288, 440};

            for (int i = 0; i < ids.length; i++) {
                Libro nuevoLibro = new Libro(ids[i], nombres[i], autores[i], generos[i], editoriales[i], idiomas[i], num_paginas[i]);
                fileobj.writeObject(nuevoLibro);
            }

            fileobj.close();
            System.out.println("Se ha creado el fichero binario que almacena la información de los libros.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
