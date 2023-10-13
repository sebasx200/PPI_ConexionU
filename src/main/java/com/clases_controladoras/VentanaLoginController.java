package com.clases_controladoras;

import com.clases.Docente;
import com.clases.Mensajes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VentanaLoginController {

    @FXML
    AnchorPane rootPane;
    @FXML
    private TextField inputUser;
    @FXML
    private PasswordField inputPass;
    @FXML
    private Button botonIngresar;
    @FXML
    private Button botonVolver;
    @FXML
    private Hyperlink linkRegistro;
    @FXML
    private ComboBox perfiles;
    private String nombreEncontrado;

    public void initialize(){
        perfiles.getItems().addAll("Docente", "Mentor", "Estudiante");

    }

    /** Validaciones para que los campos no esten vacios. */
    public boolean validaciones() {
        boolean datos = true;
        if (inputUser.getText().isEmpty() ||
                inputPass.getText().isEmpty()) {
            datos = false;
            Mensajes.mensajeError(null, "Los campos estan vacio");
        } else if(perfiles.getValue() == null && datos){
            datos=false;
            Mensajes.mensajeError( null, "No seleccionó ningún perfil");
        }
        return datos;
    }

    public int inicioSesion(String user, String pass, int posicion) {
        ArrayList<Docente> registrosActuales = new ArrayList<>();
        try {
            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(posicion);

            DataFormatter dataFormatter = new DataFormatter();

            int primeraFila = hoja.getFirstRowNum() + 1;
            int ultimaFila = hoja.getLastRowNum();

            for (int i = primeraFila; i <= ultimaFila; i++) {
                Row fila = hoja.getRow(i);
                if (fila != null) {
                    for (int j = 0; j < fila.getLastCellNum(); j++) {
                        String nombre = dataFormatter.formatCellValue(fila.getCell(0));
                        String usuario = dataFormatter.formatCellValue(fila.getCell(3));
                        String password = dataFormatter.formatCellValue(fila.getCell(6));
                        Docente docente = new Docente(nombre, usuario, password);
                        registrosActuales.add(docente);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Map<String, Docente> mapaDocentes = new HashMap<>();
        for (Docente docente : registrosActuales) {
            mapaDocentes.put(docente.getUsuario(), docente);
        }
        if (mapaDocentes.containsKey(user)) {
            Docente docenteEncontrado = mapaDocentes.get(user);
            String passRegistrada = docenteEncontrado.getPassword();
            if (pass.equals(passRegistrada)) {
                nombreEncontrado = retornarNombre(docenteEncontrado.getNombre());
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

    /** Boton iniciar sesion, se valida todo antes de iniciar sesion. */
    @FXML
    protected void onBotonIngresarAction() throws IOException{
        String user = inputUser.getText();
        String pass = inputPass.getText();
        boolean dato = validaciones();
        if (dato == true) {
            String seleccion = perfiles.getValue().toString();
            switch (seleccion) {
                case "Docente":
                    int posicion = 0;
                    int validacion = inicioSesion(user, pass, posicion);
                    if (validacion == 1) {
                        Mensajes.mensajeInformativo("", "Bienvenido docente " + nombreEncontrado);
                    } else if (validacion == -1) {
                        Mensajes.mensajeAdvertencia("La información no coincide", "Contraseña incorrecta");
                    } else if (validacion == 0) {
                        Mensajes.mensajeInformativo("No hay datos relacionados", "Usuario no encontrado");
                    }
                    break;
                case "Mentor":
                    posicion = 2;
                    validacion = inicioSesion(user, pass, posicion);
                    if (validacion == 1) {
                        Mensajes.mensajeInformativo("", "Bienvenido mentor " + nombreEncontrado);
                    } else if (validacion == -1) {
                        Mensajes.mensajeAdvertencia("La información no coincide", "Contraseña incorrecta");
                    } else if (validacion == 0) {
                        Mensajes.mensajeInformativo("No hay datos relacionados", "Usuario no encontrado");
                    }
                    break;
                case "Estudiante":
                    posicion = 1;
                    validacion = inicioSesion(user, pass, posicion);
                    if (validacion == 1) {
                        Mensajes.mensajeInformativo("", "Bienvenido estudiante " + nombreEncontrado);
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-menu.fxml"));
                        rootPane.getChildren().setAll(pane);
                    } else if (validacion == -1) {
                        Mensajes.mensajeAdvertencia("La información no coincide", "Contraseña incorrecta");
                    } else if (validacion == 0) {
                        Mensajes.mensajeInformativo("No hay datos relacionados", "Usuario no encontrado");
                    }
                    break;
            }
        }
    }

    @FXML
    protected void onBotonVolverAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-principal.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void onLinkRegistroAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-registro.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    public String retornarNombre(String nombreRegistrado){
        String nombre;
        nombre = nombreRegistrado;
        return nombre;
    }

}
