package com.clases.clases_tabla;

import javafx.beans.property.SimpleStringProperty;

public class AsesoriaTabla {

    private SimpleStringProperty estudiante;
    private javafx.beans.property.SimpleStringProperty usuario;
    private SimpleStringProperty asesor;
    private SimpleStringProperty motivo;
    private SimpleStringProperty fecha;
    private SimpleStringProperty hora;

    public AsesoriaTabla(String estudiante, String usuario, String asesor, String motivo, String fecha, String hora) {
        this.estudiante = new SimpleStringProperty(estudiante);
        this.usuario = new SimpleStringProperty(usuario);
        this.asesor = new SimpleStringProperty(asesor);
        this.motivo = new SimpleStringProperty(motivo);
        this.fecha = new SimpleStringProperty(fecha);
        this.hora = new SimpleStringProperty(hora);
    }

    public String getEstudiante() {
        return estudiante.get();
    }
    public void setEstudiante(String estudiante) {
        this.estudiante = new SimpleStringProperty(estudiante);
    }
    public String getUsuario() {
        return usuario.get();
    }
    public void setUsuario(String usuario) {
        this.usuario = new SimpleStringProperty(usuario);
    }
    public String getAsesor() {
        return asesor.get();
    }
    public void setAsesor(String asesor) {
        this.asesor = new SimpleStringProperty(asesor);
    }
    public String getMotivo() {
        return motivo.get();
    }
    public void setMotivo(String motivo) {
        this.motivo = new SimpleStringProperty(motivo);
    }
    public String getFecha() {
        return fecha.get();
    }
    public void setFecha(String fecha) {
        this.fecha = new SimpleStringProperty(fecha);
    }
    public String getHora() {
        return hora.get();
    }
    public void setHora(String hora) {
        this.hora = new SimpleStringProperty(hora);
    }

    public SimpleStringProperty estudianteProperty() {
        return estudiante;
    }
    public SimpleStringProperty usuarioProperty() {
        return usuario;
    }
    public SimpleStringProperty asesorProperty() {
        return asesor;
    }
    public SimpleStringProperty motivoProperty() {
        return motivo;
    }
    public SimpleStringProperty fechaProperty() {
        return fecha;
    }
    public SimpleStringProperty horaProperty() {
        return hora;
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
