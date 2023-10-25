package com.clases_controladoras;

import com.clases.DataSingleton;
import com.clases.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MenuEstudianteController extends VentanaMenuController{

    @FXML
    private Label botonMenu, paginaInicio, botonLogOut, agendarAsesoria, misAsesorias, notificaciones, verLista, configuraciones, acercaDe, opcionSeleccionada;
    @FXML
    private VBox menu;
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane stackPane, content;
    @FXML
    private ImageView fondo;
    @FXML
    private Label nombreUser;
    private Usuario usuario;
    DataSingleton data = DataSingleton.getInstance();

    public void initialize(){

        cambiarColorEtiqueta(paginaInicio);
        cambiarColorEtiqueta(agendarAsesoria);
        cambiarColorEtiqueta(misAsesorias);
        cambiarColorEtiqueta(notificaciones);
        cambiarColorEtiqueta(verLista);
        cambiarColorEtiqueta(configuraciones);
        cambiarColorEtiqueta(acercaDe);

    }
    @Override
    protected void onMouseExitedLogOut() {
        botonLogOut.setStyle("-fx-background-color:  #4CAF50;");
        botonLogOut.setTextFill(Color.BLACK);
    }
    public void cambiarColorEtiqueta(Label label) {
        setMouseOverEffect(label, "#4CAF50", "#2e7d32");
    }
}
