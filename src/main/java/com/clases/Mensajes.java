package com.clases;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Optional;

public class Mensajes {

    // método para mostrarle al usuario un mensaje informativo
    public static void mensajeInformativo(String header, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        establecerIcono(alerta);
        alerta.setTitle("Información");
        alerta.setContentText(mensaje);
        alerta.setHeaderText(header);
        alerta.showAndWait();
    }

    public static void mensajeAdvertencia(String header, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        establecerIcono(alerta);
        alerta.setTitle("Advertencia");
        alerta.setContentText(mensaje);
        alerta.setHeaderText(header);
        alerta.showAndWait();
    }

    public static void mensajeError(String header, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        establecerIcono(alerta);
        alerta.setTitle("Error");
        alerta.setContentText(mensaje);
        alerta.setHeaderText(header);
        alerta.showAndWait();
    }
    public static boolean mensajeConfirmacion(String titulo, String header, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        establecerIcono(alerta);
        alerta.setGraphic(null);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(header);

        ButtonType botonConfirmar = new ButtonType("Confirmar");
        ButtonType botonCancelar = new ButtonType("Cancelar");
        alerta.getButtonTypes().setAll(botonConfirmar, botonCancelar);
        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.filter(response -> response == botonConfirmar).isPresent();
    }

    public static void establecerIcono(Alert alert){
        // este método se usa para esblacerle a todas las alertas el ícono de la aplicación
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        String rutaRecurso = "/imagenes/logos/logo_negro_16x16.png";
        URL urlRecurso = Mensajes.class.getResource(rutaRecurso);
        stage.getIcons().add(new Image(urlRecurso.toExternalForm()));
    }
}
