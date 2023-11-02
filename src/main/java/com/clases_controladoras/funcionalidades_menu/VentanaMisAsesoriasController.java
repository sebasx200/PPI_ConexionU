package com.clases_controladoras.funcionalidades_menu;

import com.clases.Asesoria;
import com.clases.DataSingleton;
import com.clases.Usuario;
import com.clases.UsuarioTabla;
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
    private TableView<Asesoria> tabla;
    private ObservableList<Asesoria> listaUsuarios;
    @FXML
    private TableColumn<Asesoria, String> colNombre;
    @FXML
    private TableColumn<Asesoria, String> colAsesor;
    @FXML
    private TableColumn<Asesoria, String> colMotivoAsesoria;
    @FXML
    private TableColumn<Asesoria, String> colFecha;
    @FXML
    private TableColumn<Asesoria, String> colHora;
    private Asesoria usuario;
    private Usuario usuarioLogin;
    private Usuario user;
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
                        usuario = new Asesoria(nombre, usuari, asesor, motivo, fecha, hora);

                    }
                    if (usuari.equals(data.getUsuario().getUsuario())) {
                        listaUsuarios.add(usuario);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Map<String, Asesoria> asesoriaMap = new HashMap<>();
        for (Asesoria asesoria : listaUsuarios) {
            asesoriaMap.put(asesoria.getUsuario(), asesoria);
        }
    }
}