package com.clases;

/**
 *
 * @author sebas
 */
public class PruebaListasNodos {
    
    public static void main(String[] args) {
        ListaDocentes l1;
        l1 = new ListaDocentes();
        String usuario = "sebasx200", pass = "1234";
        Docente docente = new Docente(usuario, pass);
        Docente nd = new Docente(docente);
        
        l1.insertarInicio(nd);

    }
    
}
