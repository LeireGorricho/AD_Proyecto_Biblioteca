package Clases;

import java.util.ArrayList;
import java.util.List;

// Se usa para pasar los .dat de clientes a XML con XStream
public class ListaClientes {
    private List<Cliente> lista = new ArrayList<Cliente>();

    //Contructor
    public ListaClientes() {
    }

    public void add(Cliente c) {
        lista.add(c);
    }
}
