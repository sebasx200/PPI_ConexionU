package com.clases_controladoras;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipalController {
    @FXML
    private Button botonLogin;
    @FXML
    private Button botonRegistrarse;
    @FXML
    private AnchorPane rootPane;
    private double x =0;
    private double y = 0;

    @FXML
    private void onBotonLoginAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-login.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
