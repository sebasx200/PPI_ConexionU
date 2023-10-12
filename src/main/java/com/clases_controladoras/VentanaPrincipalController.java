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
import javafx.scene.layout.*;
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

    @FXML
    protected void onBotonLoginMouseEntered(){
        botonLogin.setStyle("-fx-background-color: #2265E8;" +
                " -fx-text-fill: white; -fx-background-radius: 40;" +
                " -fx-border-color: white; -fx-border-radius: 40;");
    }
    @FXML
    protected void onBotonLoginMouseExited(){
        botonLogin.setStyle("-fx-background-color: #2265E8;" +
                " -fx-text-fill: black; -fx-background-radius: 40;" +
                " -fx-border-color: black; -fx-border-radius: 40;");
    }
    @FXML
    protected void onBotonRegistrarMouseEntered(){
        botonRegistrarse.setStyle("-fx-background-color: #2265E8;" +
                " -fx-text-fill: white; -fx-background-radius: 40;" +
                " -fx-border-color: white; -fx-border-radius: 40;");
    }
    @FXML
    protected void onBotonRegistrarMouseExited(){
        botonRegistrarse.setStyle("-fx-background-color: #2265E8;" +
                " -fx-text-fill: black; -fx-background-radius: 40;" +
                " -fx-border-color: black; -fx-border-radius: 40;");
    }
    @FXML
    private void onBotonLoginAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-login.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    protected void onBotonRegistrarseAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-registro.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}