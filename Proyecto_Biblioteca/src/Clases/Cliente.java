package Clases;

public class Cliente {

    int id;
    String usuario;
    String contrasenia;
    String nombre;
    String apellidos;
    int telefono;
    String email;

    // Constructor
    public Cliente(int id, String usuario, String contrasenia, String nombre, String apellidos, int telefono, String email) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }
}
