package Clases;

import java.io.Serializable;

public class Prestamo implements Serializable {

    int id;
    String fecha;
    int dias_prestamo;
    String libro;
    String cliente;

    // Constructor
    public Prestamo(int id, String fecha, int dias_prestamo, String libro, String cliente) {
        this.id = id;
        this.fecha = fecha;
        this.dias_prestamo = dias_prestamo;
        this.libro = libro;
        this.cliente = cliente;
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

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
