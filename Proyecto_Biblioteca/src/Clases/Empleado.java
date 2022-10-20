package Clases;

public class Empleado {

    int id;
    String usuario;
    String contrasenia;
    String apellido;
    int telefono;
    String email;
    double salario;

    // Constructor
    public Empleado(int id, String usuario, String contrasenia, String apellido, int telefono, String email, double salario) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.salario = salario;
    }
}
