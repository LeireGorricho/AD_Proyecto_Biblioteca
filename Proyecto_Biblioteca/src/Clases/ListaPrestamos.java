package Clases;

import java.util.ArrayList;
import java.util.List;

// Pasar los .dat de empleados a XML con XStream
public class ListaPrestamos {
    private List<Prestamo> lista = new ArrayList<Prestamo>();

    //Contructor
    public ListaPrestamos() {
    }

    public void add(Prestamo p) {
        lista.add(p);
    }
}
