package com.clases;

public class Asesoria {

    private String estudiante;
    private String usuario;
    private String asesor;
    private String motivo;
    private String fecha;
    private String hora;

    public Asesoria(String estudiante, String usuario, String asesor, String motivo, String fecha, String hora) {
        this.estudiante = estudiante;
        this.usuario = usuario;
        this.asesor = asesor;
        this.motivo = motivo;
        this.fecha = fecha;
        this.hora = hora;
    }
    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Asesoria{" +
                "estudiante='" + estudiante + '\'' +
                ", asesor='" + asesor + '\'' +
                ", motivo='" + motivo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}