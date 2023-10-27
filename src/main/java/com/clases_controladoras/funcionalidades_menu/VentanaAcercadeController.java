package com.clases_controladoras.funcionalidades_menu;

import com.clases.Mensajes;
import com.clases_controladoras.VentanaMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class VentanaAcercadeController {

    @FXML
    private Label texto;

    public void initialize(){

        texto.setText("""
                Somos un equipo de trabajo conformado por 3 estudiantes de la Técnica Profesional de programación de 
                sistemas de información; nos encontramos realizando nuestro segundo Proyecto Pedagógico Integrador de 
                este año el cual busca brindarte asesorías personalizadas para aumentar tu rendimiento académico de 
                manera notable.
                """);
    }

}
