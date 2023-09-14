package com.clases;

public class Estudiante {
    
    
    private String nombre;
    private String apellido;
    private int documento;
    private String usuario;
    private String password;
    private String correo;
    
    public Estudiante(){
        
        this.nombre = "";
        this.apellido = "";
        this.documento = 0;
        this.usuario = "";
        this.password = "";
        this.correo = "";      
    }
    
    public Estudiante(String nombre, String apellido, int documento, String usuario, String password, String correo, boolean checkOficina, String oficina){
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public int getDocumento(){
        return documento;
    }
    
    public void setDocumento(int documento){
        this.documento = documento;
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    @Override
    public String toString() {
        return nombre + " " + apellido + " " +  usuario + " " + password + " " + correo;
    }
}
