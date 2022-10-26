package Clases;

import java.io.Serializable;

public class Prestamo implements Serializable {

    int id;
    String fecha;
    int dias_prestamo;
    int id_libro;
    int id_cliente;

    // Constructor
    public Prestamo(int id, String fecha, int dias_prestamo, int id_libro, int id_cliente) {
        this.id = id;
        this.fecha = fecha;
        this.dias_prestamo = dias_prestamo;
        this.id_libro = id_libro;
        this.id_cliente = id_cliente;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDias_prestamo() {
        return dias_prestamo;
    }

    public void setDias_prestamo(int dias_prestamo) {
        this.dias_prestamo = dias_prestamo;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
