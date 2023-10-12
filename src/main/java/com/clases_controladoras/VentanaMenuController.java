package com.clases_controladoras;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VentanaMenuController {

    @FXML
    private Label botonMenu, paginaInicio, agendarAsesoria, misAsesorias, notificaciones, verListaEstudiantes, configuraciones, acercaDe;
    @FXML
    private HBox hBox;
    @FXML
    private VBox menu;
    @FXML
    private AnchorPane content;

    public void initialize(){

        hBox.setSpacing(10);
        setMouseOverEffect(paginaInicio);
        setMouseOverEffect(agendarAsesoria);
        setMouseOverEffect(misAsesorias);
        setMouseOverEffect(notificaciones);
        setMouseOverEffect(verListaEstudiantes);
        setMouseOverEffect(configuraciones);
        setMouseOverEffect(acercaDe);

    }
    private void setMouseOverEffect(Label label) {
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

    @FXML
    private void onMouseEnteredMenu(){
        botonMenu.setStyle("-fx-background-color: #2196f3;");
    }
    @FXML
    private void onMouseExited(){
        botonMenu.setStyle("-fx-background-color: #90caf9;");
    }
    @FXML
    protected void onbotonMenuClick(){

        if(menu.getPrefWidth() == 250) {
            menu.setPrefWidth(85);
            paginaInicio.setEllipsisString(null);
            agendarAsesoria.setEllipsisString(null);
            misAsesorias.setEllipsisString(null);
            notificaciones.setEllipsisString(null);
            verListaEstudiantes.setEllipsisString(null);
            configuraciones.setEllipsisString(null);
            acercaDe.setEllipsisString(null);
            content.setPrefWidth(1000);
        }
        else{
            menu.setPrefWidth(250);
        }
    }

}
