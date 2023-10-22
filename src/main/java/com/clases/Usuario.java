package com.clases;

public class Usuario {
    private String nombre;
    private String apellido;
    private String documento;
    private String usuario;
    private String correo;
    private String telefono;
    private String password;
    private String perfil;
    private String departamento;
    private String ciudad;
    private String universidad;
    private Usuario liga;

    /** Este metodo constructor es para inicializar los atributos */
    public Usuario(){
        nombre = "";
        apellido = "";
        documento = "";
        usuario = "";
        correo = "";
        telefono = "";
        password = "";
        perfil = "";
        departamento = "";
        ciudad = "";
        universidad = "";
    }

    public Usuario(Usuario usuario){
        this.nombre = usuario.nombre;
        this.apellido = usuario.apellido;
        this.documento = usuario.documento;
        this.usuario = usuario.usuario;
        this.correo = usuario.correo;
        this.telefono = usuario.telefono;
        this.password = usuario.password;
        this.perfil = usuario.perfil;
        this.departamento = usuario.departamento;
        this.ciudad = usuario.ciudad;
        this.universidad = usuario.universidad;
    }

    /** Este es un metodo constructor para solo llamar los atributos de iniciar sesion */
    public Usuario(String nombre, String usuario, String pass){
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = pass;
    }

    /** Este es un metdo constructor para llamar todos los atributos */
    public Usuario(String nombre, String apellido, String documento, String usuario, String correo, String telefono,
                   String password, String perfil, String departamento, String ciudad, String universidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.usuario = usuario;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.perfil = perfil;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.universidad = universidad;
    }

    /** Este médoto constructor se usa para crear un tipo de usario que se mostrará en el tableview del menú principal*/

    public Usuario(String nombre, String apellido, String documento, String correo, String telefono) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.correo = correo;
        this.telefono = telefono;
    }

    /** Estos son los metodos setter y getter de cada atributo */
    
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
    
    public String getDocumento(){
        return documento;
    }

    public void setDocumento(String documento){
        this.documento = documento;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPerfil() {return perfil;}

    public void setPerfil(String perfil) {this.perfil = perfil;}

    public String getDepartamento() {return departamento;}

    public void setDepartamento(String departamento) {this.departamento = departamento;}

    public String getCiudad() {return ciudad;}

    public void setCiudad(String ciudad) {this.ciudad = ciudad;}

    public String getUniversidad() {return universidad;}

    public void setUniversidad(String universidad) {this.universidad = universidad;}

    public Usuario getLiga() {
        return liga;
    }

    public void setLiga(Usuario liga) {
        this.liga = liga;
    }
    
    @Override
    public String toString() {
        return nombre + apellido + documento + usuario + telefono + correo + password;
    }
}
