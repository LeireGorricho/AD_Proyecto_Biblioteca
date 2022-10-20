package Clases;

public class Prestamos {

    int id;
    String fecha;
    int dias_prestamo;
    int id_libro;
    int id_cliente;

    // Constructor
    public Prestamos(int id, String fecha, int dias_prestamo, int id_libro, int id_cliente) {
        this.id = id;
        this.fecha = fecha;
        this.dias_prestamo = dias_prestamo;
        this.id_libro = id_libro;
        this.id_cliente = id_cliente;
    }
}
