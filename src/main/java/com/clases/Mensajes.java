package com.clases;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Mensajes {
    public static void messageDialog(String mensaje){
        Stage dialogo = new Stage();
        dialogo.initModality(Modality.APPLICATION_MODAL);
        dialogo.setTitle("Mensaje");
        dialogo.setMinWidth(250);
        Label etiquetaMensaje = new Label(mensaje);
        Button botonCerrar = new Button("Cerrar");
        botonCerrar.setOnAction(e -> dialogo.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(etiquetaMensaje, botonCerrar);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 250, 150);
        dialogo.setScene(scene);
        dialogo.showAndWait();
    }

}
