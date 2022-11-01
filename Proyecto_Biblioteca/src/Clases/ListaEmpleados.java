package Clases;

import java.util.ArrayList;
import java.util.List;

// Pasar los .dat de empleados a XML con XStream
public class ListaEmpleados {
    private List<Empleado> lista = new ArrayList<Empleado>();

    //Contructor
    public ListaEmpleados() {
    }

    public void add(Empleado e) {
        lista.add(e);
    }
}
