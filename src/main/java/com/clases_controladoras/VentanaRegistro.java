package com.clases_controladoras;

import com.clases.Mensajes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaRegistro {

    @FXML
    private AnchorPane panelRegistro;

    @FXML
    private Button botonRegistrar;

    @FXML
    private Button botonVolver;

    @FXML
    private Hyperlink hyperInicio;

    @FXML
    private ComboBox departamentos;

    @FXML
    private ComboBox municipios;

    @FXML
    private ComboBox universidades;

    @FXML
    private ComboBox perfiles;

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputApellido;

    @FXML
    private TextField inputDocumento;

    @FXML
    private TextField inputUsuario;

    @FXML
    private TextField inputCorreo;

    @FXML
    private TextField inputTelefono;

    @FXML
    private TextField inputContraseña;

    @FXML
    private TextField inputConfirmarContraseña;

    // ## Estos son los metodos de validaciones

    // Se van avalidar los datos que ingrese el usuario
    public void validarDatos() {
        boolean datosValidos = true;

        // Se valida que todos los campos esten llenos
        if (inputNombre.getText().isEmpty() || inputApellido.getText().isEmpty()
                || inputDocumento.getText().isEmpty() || inputUsuario.getText().isEmpty()
                || inputCorreo.getText().isEmpty() || inputTelefono.getText().isEmpty()
                || inputContraseña.getText().length() == 0 || inputConfirmarContraseña.getText().length() == 0) {
            datosValidos = false;
            Mensajes.mensajeError(null, "Llenar todos los campos");
        }

        // Se valida que ingrese un nombre con mas de 3 letras
        if (inputNombre.getText().length() < 3 && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"El nombre debe ser mayor o igual a 3 letras");
        }

        // Se valida que ingrese un apellido con mas de 3 letras
        if (inputApellido.getText().length() < 3 && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"El apellido debe ser mayor o igual a 3 letras");
        }

        // Validamos el usuario que tenga letras y números
        if (!validarUsuario(inputUsuario.getText()) && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"Usuario no valido, debe contener letras y números,");
        }

        // Validamos el correo
        if (!validarCorreo(inputCorreo.getText()) && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"Correo no valido");
        }

        // Se valida que la contraseña tenga mas de 4 digitos
        if (inputContraseña.getText().length() < 4 && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null, "Las contraseñas deben tener mas de 4 digitos");
        }

        // Se validan las contraseñas que sean iguales
        if (!inputContraseña.getText().equals(inputConfirmarContraseña.getText()) && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"Las contraseñas no son iguales");
        }



    }

    public static boolean validarUsuario(String usuario) {
        // Definir una expresión regular para verificar si el usuario contiene solo letras y números
        String patron = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(usuario);
        return matcher.matches();
    }

    public static boolean validarCorreo(String correo) {
        // Define una expresión regular para validar correos electrónicos
        String patron = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }


    // ## Estos son los metodos para comboBox

    // Se inicializa los comboBox con lo que va iniciar cada comboBox
    public void initialize() {
        perfiles.getItems().addAll("Docente", "Mentor", "Estudiante");
        departamentos.getItems().addAll("Antioquia", "Caldas");
        municipios.setDisable(true);
        universidades.setDisable(true);
    }

    public void onDepartamentoAction() {
        String[] ciudadesCal = new String[]{"Manizales", "Supia"};
        String[] ciudadesAnt = new String[]{"Medellín", "Apartadó"};
        String itemSeleccionado = departamentos.getValue().toString();
        /*switch (itemSeleccionado) {

            case "Seleccionar":

                municipios.setDisable(false);
                universidades.setDisable(false);
                break;

            case "Antioquia":
                municipios.setDisable(true);
                int i = ciudadesAnt.length;

                for (i = i - 1; i > 0; i--) {
                    municipios
                }

                if (municipios.getItemCount() == 1) {
                    for (i = 0; i < ciudadesAnt.length; i++) {
                        municipios.addItem(ciudadesAnt[i]);
                    }
                }
                break;
            case "Caldas":
                municipios.setEnabled(true);
                i = municipios.getItemCount();

                for (i = i - 1; i > 0; i--) {
                    municipios.removeItemAt(i);
                }

                if (municipios.getItemCount() == 1) {
                    for (i = 0; i < ciudadesCal.length; i++) {
                        municipios.addItem(ciudadesCal[i]);
                    }
                }
                break;

        }*/
    }



    // ## Estos son los metodos de los botones

    // Navega el boton registrar asi el menu, validando todos los datos
    public void onBotonRegistrarAction() throws IOException {
        validarDatos();
    }

    // Navega el boton volver asía la ventana anterior
    public void onBotonVolverAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-principal.fxml"));
        panelRegistro.getChildren().setAll(pane);
    }

    // El hyperlink navega hacia la ventana, es decir, iniciar sesion
    public void onHyperInicioAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-login.fxml"));
        panelRegistro.getChildren().setAll(pane);
    }
}