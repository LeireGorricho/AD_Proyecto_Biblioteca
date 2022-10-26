package Clases;

import java.io.Serializable;

public class Libro implements Serializable{

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

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNum_paginas() {
        return num_paginas;
    }

    public void setNum_paginas(int num_paginas) {
        this.num_paginas = num_paginas;
    }
}
