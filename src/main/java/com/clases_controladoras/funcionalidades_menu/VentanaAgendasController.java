package com.clases_controladoras.funcionalidades_menu;

import com.clases.Mensajes;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class VentanaAgendasController {
    @FXML
    private HBox hBox;
    @FXML
    private CheckBox lunes, martes, miercoles, jueves, viernes, sabado, domingo;

    @FXML
    private void onBotonConfirmarAction(){
        obtenerDiasSeleccionados();
    }
    private void obtenerDiasSeleccionados(){
        boolean seSelecciona = false;
        String[] dias = new String[7];
        int pos = 0;
        for(Node nodo:hBox.getChildren()){
            if(nodo instanceof CheckBox){
                CheckBox checkBox = (CheckBox) nodo;
                if(checkBox.isSelected()){
                    seSelecciona = true;
                    dias[pos] = checkBox.getText();
                    checkBox.setSelected(false);
                }
                pos++;
            }
        }
        if(!seSelecciona){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado ningún día se la semana");
        }
    }
}
