package com.clases_controladoras.funcionalidades_menu;

import com.clases.DataSingleton;
import com.clases.Usuario;
import com.clases.UsuarioTabla;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaTableViewController {

    /** Se declaran los atributos que va a tener la ventana de ver lista de registros
     * Los atributos serán los mismos para los demás perfiles*/

    // se declara la tabla principal que va a tener en su interior las columnas con objetos de tipo UsuarioTabla

    @FXML
    private TableView<UsuarioTabla> tabla;
    private ObservableList<UsuarioTabla> listaUsuarios;
    @FXML
    private TableColumn<UsuarioTabla, String> colNombre;
    @FXML
    private TableColumn<UsuarioTabla, String> colApellido;
    @FXML
    private TableColumn<UsuarioTabla, String> colDocumento; // Estas son las columnas que va a tener la tabla
    @FXML
    private TableColumn<UsuarioTabla, String> colCorreo;
    @FXML
    private TableColumn<UsuarioTabla, String> colTelefono;
    @FXML
    private Label titulo;
    private UsuarioTabla usuario;
    private Usuario usuarioLogin;
    DataSingleton data = DataSingleton.getInstance();

    /** Se declara el método que inicializa los objetos que se quieren mostrar apenas la ventana sea visible para el
    * usuario, en este caso se inicializa la tabla y se llena de manera automática con los registros de excel
    * dependiendo del perfil que haya iniciado sesión*/
    public void initialize(){
        usuarioLogin = data.getUsuario();
        if(usuarioLogin.getPerfil().equals("Estudiante")){
            titulo.setTextFill(Color.WHITE);
        }

        listaUsuarios = FXCollections.observableArrayList();
        obtenerRegistros();
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        colDocumento.setCellValueFactory(cellData -> cellData.getValue().documentoProperty());
        colCorreo.setCellValueFactory(cellData -> cellData.getValue().correoProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        tabla.setItems(listaUsuarios);
    }

    /** Este método sirve para cargar los datos del archivo de excel*/
    public void obtenerRegistros(){

        try {
            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(1); // cambiar posicion
            DataFormatter dataFormatter = new DataFormatter();

            int primeraFila = hoja.getFirstRowNum() + 1;
            int ultimaFila = hoja.getLastRowNum();

            for (int i = primeraFila; i <= ultimaFila; i++) {
                Row fila = hoja.getRow(i);
                if (fila != null) {
                    for (int j = 0; j < fila.getLastCellNum(); j++) {

                        String nombre = dataFormatter.formatCellValue(fila.getCell(0));
                        String apellido = dataFormatter.formatCellValue(fila.getCell(1));
                        String documento = dataFormatter.formatCellValue(fila.getCell(2));
                        String correo = dataFormatter.formatCellValue(fila.getCell(4));
                        String telefono = dataFormatter.formatCellValue(fila.getCell(5));

                        usuario = new UsuarioTabla(nombre, apellido, documento, correo, telefono);
                    }
                    listaUsuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
