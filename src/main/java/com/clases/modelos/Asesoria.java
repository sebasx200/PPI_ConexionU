package com.clases.modelos;

public class Asesoria {

    private String estudiante;
    private String usuario;
    private String usuarioAsesor;
    private String asesor;
    private String motivo;
    private String fecha;
    private String hora;
    private String estado;

    public Asesoria(String estudiante, String usuario, String usuarioAsesor, String asesor, String motivo, String fecha,
                    String hora, String estado) {
        this.estudiante = estudiante;
        this.usuario = usuario;
        this.usuarioAsesor = usuarioAsesor;
        this.asesor = asesor;
        this.motivo = motivo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
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

    public String getUsuarioAsesor() {
        return usuarioAsesor;
    }

    public void setUsuarioAsesor(String usuarioAsesor) {
        this.usuarioAsesor = usuarioAsesor;
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
                ", usuario='" + usuario + '\'' +
                ", usuarioAsesor='" + usuarioAsesor + '\'' +
                ", asesor='" + asesor + '\'' +
                ", motivo='" + motivo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}