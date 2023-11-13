package com.clases_controladoras.funcionalidades_menu;

import com.clases.Mensajes;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class VentanaAgendasController {
    @FXML
    private ComboBox<String> horaInicial, horaFinal, comboDias;
    public void initialize(){
        llenarHoras();
        diaSemana();
    }
    @FXML
    private void onBotonConfirmarAction(){
        String dia = comboDias.getValue();
        String horaInicio = horaInicial.getValue();
        String horaFin = horaFinal.getValue();
        Mensajes.mensajeConfirmacion("Día y horas seleccionadas",
                "El día " + dia + " dará asesorías entre las horas " + horaInicio + "-" + horaFin,
                "¿Está seguro/a de la información proporcianda?");
    }
    private void llenarHoras(){
        for (int hora = 6; hora < 22; hora++) {
            for (int minuto = 0; minuto < 60; minuto += 60) {
                int horaFin = hora;
                int minutoFin = minuto + 60;
                if (minutoFin >= 60) {
                    horaFin++;
                    minutoFin = 0;
                }
                String horaInicio = String.format("%02d:%02d", hora, minuto);
                String horaFinStr = String.format("%02d:%02d", horaFin, minutoFin);
                String rangoHoras = horaInicio + " - " + horaFinStr;
                horaInicial.getItems().add(horaInicio);
                horaFinal.getItems().add(horaFinStr);
            }
        }
    }
    @FXML
    private void onComboDiasClick(){
        String[] dias = new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        comboDias.getItems().clear();
        comboDias.getItems().setAll(dias);
    }
    private void diaSemana(){
        LocalDate fechaActual = LocalDate.now();

        // Obtén la fecha del próximo lunes
        LocalDate proximoLunes = fechaActual.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        // Formatea la fecha en el formato "dd-MM-yyyy"
        String formatoDeseado = proximoLunes.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Imprime el resultado
        System.out.println("Próximo lunes: " + formatoDeseado);
    }
}
