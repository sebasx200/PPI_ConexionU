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

/** Se crea una clase controladora para que en caso de que se inicie sesión como estudiante o mentor, se llame a una
 * vista diferente y se hace esta clase con herencia del menú principal porque la mayoría de las configuraciones serán
 * las mismas*/

public class MenuEstudianteController extends VentanaMenuController{

    @FXML
    private Label botonMenu, paginaInicio, botonLogOut, agendarAsesoria, misAsesorias, notificaciones, verLista, configuraciones, acercaDe, opcionSeleccionada;
    @FXML
    private StackPane stackPane, content;
    @FXML
    private ImageView fondo;
    @FXML
    private Label nombreUser;
    private Usuario usuario;
    DataSingleton data = DataSingleton.getInstance();

    /** Este es el método que se inicia por defecto cuando se inicia la ventana, se llama a los métodos que capturan
     *  las opciones del menú*/
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

    /** Se configura un listener en el cual se configura el evento del mouse cuando se pone el cursor en el menú
     * y cuando sale del mismo para cambiar los colores*/
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

    /** Método central del menú, en el cual se llaman a las ventanas de las funcionalidades dependiendo de la opción
     * que le usuario haya elegido*/
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
                ruta = "/com/ppi_conexionu/funcionalidades_menu/ventana-asesorias.fxml";
                rutaImagen = "/imagenes/background/background_18.png";
                image = new Image(getClass().getResource(rutaImagen).toExternalForm());
                fondo.setImage(image);
                pane = FXMLLoader.load(getClass().getResource(ruta));
                content.getChildren().setAll(fondo, pane);
                break;
            case "Mis asesorias":
                opcionSeleccionada.setText(opcion);
                ruta = "/com/ppi_conexionu/funcionalidades_menu/ventana-mis-asesorias.fxml";
                rutaImagen = "/imagenes/background/background_ 11.png";
                image = new Image(getClass().getResource(rutaImagen).toExternalForm());
                fondo.setImage(image);
                pane = FXMLLoader.load(getClass().getResource(ruta));
                content.getChildren().setAll(fondo, pane);
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
                content.getChildren().setAll(fondo, pane);
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
                content.getChildren().setAll(fondo, pane);
                break;
        }
    }

    /** Se le establece al botón del menú un color cuando el usuario pasa el cursor sobre él*/
    @Override
    protected void onMouseEnteredMenu() {
        botonMenu.setStyle("-fx-background-color: #2e7d32;");
    }

    /** Se restablece el color por defecto de la opción menú cuando el usuario saca el cursor de él*/
    @Override
    protected void onMouseExited() {
        botonMenu.setStyle("-fx-background-color: #4CAF50;");
    }

    /** Se restablece el color por defecto de cerrar sesión cuando el usuario saca el cursor de él*/
    @Override
    protected void onMouseExitedLogOut() {
        botonLogOut.setStyle("-fx-background-color: #4CAF50;");
        botonLogOut.setTextFill(Color.BLACK);
    }
    public Usuario getUsuario(){
        return usuario;
    }
}