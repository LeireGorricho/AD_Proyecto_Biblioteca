package Clases;

import java.util.ArrayList;
import java.util.List;

// Pasar los .dat de empleados a XML con XStream
public class ListaLibros {
    private List<Libro> lista = new ArrayList<Libro>();

    //Contructor
    public ListaLibros() {
    }

    public void add(Libro l) {
        lista.add(l);
    }
}
