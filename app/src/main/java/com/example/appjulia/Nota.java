package com.example.appjulia;

import androidx.annotation.NonNull;

public class Nota {
    private long id;
    private String titulo;
    private String texto;

    public Nota(long id, String titulo, String texto) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @NonNull
    @Override
    public String toString() {
        if (titulo != null && !titulo.isEmpty() && texto != null && !texto.isEmpty()) {
            return "Título: " + titulo + "\nTexto: " + texto;
        } else if (titulo != null && !titulo.isEmpty()) {
            return "Título: " + titulo;
        } else {
            return "Texto: " + texto;
        }
    }
}
