package com.clases.modelos;

public class Notificacion {
    private String titulo;
    private String emisor;
    private String fecha_hora;
    private String mensaje;
    private String receptor;

    public Notificacion(String titulo, String emisor, String fecha_hora, String mensaje, String receptor) {
        this.titulo = titulo;
        this.emisor = emisor;
        this.fecha_hora = fecha_hora;
        this.mensaje = mensaje;
        this.receptor = receptor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "titulo='" + titulo + '\'' +
                ", emisor='" + emisor + '\'' +
                ", fecha_hora='" + fecha_hora + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", receptor='" + receptor + '\'' +
                '}';
    }
}
