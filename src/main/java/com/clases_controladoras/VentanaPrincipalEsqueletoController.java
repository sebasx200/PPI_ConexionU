package com.clases_controladoras;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;

public class VentanaPrincipalEsqueletoController {

    @FXML
    private Label botonX, botonMinimizar;
    @FXML
    private AnchorPane content;
    @FXML
    private BorderPane header;
    private double x =0;
    private double y = 0;

    public void initialize() throws IOException {

        mostrarPanel();
    }

    @FXML
    protected void onBotonXMouseEntered(){
        botonX.setStyle("-fx-background-color: red;");
        botonX.setTextFill(Color.WHITE);
        Tooltip tooltip = new Tooltip("Cerrar");
        botonX.setTooltip(tooltip);
    }
    @FXML
    protected void onBotonXMouseExited(){
        botonX.setStyle("-fx-background-color: white;");
        botonX.setTextFill(Color.BLACK);
    }
    @FXML
    protected void onBotonMinimizarMouseEntered(){
        botonMinimizar.setStyle("-fx-background-color: #9e9e9e;");
        botonMinimizar.setTextFill(Color.WHITE);
        Tooltip tooltip = new Tooltip("Minimizar");
        botonMinimizar.setTooltip(tooltip);
    }
    @FXML
    protected void onBotonMinimizarMouseExited(){
        botonMinimizar.setStyle("-fx-background-color: white;");
        botonMinimizar.setTextFill(Color.BLACK);
    }
    @FXML
    protected void onBotonXClick(){
        Platform.exit();
    }
    @FXML
    protected void onBotonMinimizarClick(){
        Stage stage = (Stage) botonMinimizar.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void mostrarPanel() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-principal.fxml"));
        content.getChildren().setAll(pane);
    }
    @FXML
    protected void onHeaderMouseDragged(MouseEvent event){
        Stage stage = (Stage) header.getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }
    @FXML
    protected void onHeaderMousePressed(MouseEvent event){
        x = event.getSceneX();
        y = event.getSceneY();
    }
}
