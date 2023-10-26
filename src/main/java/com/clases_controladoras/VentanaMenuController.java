package com.clases_controladoras;

import com.clases.DataSingleton;
import com.clases.Mensajes;
import com.clases.Usuario;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;

public class VentanaMenuController {

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
    public void initialize() {
        // este es el método que se inicia por defecto cuando se inicia la ventana, se llama a los métodos que capturan las opciones del menú
        usuario = data.getUsuario();
        nombreUser.setText(usuario.getNombre());
        setMouseOverEffect(paginaInicio,  "#90caf9","#2196f3");
        setMouseOverEffect(agendarAsesoria, "#90caf9","#2196f3");
        setMouseOverEffect(misAsesorias, "#90caf9","#2196f3");
        setMouseOverEffect(notificaciones, "#90caf9","#2196f3");
        setMouseOverEffect(verLista, "#90caf9","#2196f3");
        setMouseOverEffect(configuraciones, "#90caf9","#2196f3");
        setMouseOverEffect(acercaDe, "#90caf9","#2196f3");
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


    protected void setOpcionMouseClick(Label label) {
        // con este método simplemente se busca capturar cuál fue la opción que el usuario eligió
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    opcionElegida(label.getText()); // con la opcion capturada se llama al switch pasándole la opción seleccionada por el usuario
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @FXML
    protected void onbotonMenuClick() {
        // este método busca que cuando el usuario le da clic al botón menú, se cambia de tamaño según la ocasión cuando el usuario le dé clic
        if (menu.getPrefWidth() == 250) {
            fondo.setFitWidth(915);
            menu.setPrefWidth(85);
            paginaInicio.setEllipsisString(null);
            agendarAsesoria.setEllipsisString(null);
            misAsesorias.setEllipsisString(null);
            notificaciones.setEllipsisString(null); // estas líneas hacen que cuando el menú esté recogido no aparezcan puntos suspensivos en los íconos
            verLista.setEllipsisString(null);
            configuraciones.setEllipsisString(null);
            acercaDe.setEllipsisString(null);
        } else {
            fondo.setFitWidth(750);
            menu.setPrefWidth(250);
        }
    }
    protected void opcionElegida(String opcion) throws IOException {
        // se le especifica al switch que ejecute el código del caso según la opción que seleccionó el usuario del menú

        String ruta;
        String rutaImagen;
        Image image;
        StackPane pane;

        switch (opcion) {
            case "Página inicio":
                opcionSeleccionada.setText(opcion);
                rutaImagen = "/imagenes/background/background_8.png";
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
            case "Ver lista estudiantes":
                opcionSeleccionada.setText(opcion);
                ruta = "/com/ppi_conexionu/funcionalidades_menu/ventana-tableview.fxml";
                rutaImagen = "/imagenes/background/background_5.png";
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
                rutaImagen = "/imagenes/background/background_3.png";
                image = new Image(getClass().getResource((rutaImagen)).toExternalForm());
                fondo.setImage(image);
                pane = FXMLLoader.load(getClass().getResource(ruta));
                content.getChildren().setAll(fondo, pane);
                break;
        }
    }
    @FXML
    // evento del botón cerrar sesión para que si el usuario confirma, se cambie la pantalla a ventana de login
    protected void onBotonLogoutClick() throws IOException {
        boolean mensaje = Mensajes.mensajeConfirmacion("Cerrar Sesión", null, "¿Está seguro que desea cerrar sesión?");
        if (mensaje) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-login.fxml"));
            rootPane.getChildren().setAll(pane);
        }
    }
    @FXML
    protected void onMouseEnteredMenu() {
        botonMenu.setStyle("-fx-background-color: #2196f3;");
    } // se le establece al botón del menú un color cuando el usuario pasa el cursor sobre él

    @FXML
    protected void onMouseExited() {
        botonMenu.setStyle("-fx-background-color: #90caf9;");
    } // se restablece el color por defecto de la opción menú cuando el usuario saca el cursor de él

    @FXML
    protected void onMouseEnteredLogOut() {
        botonLogOut.setStyle("-fx-background-color: red;");
        botonLogOut.setTextFill(Color.WHITE);
    } // se le establece al botón de cerrar sesión un color cuando el usuario pasa el cursor sobre él

    @FXML
    protected void onMouseExitedLogOut() {
        botonLogOut.setStyle("-fx-background-color: #90caf9;");
        botonLogOut.setTextFill(Color.BLACK);
    } // se restablece el color por defecto de cerrar sesión cuando el usuario saca el cursor de él
}
