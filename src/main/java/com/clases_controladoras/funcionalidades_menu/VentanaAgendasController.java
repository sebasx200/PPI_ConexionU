package com.clases_controladoras.funcionalidades_menu;

import com.clases.DataSingleton;
import com.clases.Mensajes;
import com.clases.modelos.AgendaSemanal;
import com.clases.modelos.Asesoria;
import com.clases.modelos.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class VentanaAgendasController {
    @FXML
    private ComboBox<String> horaInicial, horaFinal, comboDias;
    private AgendaSemanal agendaSemanal;
    private Usuario usuarioLogin;
    DataSingleton data = DataSingleton.getInstance();
    public void initialize(){
        usuarioLogin = data.getUsuario();
        llenarHoras();
    }
    /** Evento del botón confirmar en el cual se calculará la fecha seleccionada por el usuario*/
    @FXML
    private void onBotonConfirmarAction() throws IOException {
        if(validaciones()) {
            String dia = comboDias.getValue();
            DayOfWeek dayOfWeek = traducirDia(dia);
            LocalDate fecha = LocalDate.now().with(TemporalAdjusters.nextOrSame(dayOfWeek));
            String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            String horaInicio = horaInicial.getValue();
            String horaFin = horaFinal.getValue();
            boolean mensaje = Mensajes.mensajeConfirmacion("Día y horas seleccionadas",
                    "El día " + dia + " " + fechaFormateada + " dará asesorías entre las horas " + horaInicio + "-" + horaFin,
                    "¿Está seguro/a de la información proporcionada?");
            if(mensaje){
                ArrayList<AgendaSemanal> agendas = new ArrayList<>();
                String userAsesor = usuarioLogin.getUsuario();
                agendaSemanal = new AgendaSemanal(userAsesor, horaInicio, horaFin);
                agendas.add(agendaSemanal);

                FileInputStream archivoExcel = new FileInputStream("src/main/resources/datos/registros.xlsx");
                XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel);
                XSSFSheet hoja = libroExcel.getSheetAt(4);

                int ultimaFila = hoja.getLastRowNum();

                for (AgendaSemanal agenda : agendas) {
                    Row nuevaFila = hoja.createRow(ultimaFila + 1);
                    nuevaFila.createCell(0).setCellValue(agenda.getUserAsesor());
                    nuevaFila.createCell((1)).setCellValue(agenda.getHoraInicial()+"-"+agenda.getHoraFinal());
                    ultimaFila++;
                }

                try (FileOutputStream archivoSalida = new FileOutputStream("src/main/resources/datos/registros.xlsx")) {
                    libroExcel.write(archivoSalida);
                    Mensajes.mensajeInformativo("", "Se registró correctamente la información");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    /**aquí se llena el comboBox de la hora de inicio y final para que el usuario escoja las horas en las cuales
     * va a dar asesorías */
    private void llenarHoras(){
        for (int hora = 6; hora < 22; hora++) {
            for (int minuto = 0; minuto < 60; minuto += 60) {
                int horaFin = hora;
                int minutoFin = minuto + 60;
                if (minutoFin >= 60) {
                    horaFin++;
                    minutoFin = 0;
                }
                String horaInicio = String.format("%02d:%02d", hora, minuto);
                String horaFinStr = String.format("%02d:%02d", horaFin, minutoFin);
                String rangoHoras = horaInicio + " - " + horaFinStr;
                horaInicial.getItems().add(horaInicio);
                horaFinal.getItems().add(horaFinStr);
            }
        }
    }
    /** Se llena el comboBox de días de la semana con los días*/
    @FXML
    private void onComboDiasClick(){
        String[] dias = new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        comboDias.getItems().clear();
        comboDias.getItems().setAll(dias);
    }
    /** En este switch se traduce el día seleccionado por el usuario para que pueda calcularse la fecha*/
    private DayOfWeek traducirDia(String nombreDia) {
        switch (nombreDia) {
            case "Lunes":
                return DayOfWeek.MONDAY;
            case "Martes":
                return DayOfWeek.TUESDAY;
            case "Miércoles":
                return DayOfWeek.WEDNESDAY;
            case "Jueves":
                return DayOfWeek.THURSDAY;
            case "Viernes":
                return DayOfWeek.FRIDAY;
            case "Sábado":
                return DayOfWeek.SATURDAY;
            case "Domingo":
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Nombre del día no válido: " + nombreDia);
        }
    }
    private boolean validaciones(){
        if(comboDias.getValue() == null){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado un día de la semana");
            return false;
        }
        if(horaInicial.getValue() == null){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado una hora inicial para dar asesorías");
            return false;
        }
        if(horaFinal.getValue() == null){
            Mensajes.mensajeAdvertencia("", "No ha seleccionado una hora final para dar asesorías");
            return false;
        }
        String horaInicialCombo = horaInicial.getValue();
        LocalTime horaInicialConvertida = LocalTime.parse(horaInicialCombo);

        int horaInicial = horaInicialConvertida.getHour();

        String horaFinalCombo = horaFinal.getValue();
        LocalTime horaFinalConvertida = LocalTime.parse(horaFinalCombo);

        int horaFinal = horaFinalConvertida.getHour();

        if(horaInicial >= horaFinal){
            Mensajes.mensajeAdvertencia("", "La hora inicial debe ser menor que la hora final");
            return false;
        }
        return true;
    }
}
