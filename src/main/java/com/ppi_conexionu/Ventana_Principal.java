package com.ppi_conexionu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Ventana_Principal extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ventana_Principal.class.getResource("/com/ppi_conexionu/ventana-principal(esqueleto).fxml"));
        stage.getIcons().add(new javafx.scene.image.Image(getClass().getResource("/imagenes/logos/logo_negro_16x16.png").toExternalForm()));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 630);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}
