package com.clases_controladoras;

import com.clases.Usuario;
import com.clases.Mensajes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaRegistro {

    @FXML
    private AnchorPane panelRegistro;

    @FXML
    private Button botonRegistro;

    @FXML
    private Button botonVolver;

    @FXML
    private Hyperlink hyperInicio;

    @FXML
    private ComboBox departamentos;

    @FXML
    private ComboBox ciudades;

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
    public boolean validarDatos() {
        boolean datosValidos = true;
        /** Se valida que todos los campos esten llenos */
        if (inputNombre.getText().isEmpty() || inputApellido.getText().isEmpty()
                || inputDocumento.getText().isEmpty() || inputUsuario.getText().isEmpty()
                || inputCorreo.getText().isEmpty() || inputTelefono.getText().isEmpty()
                || inputContraseña.getText().length() == 0 || inputConfirmarContraseña.getText().length() == 0
                || perfiles.getValue() == null
                || departamentos.getValue() == null
                || ciudades.getValue() == null
                || universidades.getValue() == null) {
            datosValidos = false;
            Mensajes.mensajeError(null, "Llenar todos los campos");
        }

        /** Se valida que ingrese un nombre con mas de 3 letras */
        if ((inputNombre.getText().length() < 3 || !inputNombre.getText().matches("^[a-zA-Z0-9]*$"))
                && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"El nombre " +
                    "debe ser mayor o igual a 3 letras, y no debes ingresar caracteres especiales");
        }

        /** Se valida que ingrese un apellido con mas de 3 letras */
        if ((inputApellido.getText().length() < 3 || !inputApellido.getText().matches("^[a-zA-Z0-9]*$"))
                && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"El nombre " +
                    "debe ser mayor o igual a 3 letras, y no debes ingresar caracteres especiales");
        }

        /**
         * Validamos el documento, que sea minimo
         * de 8 digitos y maximo de 10 digitos, y sean
         * números.
         */
        if ((inputDocumento.getText().length() < 8 || inputDocumento.getText().length() > 10
                || !inputDocumento.getText().matches("\\d*")) && datosValidos == true ) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null, "El documento debe " +
                    "ser mínimo de 8 dígitos y máximo de 10 dígitos, y debe ingresar solo números");
        }


        /** Validamos el usuario que tenga letras y números, y se mayor o igual de 4 digitos */
        if ((!validarUsuario(inputUsuario.getText()) ||
                inputUsuario.getText().length() < 4) && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"Usuario no válido, " +
                    "debe contener letras y números,");
        }

        /** Validamos el correo */
        if (!validarCorreo(inputCorreo.getText()) && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"Correo no válido");
        }

        /** Validamos el número de telefono */
        if ((inputTelefono.getText().length() != 10 || !inputTelefono.getText().matches("\\d*"))
                && datosValidos == true ) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null, "El número de teléfono debe ser de 10 dígitos," +
                    " y solo ingrese números");
        }

        // Se valida que la contraseña tenga mas de 4 digitos
        if (inputContraseña.getText().length() < 4 && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null, "Las contraseñas " +
                    "deben tener mas de 4 dígitos");
        }

        // Se validan las contraseñas que sean iguales
        if (!inputContraseña.getText().equals(inputConfirmarContraseña.getText()) && datosValidos == true) {
            datosValidos = false;
            Mensajes.mensajeInformativo(null,"Las contraseñas no son iguales");
        }
        return datosValidos;
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

    /** Estos son los metodos para comboBox */

    /** Se inicializa los comboBox principales que son: perfiles y departamentos */
    public void initialize() {
        perfiles.getItems().addAll("Docente", "Mentor", "Estudiante");
        departamentos.getItems().addAll( "Antioquia", "Caldas");
    }

    /**
     * Se habilita el comboBox siguiente, dependiendo lo
     * seleccionado del comboBox departamento
    */
    public void onDepartamentoAction() {
        /** Se hacen los arreglos que van a contener la informacion de las universidades de cada municipio */
        String[] ciudadesCal = new String[]{"Manizales", "Supia"};
        String[] ciudadesAnt = new String[]{"Medellín", "Apartadó"};
        /** Se toma lo seleccionado del comboBox cuidades y se guarda
         *  en la variable itemSeleccionado, para utilizarla en el
         *  switch. */
        String itemSeleccionado = departamentos.getValue().toString();
        switch (itemSeleccionado) {
            case "Seleccionar":
                ciudades.setDisable(true);
                universidades.setDisable(true);
                break;
            case "Antioquia":
                ciudades.setDisable(false);
                ciudades.getItems().clear();
                ciudades.getItems().addAll(ciudadesAnt);
                break;
            case "Caldas":
                ciudades.setDisable(false);
                ciudades.getItems().clear();
                ciudades.getItems().addAll(ciudadesCal);
                break;
        }
    }

    /**
     * Se ingresan los datos del comboBox siguiente, dependiendo lo que hayan
     * seleccionado anteriormente del comboBox departamento
     */
    public void onCiudadesAction() {
        String[] medellinUni = new String[]{"P.C.J.I.C", "UdeA"};
        String[] apartadoUni = new String[]{"Sede poli", "Sena"};
        String[] manizalesUni = new String[]{"Universidad de caldas", "Sena"};
        String[] supiaUni = new String[]{"Publica", "Sena"};
        String itemSeleccionado = ciudades.getValue().toString();
        switch (itemSeleccionado) {
            case "Seleccionar":
                universidades.setDisable(true);
                break;
            case "Medellín":
                universidades.setDisable(false);
                universidades.getItems().clear();
                universidades.getItems().addAll(medellinUni);
                break;
            case "Apartadó":
                universidades.setDisable(false);
                universidades.getItems().clear();
                universidades.getItems().addAll(apartadoUni);
                break;
            case "Manizales":
                universidades.setDisable(false);
                universidades.getItems().clear();
                universidades.getItems().addAll(manizalesUni);
                break;
            case "Supia":
                universidades.setDisable(false);
                universidades.getItems().clear();
                universidades.getItems().addAll(supiaUni);
                break;
        }
    }

    /**
     * Estos son los métodos de los botones de la ventana registro.
     * Se ponen a navegar todos los botones a su respectiva ventana.
    */
    public void onBotonRegistrarAction() throws IOException {
        boolean dato = validarDatos();
            if (dato == true) {
                if (perfiles.getValue().toString().equals("Docente")) {
                    int posicion = 0;
                    agregarDatos(posicion);
                } else if (perfiles.getValue().toString().equals("Estudiante")) {
                    int posicion = 1;
                    agregarDatos(posicion);
                } else {
                    int posicion = 2;
                    agregarDatos(posicion);
                }
            }
    }

    /** Boton Volver */
    public void onBotonVolverAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-principal.fxml"));
        panelRegistro.getChildren().setAll(pane);
    }

    /** Heperlink Inicio De Sesion */
    public void onHyperInicioAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/ppi_conexionu/ventana-login.fxml"));
        panelRegistro.getChildren().setAll(pane);
    }

    /** Método para ingresar los datos */
    public void agregarDatos(int posicion) {
        ArrayList<Usuario> nuevoRegistro = new ArrayList<>();

        String nombre = inputNombre.getText();
        String apellido = inputApellido.getText();
        String documento = inputDocumento.getText();
        String user = inputUsuario.getText();
        String correo = inputCorreo.getText();
        String telefono = inputTelefono.getText();
        String password = inputContraseña.getText();
        String perfil = perfiles.getValue().toString();
        String departamento = departamentos.getValue().toString();
        String ciudad = ciudades.getValue().toString();
        String universidad = universidades.getValue().toString();

        // se capturan los datos ingresados por el usuario en los campos de texto y opciones del menú

        Usuario usuario = new Usuario(nombre, apellido, documento,user, correo, telefono,
                password, perfil, departamento, ciudad, universidad);

        /** se asignan los datos como un nuevo usuario y se guarda en una lista de tipo usuario*/

        nuevoRegistro.add(usuario);

        try{
            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(posicion);

            int ultimaFila = hoja.getLastRowNum();

            /** se itera sobre el archivo de excel para añadir los datos del nuevo usuario*/

            for (Usuario usuarioNuevo : nuevoRegistro) {
                Row nuevaFila = hoja.createRow(ultimaFila + 1);
                nuevaFila.createCell(0).setCellValue(usuarioNuevo.getNombre());
                nuevaFila.createCell(1).setCellValue(usuarioNuevo.getApellido());
                nuevaFila.createCell(2).setCellValue(usuarioNuevo.getDocumento());
                nuevaFila.createCell(3).setCellValue(usuarioNuevo.getUsuario());
                nuevaFila.createCell(4).setCellValue(usuarioNuevo.getCorreo());
                nuevaFila.createCell(5).setCellValue(usuarioNuevo.getTelefono());
                nuevaFila.createCell(6).setCellValue(usuarioNuevo.getPassword());
                nuevaFila.createCell(7).setCellValue(usuarioNuevo.getPerfil());
                nuevaFila.createCell(8).setCellValue(usuarioNuevo.getDepartamento());
                nuevaFila.createCell(9).setCellValue(usuarioNuevo.getCiudad());
                nuevaFila.createCell(10).setCellValue(usuarioNuevo.getUniversidad());
                ultimaFila ++ ;
            }

            // se llama al método para ver la respuesta, si es falsa se procede a agregar el nuevo usuario
            boolean existeRegistro = existeNuevoRegistro(nuevoRegistro, posicion);

            if (existeRegistro == false) {
                try (FileOutputStream archivoSalida = new FileOutputStream("src/main/resources/datos/registros.xlsx")) {
                    libroExcel.write(archivoSalida);
                    Mensajes.mensajeInformativo(null, "Usuario registrado correctamente");
                }
            } else {
                Mensajes.mensajeInformativo(null, "El usuario o documento ya están registrados");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Este médoto es usado para comparar el usuario nuevo ingresado con los registros que hay para ver que no hayan
    datos repedidos tales como el documento o el usuario
     */
    public  boolean existeNuevoRegistro(ArrayList<Usuario> nuevoRegistro, int posicion) {
        ArrayList<Usuario> registroActual = new ArrayList<>();
        boolean existeRegistro = false;

        try {
            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(posicion); // cambiar posicion
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
                        String user = dataFormatter.formatCellValue(fila.getCell(3));
                        String correo = dataFormatter.formatCellValue(fila.createCell(4));
                        String telefono = dataFormatter.formatCellValue(fila.getCell(5));
                        String password = dataFormatter.formatCellValue(fila.getCell(6));
                        String perfil = dataFormatter.formatCellValue(fila.getCell(7));
                        String departamento = dataFormatter.formatCellValue(fila.createCell(8));
                        String ciudad = dataFormatter.formatCellValue(fila.getCell(9));
                        String univesidad = dataFormatter.formatCellValue(fila.getCell(10));

                        Usuario usuario = new Usuario(nombre, apellido, documento, user, correo, telefono,
                                password, perfil, departamento, ciudad, univesidad);

                        registroActual.add(usuario);
                    }
                }
            }
            for (Usuario n : nuevoRegistro) {
                for (Usuario a : registroActual) {
                    if (n.getDocumento() == a.getDocumento()) {
                        existeRegistro = true;
                    } else if (n.getUsuario().equals(a.getUsuario())) {
                        existeRegistro = true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return existeRegistro;
    }

    @FXML
    protected void onBotonRegistrarMouseEntered(){
        botonRegistro.setStyle("-fx-background-color: #1976d2;" +
                " -fx-text-fill: white; -fx-background-radius: 40;" +
                " -fx-border-color: white; -fx-border-radius: 40;");
    }
    @FXML
    protected void onBotonRegistrarMouseExited(){
        botonRegistro.setStyle("-fx-background-color: #2265E8;" +
                " -fx-text-fill: black; -fx-background-radius: 40;" +
                " -fx-border-color: black; -fx-border-radius: 40;");
    }
}