package Clases;

import java.io.Serializable;
public class Empleado implements Serializable{

    int id;
    String usuario;
    String contrasenia;

    // Constructor
    public Empleado(int id, String usuario, String contrasenia) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
