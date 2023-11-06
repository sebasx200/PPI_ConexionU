package com.clases_controladoras.funcionalidades_menu;

import com.clases.*;
import com.clases.clases_tabla.AsesoriaTabla;
import com.clases.modelos.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VentanaMisAsesoriasController {
    @FXML
    private TableView<AsesoriaTabla> tabla;
    private ObservableList<AsesoriaTabla> listaUsuarios;
    @FXML
    private TableColumn<AsesoriaTabla, String> colNombre;
    @FXML
    private TableColumn<AsesoriaTabla, String> colAsesor;
    @FXML
    private TableColumn<AsesoriaTabla, String> colMotivoAsesoria;
    @FXML
    private TableColumn<AsesoriaTabla, String> colFecha;
    @FXML
    private TableColumn<AsesoriaTabla, String> colHora;
    private AsesoriaTabla usuario;
    private Usuario usuarioLogin;
    DataSingleton data = DataSingleton.getInstance();

    public void initialize(){
        listaUsuarios = FXCollections.observableArrayList();
        obtenerRegistros();
        colNombre.setCellValueFactory(cellData -> cellData.getValue().estudianteProperty());
        colAsesor.setCellValueFactory(cellData -> cellData.getValue().asesorProperty());
        colMotivoAsesoria.setCellValueFactory(cellData -> cellData.getValue().motivoProperty());
        colFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        colHora.setCellValueFactory(cellData -> cellData.getValue().horaProperty());
        tabla.setItems(listaUsuarios);

    }

    private void obtenerRegistros(){
        usuarioLogin = data.getUsuario();
        try {
            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(3); // cambiar posicion
            DataFormatter dataFormatter = new DataFormatter();

            int primeraFila = hoja.getFirstRowNum() + 1;
            int ultimaFila = hoja.getLastRowNum();
            String usuari = "";
            for (int i = primeraFila; i <= ultimaFila; i++) {
                Row fila = hoja.getRow(i);
                if (fila != null) {
                    for (int j = 0; j < fila.getLastCellNum(); j++) {

                        String nombre = dataFormatter.formatCellValue(fila.getCell(0));
                        usuari = dataFormatter.formatCellValue(fila.getCell(1));
                        String asesor = dataFormatter.formatCellValue(fila.getCell(2));
                        String motivo = dataFormatter.formatCellValue(fila.getCell(3));
                        String fecha = dataFormatter.formatCellValue(fila.getCell(4));
                        String hora = dataFormatter.formatCellValue(fila.getCell(5));
                        usuario = new AsesoriaTabla(nombre, usuari, asesor, motivo, fecha, hora);

                    }
                    if (usuari.equals(data.getUsuario().getUsuario())) {
                        listaUsuarios.add(usuario);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Map<String, AsesoriaTabla> asesoriaMap = new HashMap<>();
        for (AsesoriaTabla asesoria : listaUsuarios) {
            asesoriaMap.put(asesoria.getUsuario(), asesoria);
        }
    }
}