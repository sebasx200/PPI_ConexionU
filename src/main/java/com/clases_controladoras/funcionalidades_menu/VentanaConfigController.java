package com.clases_controladoras.funcionalidades_menu;

import com.clases.DataSingleton;
import com.clases.Mensajes;
import com.clases.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class VentanaConfigController {

    @FXML
    private TextField inputUser, inputDocumento, inputNombre, inputApellido, inputCorreo, inputTelefono, inputPass, inputNuevaPass, inputConfirmarPass;
    DataSingleton data = DataSingleton.getInstance();

    private Usuario usuario;
    private String clave;

    public void initialize() throws IOException {
        usuario = data.getUsuario();
        clave = usuario.getUsuario();
        ponerDatosUsuario();
    }

    @FXML
    private void onBotonCambiarPassAction() throws IOException {
        if(validarPass()){
            cambiarPass(inputPass.getText(), inputNuevaPass.getText());
        }
    }


    private Usuario recuperarValoresUsuario() throws IOException {
        Usuario resultado = new Usuario();

        FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
        XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
        XSSFSheet hoja = libroExcel.getSheetAt(1);

        DataFormatter dataFormatter = new DataFormatter();

        int primeraFila = hoja.getFirstRowNum() + 1;
        int ultimaFila = hoja.getLastRowNum();

        for (int i = primeraFila; i <= ultimaFila; i++) {
            Row fila = hoja.getRow(i);
            Cell usuarioCell = fila.getCell(3);
            if (usuarioCell.getStringCellValue().equals(clave)) {
                resultado.setNombre(dataFormatter.formatCellValue(fila.getCell(0)));
                resultado.setApellido(dataFormatter.formatCellValue(fila.getCell(1)));
                resultado.setDocumento(dataFormatter.formatCellValue(fila.getCell(2)));
                resultado.setCorreo(dataFormatter.formatCellValue(fila.getCell(4)));
                resultado.setTelefono(dataFormatter.formatCellValue(fila.getCell(5)));
                resultado.setPassword(dataFormatter.formatCellValue(fila.getCell(6)));
                return resultado;
            }
        }
        return null;
    }
    private void ponerDatosUsuario() throws IOException {
        Usuario datosUsuario = recuperarValoresUsuario();
        if(datosUsuario!=null){
            inputUser.setText(usuario.getUsuario());
            inputDocumento.setText(datosUsuario.getDocumento());
            inputNombre.setText(datosUsuario.getNombre());
            inputApellido.setText(datosUsuario.getApellido());
            inputCorreo.setText(datosUsuario.getCorreo());
            inputTelefono.setText(datosUsuario.getTelefono());
            inputNombre.setText(datosUsuario.getNombre());
        } else{
            Mensajes.mensajeError("Error al cargar datos", "No se pudo recuperar los datos de usuario");
        }
    }
    private void cambiarPass(String passActual, String passNueva) throws IOException {
        Usuario cambioPass = recuperarValoresUsuario();
        if (passActual.equals(cambioPass.getPassword())) {
            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(1);

            int primeraFila = hoja.getFirstRowNum() + 1;
            int ultimaFila = hoja.getLastRowNum();

            for (int i = primeraFila; i <= ultimaFila; i++) {
                Row fila = hoja.getRow(i);
                Cell usuarioCell = fila.getCell(3);
                if (usuarioCell.getStringCellValue().equals(clave)) {
                    fila.getCell(6).setCellValue(passNueva);
                }
            }
            try (FileOutputStream archivoSalida = new FileOutputStream("src/main/resources/datos/registros.xlsx")) {
                libroExcel.write(archivoSalida);
                Mensajes.mensajeInformativo("", "El cambio de la contraseña ha sido exitoso");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else{
            Mensajes.mensajeError("Error al cambiar la contraseña", "La contraseña actual es incorrecta");
        }
    }

    private boolean validarPass(){
        if(inputPass.getText().isEmpty()){
            Mensajes.mensajeError("", "No ha digitado la contraseña actual");
            return false;
        }
        if(inputNuevaPass.getText().isEmpty()){
            Mensajes.mensajeError("", "No ha digitado la contraseña nueva");
            return false;
        }
        if(inputConfirmarPass.getText().isEmpty()){
            Mensajes.mensajeError("", "No ha digitado la confirmación de la contraseña nueva");
            return false;
        }
        if(!inputNuevaPass.getText().equals(inputConfirmarPass.getText())){
            Mensajes.mensajeError("", "La contraseña no coincide con la confirmación");
            return false;
        }
        return true;
    }
}
