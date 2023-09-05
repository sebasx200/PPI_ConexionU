
package com.clases;

public class Docente {
    
    
    private String nombre;
    private String apellido;
    private int documento;
    private String usuario;
    private String password;
    private String correo;
    private boolean checkOficina;
    private String oficina;
    
    public Docente(String nombre, String apellido, int documento, String usuario, String password, String correo, boolean checkOficina, String oficina){
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.checkOficina = checkOficina;
        this.oficina = oficina;
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
    
    public boolean getCheckOficina(){
        return checkOficina;
    }
    
    public void setCheckOficina(boolean checkOficina){
        this.checkOficina = checkOficina;
    }
    
    public String getOficina(){
        return oficina;
    }
    
    public void setOficina(String oficina){
        this.oficina = oficina;
    }
}
