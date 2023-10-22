package com.clases;

import javafx.beans.property.SimpleStringProperty;

/** Se crea esta clase especial con los atributos que se van a mostrar en la tabla tanto de docentes, mentores y
 * estudiantes, se debe hacer esta clase especial porque al momento de mostrar los valores en las columnas de la tabla
 * fue necesario hacerlos con SimpleStringProperty, de otra manera daba errores*/

public class UsuarioTabla {

    // atributos que se van a mostrar en la tabla
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty documento;
    private SimpleStringProperty correo;
    private SimpleStringProperty telefono;

    // método constructor que pide todos los atributos, no es necesario constructor por defecto
    public UsuarioTabla(String nombre, String apellido, String documento, String correo, String telefono) {
        this.nombre = new SimpleStringProperty (nombre);
        this.apellido = new SimpleStringProperty (apellido);
        this.documento = new SimpleStringProperty (documento);
        this.correo = new SimpleStringProperty (correo);
        this.telefono = new SimpleStringProperty (telefono);
    }

    // Getters y Setters

    public String getNombre(){
        return nombre.get();
    }
    public void setNombre(String nombre){
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getApellido(){
        return apellido.get();
    }
    public void setApellido(String apellido){
        this.apellido = new SimpleStringProperty(apellido);
    }

    public String getDocumento(){
        return documento.get();
    }
    public void setDocumento(String documento){
        this.documento = new SimpleStringProperty(documento);
    }

    public String getCorreo(){
        return correo.get();
    }
    public void setCorreo(String correo){
        this.correo = new SimpleStringProperty(correo);
    }

    public String getTelefono(){
        return telefono.get();
    }
    public void setTelefono(String telefono){
        this.telefono = new SimpleStringProperty(telefono);
    }

    // Estos métodos Property son necesarios para que las columnas del TableView puedan mostrar los atributos del objeto
    public SimpleStringProperty nombreProperty() {
        return nombre;
    }
    public SimpleStringProperty apellidoProperty() {
        return apellido;
    }
    public SimpleStringProperty documentoProperty() {
        return documento;
    }
    public SimpleStringProperty correoProperty() {
        return correo;
    }
    public SimpleStringProperty telefonoProperty() {
        return telefono;
    }

}
