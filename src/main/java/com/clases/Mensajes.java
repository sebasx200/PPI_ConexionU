package com.clases;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Mensajes {

    // método para mostrarle al usuario un mensaje informativo
    public static void mensajeInformativo(String header, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setContentText(mensaje);
        alerta.setHeaderText(header);
        alerta.showAndWait();
    }

    public static void mensajeAdvertencia(String header, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setContentText(mensaje);
        alerta.setHeaderText(header);
        alerta.showAndWait();
    }

    public static void mensajeError(String header, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setContentText(mensaje);
        alerta.setHeaderText(header);
        alerta.showAndWait();
    }

}
