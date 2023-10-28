package com.clases_controladoras.funcionalidades_menu;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class VentanaAsesoriasController {

    @FXML
    private ComboBox<String> comboAsesor, comboMotivo, comboHora;
    @FXML
    private DatePicker elegirFecha;
    @FXML
    private CheckBox checkDocente, checkMentor;

    public void initialize(){
        llenarHoras();
    }

    private void llenarHoras(){
        for (int hora = 0; hora < 24; hora++) {
            for (int minuto = 0; minuto < 60; minuto += 20) {
                int horaFin = hora;
                int minutoFin = minuto + 20;
                if (minutoFin >= 60) {
                    horaFin++;
                    minutoFin = 0;
                }
                String horaInicio = String.format("%02d:%02d", hora, minuto);
                String horaFinStr = String.format("%02d:%02d", horaFin, minutoFin);
                String rangoHoras = horaInicio + " - " + horaFinStr;
                comboHora.getItems().add(rangoHoras);
            }
        }
    }

    @FXML
    private void onCheckDocenteAction(){
        String[] docentes = new String[]{"Maryem Ruíz", "Juan Carlos Gil", "Carlos Builes"};
        if(checkMentor.isSelected()){
            checkDocente.setSelected(false);
        }
        comboAsesor.getItems().clear();
        comboAsesor.getItems().addAll(docentes);
    }
    @FXML
    private void onCheckMentorAction(){
        String[] mentores = new String[]{"Sebas Palacio", "Juan Pablo Ángel", "Daniel Henao"};
        if(checkDocente.isSelected()){
            checkMentor.setSelected(false);
        }
        comboAsesor.getItems().clear();
        comboAsesor.getItems().addAll(mentores);
    }
}
