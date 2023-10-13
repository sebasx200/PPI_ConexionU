package com.clases_controladoras;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VentanaMenuController {

    @FXML
    private Label botonMenu, paginaInicio, agendarAsesoria, misAsesorias, notificaciones, verListaEstudiantes, configuraciones, acercaDe, opcionSeleccionada;
    @FXML
    private HBox hBox;
    @FXML
    private VBox menu;
    @FXML
    private AnchorPane content;
    @FXML
    private VBox vBox;
    @FXML
    private ImageView fondo;

    public void initialize(){
    // este es el método que se inicia por defecto cuando se inicia la ventana, se llama a los métodos que capturan las opciones del menú
        vBox.setSpacing(10);
        setMouseOverEffect(paginaInicio);
        setMouseOverEffect(agendarAsesoria);
        setMouseOverEffect(misAsesorias);
        setMouseOverEffect(notificaciones);
        setMouseOverEffect(verListaEstudiantes);
        setMouseOverEffect(configuraciones);
        setMouseOverEffect(acercaDe);
        setOpcionMouseClick(paginaInicio);
        setOpcionMouseClick(agendarAsesoria);
        setOpcionMouseClick(misAsesorias);
        setOpcionMouseClick(notificaciones);
        setOpcionMouseClick(verListaEstudiantes);
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

    private void setOpcionMouseClick(Label label){
        // con este método simplemente se busca capturar cuál fue la opción que el usuario eligió
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                opcionElegida(label.getText()); // con la opcion capturada se llama al switch pasándole la opción seleccionada por el usuario
            }
        });
    }

    @FXML
    private void onMouseEnteredMenu(){
        botonMenu.setStyle("-fx-background-color: #2196f3;");
    } // se le establece al botón del menú un color cuando el usuario pasa el cursor sobre él

    @FXML
    private void onMouseExited(){
        botonMenu.setStyle("-fx-background-color: #90caf9;");
    } // se restablece el color por defecto de la opción menú cuando el usuario saca el cursor de él

    @FXML
    protected void onbotonMenuClick(){
    // este método busca que cuando el usuario le da clic al botón menú, se cambia de tamaño según la ocasión cuando el usuario le dé clic

        if(menu.getPrefWidth() == 250) {
            fondo.setFitWidth(915);
            menu.setPrefWidth(85);
            paginaInicio.setEllipsisString(null);
            agendarAsesoria.setEllipsisString(null);
            misAsesorias.setEllipsisString(null);
            notificaciones.setEllipsisString(null); // estas líneas hacen que cuando el menú esté recogido no aparezcan puntos suspensivos en los íconos
            verListaEstudiantes.setEllipsisString(null);
            configuraciones.setEllipsisString(null);
            acercaDe.setEllipsisString(null);
        }
        else{
            fondo.setFitWidth(750);
            menu.setPrefWidth(250);
        }
    }

    private void opcionElegida(String opcion){
    // se le especifica al switch que ejecute el código del caso según la opción que seleccionó el usuario del menú
        switch (opcion){

            case "Página inicio":
                opcionSeleccionada.setText(opcion);
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
                break;
            case "Configuraciones":
                opcionSeleccionada.setText(opcion);
                break;
            case "Acerca de":
                opcionSeleccionada.setText(opcion);
                break;
        }
    }
}
