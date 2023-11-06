package com.clases;

import com.clases.modelos.Usuario;

/** Esta es una clase manejadora de datos, la cual se usa para que los datos sean almanenados aquí y se pueda acceeder
 * a ellos desde otras ventanas.*/
public class DataSingleton {

    private static final DataSingleton instance = new DataSingleton();

    private Usuario usuario;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
