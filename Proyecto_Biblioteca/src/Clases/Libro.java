package Clases;

public class Libro {

    int id;
    String nombre;
    String autor;
    String genero;
    String editorial;
    String idioma;
    int num_paginas;

    // Constructor
    public Libro(int id, String nombre, String autor, String genero, String editorial, String idioma, int num_paginas) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.genero = genero;
        this.editorial = editorial;
        this.idioma = idioma;
        this.num_paginas = num_paginas;
    }
}
