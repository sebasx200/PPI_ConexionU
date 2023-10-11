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

    public void initialize(){

        perfiles.getItems().addAll("Docente", "Mentor", "Estudiante");

    }

    @FXML
    protected void onBotonIngresarAction(){

        String user = inputUser.getText();
        String pass = inputPass.getText();
        int validacion = inicioSesion(user, pass);
        if(!user.isEmpty() && !pass.isEmpty()){
            if(validacion ==1){
                Mensajes.mensajeInformativo("", "Bienvenido " + user);
            } else if (validacion==-1) {
                Mensajes.mensajeAdvertencia("La información no coincide", "Contraseña incorrecta");
            } else if (validacion==0) {
                Mensajes.mensajeInformativo("No hay datos relacionados", "Usuario no encontrado");
            }
        } else {
            Mensajes.mensajeError(null,"Por favor llenar todos los campos");
        }
    }
    public int inicioSesion(String user, String pass) {

        ArrayList<Docente> registrosActuales = new ArrayList<>();
        try {

            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");

            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(0);

            DataFormatter dataFormatter = new DataFormatter();

            int primeraFila = hoja.getFirstRowNum() + 1;
            int ultimaFila = hoja.getLastRowNum();

            for (int i = primeraFila; i <= ultimaFila; i++) {
                Row fila = hoja.getRow(i);

                if (fila != null || fila.equals("")) {

                    for (int j = 0; j < fila.getLastCellNum(); j++) {

                        String usuario = dataFormatter.formatCellValue(fila.getCell(3));
                        String password = dataFormatter.formatCellValue(fila.getCell(4));

                        Docente docente = new Docente(usuario, password);
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
                return 1;

            } else {
                return -1;
            }
        }
        return 0;
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

}
