package com.clases_controladoras.funcionalidades_menu;

import com.clases.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VentanaAsesoriasController {

    @FXML
    private ComboBox<String> comboAsesor, comboMotivo, comboHora;
    @FXML
    private DatePicker elegirFecha;
    @FXML
    private CheckBox checkDocente, checkMentor;
    private Usuario usuario;
    DataSingleton data = DataSingleton.getInstance();

    public void initialize(){
        usuario = data.getUsuario();
        llenarHoras();
    }

    private void llenarHoras(){
        for (int hora = 0; hora < 24; hora++) {
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
    private void onComboAsesorClick(){
        String[] docentes = new String[]{"Maryem Ruíz", "Juan Carlos Gil", "Carlos Builes"};
        String[] mentores = new String[]{"Sebas Palacio", "Juan Pablo Ángel", "Daniel Henao"};
        if(checkDocente.isSelected()){
            comboAsesor.getItems().clear();
            comboAsesor.getItems().setAll(docentes);
        } else if(checkMentor.isSelected()){
            comboAsesor.getItems().clear();
            comboAsesor.getItems().setAll(mentores);
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
        String[] motivos = new String[]{"Consulta general", "Consulta tema de clase", "Consulta sobre módulos",
        "Asesoría académica", "Asesoría PPI", "Asesoría de la universidad"};
        comboMotivo.getItems().clear();
        comboMotivo.getItems().setAll(motivos);
    }

    @FXML
    private void onBotonSeleccionarAction() throws IOException {
        agregarAsesoria();
    }

    private void agregarAsesoria() throws IOException {
        ArrayList<Asesoria> asesorias = new ArrayList<>();
        Asesoria nuevaAsesoria;

        if(validarDatos()){
            String estudiante = usuario.getNombre() + " ";
            String asesor = comboAsesor.getValue();
            String motivo = comboMotivo.getValue();
            String fecha = getFecha();
            String hora = comboHora.getValue();
            nuevaAsesoria = new Asesoria(estudiante, asesor, motivo, fecha, hora);
            asesorias.add(nuevaAsesoria);

            FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
            XSSFSheet hoja = libroExcel.getSheetAt(3);

            int ultimaFila = hoja.getLastRowNum();

            /** se itera sobre el archivo de excel para añadir los datos del nuevo usuario*/

            for (Asesoria asesoria : asesorias) {
                Row nuevaFila = hoja.createRow(ultimaFila + 1);
                nuevaFila.createCell(0).setCellValue(asesoria.getEstudiante());
                nuevaFila.createCell(1).setCellValue(asesoria.getAsesor());
                nuevaFila.createCell(2).setCellValue(asesoria.getMotivo());
                nuevaFila.createCell(3).setCellValue(asesoria.getFecha());
                nuevaFila.createCell(4).setCellValue(asesoria.getHora());
                ultimaFila ++ ;
            }

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
