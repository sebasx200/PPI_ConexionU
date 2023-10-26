package com.clases_controladoras;

import com.clases.DataSingleton;
import com.clases.Usuario;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

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
        usuario = data.getUsuario();
        nombreUser.setText(usuario.getNombre());
        setMouseOverEffect(paginaInicio,  " #4CAF50","#2e7d32");
        setMouseOverEffect(agendarAsesoria, " #4CAF50","#2e7d32");
        setMouseOverEffect(misAsesorias, " #4CAF50","#2e7d32");
        setMouseOverEffect(notificaciones, " #4CAF50","#2e7d32");
        setMouseOverEffect(verLista, " #4CAF50","#2e7d32");
        setMouseOverEffect(configuraciones, " #4CAF50","#2e7d32");
        setMouseOverEffect(acercaDe, " #4CAF50","#2e7d32");
        setOpcionMouseClick(paginaInicio);
        setOpcionMouseClick(agendarAsesoria);
        setOpcionMouseClick(misAsesorias);
        setOpcionMouseClick(notificaciones);
        setOpcionMouseClick(verLista);
        setOpcionMouseClick(configuraciones);
        setOpcionMouseClick(acercaDe);
    }

    protected void setMouseOverEffect(Label label, String backgroundColor, String hoverBackgroundColor) {
        label.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                label.setStyle("-fx-background-color: " + hoverBackgroundColor + ";");
                Tooltip tooltip = new Tooltip(label.getText());
                Tooltip.install(label, tooltip);
            }
        });
        label.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                label.setStyle("-fx-background-color: " + backgroundColor + ";" +
                        "-fx-border-color: black");
            }
        });
    }
    @Override
    protected void opcionElegida(String opcion) throws IOException {
        String ruta;
        String rutaImagen;
        StackPane pane;
        Image image;

        switch (opcion) {
            case "Página inicio":
                opcionSeleccionada.setText(opcion);
                rutaImagen = "/imagenes/background/background_ 13.png";
                image = new Image(getClass().getResource(rutaImagen).toExternalForm());
                fondo.setImage(image);
                content.getChildren().setAll(fondo, stackPane);
                break;
            case "Agendar asesoria":
                opcionSeleccionada.setText(opcion);
                break;
            case "Mis asesorias":
                opcionSeleccionada.setText(opcion);
                break;
            case "Notificaciones":
                opcionSeleccionada.setText(opcion);
                break;
            case "Ver lista docentes":
                opcionSeleccionada.setText(opcion);
                ruta = "/com/ppi_conexionu/funcionalidades_menu/ventana-tableview.fxml";
                rutaImagen = "/imagenes/background/background_ 12.png";
                image = new Image(getClass().getResource((rutaImagen)).toExternalForm());
                fondo.setImage(image);
                pane = FXMLLoader.load(getClass().getResource(ruta));
                stackPane.getChildren().setAll(fondo, pane);
                break;
            case "Configuraciones":
                opcionSeleccionada.setText(opcion);
                break;
            case "Acerca de":
                opcionSeleccionada.setText(opcion);
                ruta = "/com/ppi_conexionu/funcionalidades_menu/ventana-acercade.fxml";
                rutaImagen = "/imagenes/background/background_ 12.png";
                image = new Image(getClass().getResource((rutaImagen)).toExternalForm());
                fondo.setImage(image);
                pane = FXMLLoader.load(getClass().getResource(ruta));
                stackPane.getChildren().setAll(fondo, pane);
                break;
        }
    }

    @Override
    protected void onMouseEnteredMenu() {
        botonMenu.setStyle("-fx-background-color: #2e7d32;");
    }
    @Override
    protected void onMouseExited() {
        botonMenu.setStyle("-fx-background-color: #4CAF50;");
    }
    @Override
    protected void onMouseExitedLogOut() {
        botonLogOut.setStyle("-fx-background-color: #4CAF50;");
        botonLogOut.setTextFill(Color.BLACK);
    }
}