package com.clases;

public class ListaNodos {
    private Nodo punta;
    private Nodo fin;

    public ListaNodos(){
        punta = null;
    }
    public Nodo getPunta(){
        return punta;
    }

    public boolean listaVacia(){
        if(punta == null){
            return true;
        } else{
            return false;
        }
    }
    public void insertarAsesoria(Asesoria asesoria){
        Nodo nuevo;
        if(listaVacia()){
            nuevo = new Nodo(null, asesoria);
            punta = nuevo;
            fin = nuevo;
        } else{
            nuevo = new Nodo(null, asesoria);
            fin.setLiga(nuevo);
            fin = nuevo;
        }
    }
    public void mostrarLista(){
        if(listaVacia()){
            Mensajes.mensajeAdvertencia("", "La lista está vacía");
        } else {
            Nodo p;
            p = punta;
            while(p != null) {
                Mensajes.mensajeInformativo("Elementos de la lista de asesorías: ", p.getAsesoria().toString());
                p = p.getLiga();
            }
        }
    }
}

