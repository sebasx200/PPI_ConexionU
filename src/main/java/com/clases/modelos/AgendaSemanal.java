package com.clases.modelos;

public class AgendaSemanal {
    private String userAsesor;
    private String horaInicial;
    private String horaFinal;

    public AgendaSemanal(String userAsesor, String horaInicial, String horaFinal) {
        this.userAsesor = userAsesor;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }

    public String getUserAsesor() {
        return userAsesor;
    }

    public void setUserAsesor(String userAsesor) {
        this.userAsesor = userAsesor;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Override
    public String toString() {
        return "AgendaSemanal{" +
                "userAsesor='" + userAsesor + '\'' +
                ", horaInicial='" + horaInicial + '\'' +
                ", horaFinal='" + horaFinal + '\'' +
                '}';
    }
}
