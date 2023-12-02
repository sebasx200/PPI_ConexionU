package com.clases.clases_tabla;

import javafx.beans.property.SimpleStringProperty;

public class NotificacionesTabla {

    private SimpleStringProperty titulo;
    private SimpleStringProperty fechaHora;
    private SimpleStringProperty emisor;
    private SimpleStringProperty receptor;
    private SimpleStringProperty mensaje;

    public NotificacionesTabla(String titulo, String fechaHora, String emisor, String receptor, String mensaje) {
        this.titulo = new SimpleStringProperty (titulo);
        this.fechaHora = new SimpleStringProperty (fechaHora);
        this.emisor = new SimpleStringProperty (emisor);
        this.receptor = new SimpleStringProperty (receptor);
        this.mensaje = new SimpleStringProperty (mensaje);
    }

    public String getTitulo() {
        return titulo.get();
    }

    public SimpleStringProperty tituloProperty() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getFechaHora() {
        return fechaHora.get();
    }

    public SimpleStringProperty fechaHoraProperty() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora.set(fechaHora);
    }

    public String getEmisor() {
        return emisor.get();
    }

    public SimpleStringProperty emisorProperty() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor.set(emisor);
    }

    public String getReceptor() {
        return receptor.get();
    }

    public SimpleStringProperty receptorProperty() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor.set(receptor);
    }

    public String getMensaje() {
        return mensaje.get();
    }

    public SimpleStringProperty mensajeProperty() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje.set(mensaje);
    }
}
