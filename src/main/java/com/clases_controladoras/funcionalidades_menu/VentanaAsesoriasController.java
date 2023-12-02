package com.clases_controladoras.funcionalidades_menu;

import com.clases.*;
import com.clases.clases_tabla.NotificacionesTabla;
import com.clases.modelos.AgendaSemanal;
import com.clases.modelos.Asesoria;
import com.clases.modelos.Notificacion;
import com.clases.modelos.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VentanaAsesoriasController {

    @FXML
    private ComboBox<Usuario> comboAsesor;
    @FXML
    private ComboBox<String> comboMotivo, comboHora;
    @FXML
    private DatePicker elegirFecha;
    @FXML
    private CheckBox checkDocente, checkMentor;
    private Usuario usuario, userDocente, userMentor;
    DataSingleton data = DataSingleton.getInstance();

    public void initialize() throws IOException {
        usuario = data.getUsuario();
        onElegirFechaAction();
    }

    @FXML
    private void onComboHoraClick(){
        for (int hora = 6; hora < 12; hora++) {
            for (int minuto = 0; minuto < 60; minuto += 20) {
                int horaFin = hora;
                int minutoFin = minuto + 20;
                if (minutoFin >= 60) {
                    horaFin++;
                    minutoFin = 0;
                }
                String horaInicio = String.format("%02d:%02d", hora, minuto);
                String horaFinStr = String.format("%02d:%02d", horaFin, minutoFin);
                String rangoHoras = horaInicio + " - " + horaFinStr;
                comboHora.getItems().add(rangoHoras);
            }
        }
    }

    @FXML
    private void onElegirFechaAction() {
        elegirFecha.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Deshabilita los días fuera de la semana actual
                if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().with(DayOfWeek.SUNDAY))) {
                    setDisable(true);
                } else {
                    setDisable(false);
                }
            }
        });
    }
    @FXML
    private void onCheckDocenteAction(){
        if(checkMentor.isSelected()){
            checkDocente.setSelected(false);
        }
    }
    @FXML
    private void onCheckMentorAction(){
        if(checkDocente.isSelected()){
            checkMentor.setSelected(false);
        }
    }
    @FXML
    private void onComboAsesorClick() throws IOException {
        if(checkDocente.isSelected()){
            comboAsesor.getItems().clear();
            comboAsesor.getItems().setAll(recuperarDocentes());
        } else if(checkMentor.isSelected()){
            comboAsesor.getItems().clear();
            comboAsesor.getItems().setAll(recuperarMentores());
        } else {
            comboAsesor.getItems().clear();
        }
    }
    @FXML
    private String getFecha(){
        LocalDate fecha = elegirFecha.getValue();
        return fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    @FXML
    private void onComboMotivoClick(){
        String[] motivos = new String[]{"Consulta general", "Consulta tema de clase",
        "Asesoría PPI", "Consulta sobre quiz"};
        comboMotivo.getItems().clear();
        comboMotivo.getItems().setAll(motivos);
    }

    @FXML
    private void onBotonSeleccionarAction() throws IOException {
        agregarAsesoria();
    }

    /** Este método es usado para recuperar todos los valores del usuario que inició sesión en la aplicación*/
    private Usuario recuperarValoresUsuario() throws IOException {
        String clave = usuario.getUsuario();
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
                return resultado;
            }
        }
        return null;
    }
    private void agregarAsesoria() throws IOException {
        ArrayList<Asesoria> asesorias = new ArrayList<>();
        Usuario userInicioSesion = recuperarValoresUsuario();
        Asesoria nuevaAsesoria;
        if(validarDatos()){
            String estudiante = userInicioSesion.getNombre() + " " + userInicioSesion.getApellido();
            String user = usuario.getUsuario();
            String userAsesor = comboAsesor.getValue().getUsuario();
            String asesor = comboAsesor.getValue().getNombre() + " " + comboAsesor.getValue().getApellido();
            String motivo = comboMotivo.getValue();
            String fecha = getFecha();
            String hora = comboHora.getValue();

            nuevaAsesoria = new Asesoria(estudiante, user, userAsesor, asesor, motivo, fecha, hora, "Activo");
            asesorias.add(nuevaAsesoria);

            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(3);
            XSSFSheet hoja1 = libroExcel.getSheetAt(5);

            int ultimaFila = hoja.getLastRowNum();

            for (Asesoria asesoria : asesorias) {
                Row nuevaFila = hoja.createRow(ultimaFila + 1);
                nuevaFila.createCell(0).setCellValue(asesoria.getEstudiante());
                nuevaFila.createCell((1)).setCellValue((asesoria.getUsuario()));
                nuevaFila.createCell(2).setCellValue(asesoria.getUsuarioAsesor());
                nuevaFila.createCell(3).setCellValue(asesoria.getAsesor());
                nuevaFila.createCell(4).setCellValue(asesoria.getMotivo());
                nuevaFila.createCell(5).setCellValue(asesoria.getFecha());
                nuevaFila.createCell(6).setCellValue(asesoria.getHora());
                nuevaFila.createCell(7).setCellValue(asesoria.getHora());
                ultimaFila ++ ;
            }

            String fechaHora = obtenerFechaHoraActual();
            NotificacionesTabla notificacion = new NotificacionesTabla("Nueva asesoría agendada", fechaHora, usuario.getNombre()+usuario.getApellido(),
                    comboAsesor.getValue().getUsuario(), "Se ha agendado una nueva asesoría, REVISAR EN MIS ASESORÍAS");

            // El usuario no existe, agrega una nueva fila con los valores para el día de la semana
            int lastRowIndex = hoja1.getLastRowNum();
            Row newRow = hoja1.createRow(lastRowIndex + 1);
            newRow.createCell(0).setCellValue(notificacion.getTitulo());
            newRow.createCell(1).setCellValue(notificacion.getFechaHora());
            newRow.createCell(2).setCellValue(notificacion.getEmisor());
            newRow.createCell(3).setCellValue(notificacion.getMensaje());
            newRow.createCell(4).setCellValue(notificacion.getReceptor());

            try (FileOutputStream archivoSalida = new FileOutputStream("src/main/resources/datos/registros.xlsx")) {
                libroExcel.write(archivoSalida);
                Mensajes.mensajeInformativo("Se ha agendado exitosamente la asesoría" , "Datos asesoría" +"\n"+
                        "Asesor: " + nuevaAsesoria.getAsesor()+"\n"+
                        "Fecha y hora: " + nuevaAsesoria.getFecha() + " " + nuevaAsesoria.getHora());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    private Usuario[] recuperarDocentes() throws IOException {

        FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
        XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
        XSSFSheet hoja = libroExcel.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();

        int primeraFila = hoja.getFirstRowNum() + 1;
        int ultimaFila = hoja.getLastRowNum();
        Usuario [] registroDocentes = new Usuario[ultimaFila];

        for (int i = primeraFila; i <= ultimaFila; i++) {
            Row fila = hoja.getRow(i);
            if (fila != null) {
                for (int j = 0; j < fila.getLastCellNum(); j++) {
                    String nombre = dataFormatter.formatCellValue(fila.getCell(0));
                    String apellido = dataFormatter.formatCellValue(fila.getCell(1));
                    String usuario = dataFormatter.formatCellValue(fila.getCell(3));
                    userDocente = new Usuario();
                    userDocente.setNombre(nombre);
                    userDocente.setApellido(apellido);
                    userDocente.setUsuario(usuario);
                }
                registroDocentes[i-1] = userDocente;
            }
        }
        return registroDocentes;
    }
    private Usuario[] recuperarMentores() throws IOException {

        FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
        XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
        XSSFSheet hoja = libroExcel.getSheetAt(2);

        DataFormatter dataFormatter = new DataFormatter();

        int primeraFila = hoja.getFirstRowNum() + 1;
        int ultimaFila = hoja.getLastRowNum();
        Usuario [] registroMentores = new Usuario[ultimaFila];

        for (int i = primeraFila; i <= ultimaFila; i++) {
            Row fila = hoja.getRow(i);
            if (fila != null) {
                for (int j = 0; j < fila.getLastCellNum(); j++) {
                    String nombre = dataFormatter.formatCellValue(fila.getCell(0));
                    String apellido = dataFormatter.formatCellValue(fila.getCell(1));
                    String usuario = dataFormatter.formatCellValue(fila.getCell(3));
                    userMentor = new Usuario();
                    userMentor.setNombre(nombre);
                    userMentor.setApellido(apellido);
                    userMentor.setUsuario(usuario);

                }
                registroMentores[i-1] = userMentor;
            }
        }
        return registroMentores;
    }
    private String obtenerFechaHoraActual() {
        // Obtiene la fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Define el formato deseado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

        // Formatea la fecha y hora
        return fechaHoraActual.format(formato);
    }
    private boolean validarDatos(){
        if(!checkDocente.isSelected() && !checkMentor.isSelected()){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado el tipo de asesor");
            return false;
        }
        if(comboAsesor.getValue() == null){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado un asesor");
            return false;
        }
        if(comboMotivo.getValue() == null){
            Mensajes.mensajeAdvertencia("", "No ha un seleccionado un motivo para la asesoría");
            return false;
        }
        if(elegirFecha.getValue() == null){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado una fecha");
            return false;
        }
        if(comboHora.getValue() == null){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado una hora");
            return false;
        }
        return true;
    }
}
