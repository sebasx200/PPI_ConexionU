package com.clases_controladoras.funcionalidades_menu;

import com.clases.*;
import com.clases.clases_tabla.AsesoriaTabla;
import com.clases.modelos.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
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
    private Label titulo;
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
    @FXML
    private TableColumn<AsesoriaTabla, Void> colAccion;
    private AsesoriaTabla usuario;
    private Usuario usuarioLogin;
    DataSingleton data = DataSingleton.getInstance();

    /** Se recupera el usuario que inició sesión para validar el perfil y cambiar el color del título y recuperar los datos de la base
     * de datos, se inicializan las columnas de las tablas y se le agregan los datos*/
    public void initialize(){
        usuarioLogin = data.getUsuario();
        colorTitulo();
        listaUsuarios = FXCollections.observableArrayList();
        obtenerRegistros();
        colNombre.setCellValueFactory(cellData -> cellData.getValue().estudianteProperty());
        colAsesor.setCellValueFactory(cellData -> cellData.getValue().asesorProperty());
        colMotivoAsesoria.setCellValueFactory(cellData -> cellData.getValue().motivoProperty());
        colFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        colHora.setCellValueFactory(cellData -> cellData.getValue().horaProperty());
        /** Se agrega la columna en donde van a estar los botones de editar y eliminar asesoría y se les agrega una imagen a cada uno*/
        colAccion.setCellFactory(new Callback<TableColumn<AsesoriaTabla, Void>, TableCell<AsesoriaTabla, Void>>() {
            @Override
            public TableCell<AsesoriaTabla, Void> call(final TableColumn<AsesoriaTabla, Void> param) {
                return new TableCell<AsesoriaTabla, Void>() {
                    private final Button editarButton = createButton("/imagenes/iconos/boligrafo.png");
                    private final Button eliminarButton = createButton("/imagenes/iconos/boton-x.png");

                    {
                        editarButton.setOnAction(event -> {
                            // aquí va a ir lo que hace el botón editar
                        });

                        eliminarButton.setOnAction(event -> {
                            // aquí va a ir lo que hace el botón eliminar

                        });
                    }
                    /** se agregan los botones a las filas de la tabla en caso de que sí tenga registros*/
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new ButtonBox(editarButton, eliminarButton));
                        }
                    }
                };
            }
        });
        tabla.setItems(listaUsuarios);

    }
/** Se recuperan los registro del usuario que inició sesión pasándole como clave el usuario*/
    private void obtenerRegistros(){
        int posUser;
        if(usuarioLogin.getPerfil().equals("Estudiante")){
            posUser = 1;
        } else{
            posUser = 2;
        }
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
                        usuari = dataFormatter.formatCellValue(fila.getCell(posUser));
                        String asesor = dataFormatter.formatCellValue(fila.getCell(3));
                        String motivo = dataFormatter.formatCellValue(fila.getCell(4));
                        String fecha = dataFormatter.formatCellValue(fila.getCell(5));
                        String hora = dataFormatter.formatCellValue(fila.getCell(6));
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
    /** Se crea una clase auxiar que sirve para añadir los botones que van a ir en la tabla para que puedan acomodar de
     * manera horizontal*/
    private static class ButtonBox extends javafx.scene.layout.HBox {
        public ButtonBox(Button... buttons) {
            getChildren().addAll(buttons);
        }
    }
    /** Función que crea los botones*/
    private Button createButton(String imageName) {
        Image image = new Image(getClass().getResourceAsStream(imageName));
        ImageView imageView = new ImageView(image);
        Button button = new Button("", imageView);
        return button;
    }
    // se cambia el título de la ventana dependiendo del usuario.
    private void colorTitulo(){
        if(usuarioLogin.getPerfil().equals("Estudiante")){
            titulo.setTextFill(Color.WHITE);
        }
    }
}