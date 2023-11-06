package com.clases_controladoras;

import com.clases.DataSingleton;
import com.clases.Mensajes;
import com.clases.modelos.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class VentanaOficinaController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private CheckBox siPlanta, noPlanta, siOficina, noOficina, siRegistro, noRegistro;
    @FXML
    private TextField inputBloque, inputOficina;
    @FXML
    private Button botoContinuar;
    DataSingleton data = DataSingleton.getInstance();


    public void initialize() {
    }

    @FXML
    private void onSiPlantaAction(){
        if(siPlanta.isSelected()){
            noPlanta.setSelected(false);
            siOficina.setDisable(false);
            noOficina.setDisable(false);
            siRegistro.setDisable(false);
            noRegistro.setDisable(false);
            inputBloque.setDisable(false);
            inputOficina.setDisable(false);
        }
    }
    @FXML
    private void onNoPlantaAction(){
        if(noPlanta.isSelected()){
            siPlanta.setSelected(false);
            siOficina.setSelected(false);
            noOficina.setSelected(false);
            siRegistro.setSelected(false);
            noRegistro.setSelected(false);
            siOficina.setDisable(true);
            noOficina.setDisable(true);
            siRegistro.setDisable(true);
            noRegistro.setDisable(true);
            inputBloque.setDisable(true);
            inputOficina.setDisable(true);
        }
    }
    @FXML
    private void onSiOficinaAction(){
        if(siOficina.isSelected()){
            noOficina.setSelected(false);
            siRegistro.setSelected(false);
            noRegistro.setSelected(false);
            siRegistro.setDisable(false);
            noRegistro.setDisable(false);
            inputBloque.setDisable(false);
            inputOficina.setDisable(false);
        }
    }
    @FXML
    private void onNoOficinaAction(){
        if(noOficina.isSelected()){
            siOficina.setSelected(false);
            siRegistro.setSelected(false);
            noRegistro.setSelected(false);
            siRegistro.setDisable(true);
            noRegistro.setDisable(true);
            inputBloque.setDisable(true);
            inputOficina.setDisable(true);
        }
    }
    @FXML
    private void onSiRegistroAction(){
        if(siRegistro.isSelected()){
            noRegistro.setSelected(false);
            inputBloque.setDisable(false);
            inputOficina.setDisable(false);
        }
    }
    @FXML
    private void onNoRegistroAction(){
        if(noRegistro.isSelected()){
            siRegistro.setSelected(false);
            inputBloque.setDisable(true);
            inputOficina.setDisable(true);
        }
    }

    public void onBotonContinuarAction() throws IOException {
        String oficina = obtenerDatoOficina();
        if (validarDatosOficina()) {
            agregarOficina(oficina);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-login.fxml"));
            rootPane.getChildren().setAll(pane);
        }
    }

    public void agregarOficina(String ofici) {
        ArrayList<Usuario> agregarOficina = new ArrayList<>();
        String oficina = ofici;

        Usuario nuevaOficina = new Usuario(oficina);

        agregarOficina.add(nuevaOficina);

        try {
            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(0); // cambiar posicion
            DataFormatter dataFormatter = new DataFormatter();

            int primeraFila = hoja.getFirstRowNum() + 1;
            int ultimaFila = hoja.getLastRowNum();
            String usuari = "";

            for (int i = primeraFila; i <= ultimaFila; i++) {
                Row fila = hoja.getRow(i);

                if (fila != null) {
                    for (int j = 0; j < fila.getLastCellNum(); j++) {
                        usuari = dataFormatter.formatCellValue(fila.getCell(3));

                    }
                    if (usuari.equals(data.getUsuario().getUsuario())) {
                        fila.createCell(11).setCellValue(nuevaOficina.getOficina());
                    }
                }
            }
            try (FileOutputStream archivoSalida = new FileOutputStream("src/main/resources/datos/registros.xlsx")) {
                libroExcel.write(archivoSalida);
                Mensajes.mensajeInformativo(null, "Se agregaron los datos de la oficina correctamente");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean validarDatosOficina(){
        if(!siPlanta.isSelected() && !noPlanta.isSelected()){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado si eres profesor de planta");
            return false;
        }
        if (!siRegistro.isSelected() && !noRegistro.isSelected() && !siRegistro.isDisable() && !noRegistro.isDisable()) {
            Mensajes.mensajeAdvertencia("", "No ha seleccionado si tienes oficina");
            return false;
        }

        if (!siOficina.isSelected() && !noOficina.isSelected() && !siOficina.isDisable() && !noOficina.isDisable()) {
            Mensajes.mensajeAdvertencia("", "No ha seleccionado si vas ha agregar la oficina");
            return false;
        }

        if((inputBloque.getText().isEmpty() || inputOficina.getText().isEmpty()) && !inputBloque.isDisable()){
            Mensajes.mensajeAdvertencia("", "Los campos estan vacios");
            return false;
        }
        return true;
    }

    public String obtenerDatoOficina() {
        String datoObtenido = "";
        if (noPlanta.isSelected()) {
            datoObtenido = "No es profesor de planta";
        } else if (noOficina.isSelected()) {
            datoObtenido = "No tiene oficina";
        } else if (noRegistro.isSelected()) {
            datoObtenido = "No registro la oficina";
        } else {
            datoObtenido = "Bloque " + inputBloque.getText() + " oficina " + inputOficina.getText();
        }
        return  datoObtenido;
    }
}
