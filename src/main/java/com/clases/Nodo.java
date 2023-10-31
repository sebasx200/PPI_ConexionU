package com.clases;

public class Nodo {
    private Nodo liga;
    private Asesoria asesoria;

    public Nodo() {
    }
    public Nodo(Nodo liga, Asesoria asesoria) {
        this.liga = liga;
        this.asesoria = asesoria;
    }
    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }

    public Asesoria getAsesoria() {
        return asesoria;
    }

    public void setAsesoria(Asesoria asesoria) {
        this.asesoria = asesoria;
    }
}
