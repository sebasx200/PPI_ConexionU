package com.clases_controladoras;

import com.clases.DataSingleton;
import com.clases.Mensajes;
import com.clases.Usuario;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        perfilLogueado(usuario.getPerfil());
        if(usuario.getPerfil().equals("Estudiante")){
            URL resourceURL = getClass().getResource("/imagenes/background/background_ 13.png");
            Image image = new Image(resourceURL.toExternalForm());
            fondo.setImage(image);
            cambiarColores();
        }
        setMouseOverEffect(paginaInicio);
        setMouseOverEffect(agendarAsesoria);
        setMouseOverEffect(misAsesorias);
        setMouseOverEffect(notificaciones);
        setMouseOverEffect(verLista);
        setMouseOverEffect(configuraciones);
        setMouseOverEffect(acercaDe);
        setOpcionMouseClick(paginaInicio);
        setOpcionMouseClick(agendarAsesoria);
        setOpcionMouseClick(misAsesorias);
        setOpcionMouseClick(notificaciones);
        setOpcionMouseClick(verLista);
        setOpcionMouseClick(configuraciones);
        setOpcionMouseClick(acercaDe);
    }

    private void setMouseOverEffect(Label label) {
        // este método hace que cuando el usuario pone el cursor dentro o fuera de las opciones del menú, estas opciones cambian de color
        label.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // se establece el fondo de color que se quiere cuando se pasa el mouse encima del label
                label.setStyle("-fx-background-color: #2196f3;");
                Tooltip tooltip = new Tooltip(label.getText());
                Tooltip.install(label, tooltip);
            }
        });
        label.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Restaura el fondo original cuando se sale el mouse encima del label
                label.setStyle("-fx-background-color: #90caf9;" +
                        "-fx-border-color: black");
            }
        });
    }

    private void setOpcionMouseClick(Label label) {
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
    private void onMouseEnteredMenu() {
        botonMenu.setStyle("-fx-background-color: #2196f3;");
    } // se le establece al botón del menú un color cuando el usuario pasa el cursor sobre él

    @FXML
    private void onMouseExited() {
        botonMenu.setStyle("-fx-background-color: #90caf9;");
    } // se restablece el color por defecto de la opción menú cuando el usuario saca el cursor de él

    @FXML
    private void onMouseEnteredLogOut() {
        botonLogOut.setStyle("-fx-background-color: red;");
        botonLogOut.setTextFill(Color.WHITE);
    } // se le establece al botón de cerrar sesión un color cuando el usuario pasa el cursor sobre él

    @FXML
    private void onMouseExitedLogOut() {
        botonLogOut.setStyle("-fx-background-color: #90caf9;");
        botonLogOut.setTextFill(Color.BLACK);
    } // se restablece el color por defecto de cerrar sesión cuando el usuario saca el cursor de él

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

    @FXML
    // evento del botón cerrar sesión para que si el usuario confirma, se cambie la pantalla a ventana de login
    protected void onBotonLogoutClick() throws IOException {
        boolean mensaje = Mensajes.mensajeConfirmacion("Cerrar Sesión", null, "¿Está seguro que desea cerrar sesión?");
        if (mensaje) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-login.fxml"));
            rootPane.getChildren().setAll(pane);
        }
    }

    private void opcionElegida(String opcion) throws IOException {
        // se le especifica al switch que ejecute el código del caso según la opción que seleccionó el usuario del menú

        String ruta;
        String rutaImagen;

        switch (opcion) {
            case "Página inicio":
                opcionSeleccionada.setText(opcion);
                rutaImagen = "/imagenes/background/background_8.png";
                cambiarImagen(rutaImagen);
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
            case "Ver lista Docentes":
                opcionSeleccionada.setText(opcion);
                ruta = "/com/ppi_conexionu/funcionalidades_menu/ventana-tableview.fxml";
                rutaImagen = "/imagenes/background/background_5.png";
                cargarFXML(ruta, fondo);
                cambiarImagen(rutaImagen);
                break;
            case "Configuraciones":
                opcionSeleccionada.setText(opcion);
                break;
            case "Acerca de":
                opcionSeleccionada.setText(opcion);
                ruta = "/com/ppi_conexionu/funcionalidades_menu/ventana-acercade.fxml";
                rutaImagen = "/imagenes/background/background_3.png";
                cambiarImagen(rutaImagen);
                cargarFXML(ruta, fondo);
                break;
        }
    }

    // se encarga de cambiar el archivo FXML que está cargado en content según se seleccione una opción en el menú
    @FXML
    private void cargarFXML(String ruta, ImageView fondo) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource(ruta));
        content.getChildren().setAll(fondo, pane);
    }

    // Este método es útil pora que se cambie el fondo de pantalla según se cambie la opción del menú
    @FXML
    public void cambiarImagen(String rutaImagen){
        Image image = new Image(getClass().getResource(rutaImagen).toExternalForm());
        fondo.setImage(image);
    }

    public void perfilLogueado(String perfil){
        switch (perfil){
            case "Docente":
                verLista.setText("Ver lista Estudiantes");
                break;
            case "Mentor":
                verLista.setText("Ver lista Estudiantes");
                break;
            case "Estudiante":
                verLista.setText("Ver lista Docentes");
                break;
        }
    }
    public void cambiarColores(){
            borderPane.setStyle("-fx-background-color: #4CAF50;");
            menu.setStyle("-fx-background-color: #4CAF50;");
    }
}
