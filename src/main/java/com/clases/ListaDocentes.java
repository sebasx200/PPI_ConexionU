package com.clases;

/**
 *
 * @author sebas
 */

public class ListaDocentes {

    private Docente cab;

    public ListaDocentes() {
        cab = null;
    }

    //método para obtener la cabeza

    public Docente getCab() {
        return cab;
    }

    public void insertarInicio(Docente docenteNuevo) {
        Docente x = new Docente(docenteNuevo);
        x.setLiga(cab);
        cab = x;

    }

    public void insertarFinal(Docente docenteNuevo) {

        Docente p = cab, x = new Docente(docenteNuevo);
        if (cab == null) {
            cab = x;
        } else {
            while (p.getLiga() != null) {
                p = p.getLiga();
            }
            p.setLiga(x);
        }
    }
}
    /*
    // Metodo para eliminar el ultimo nodo de la lista
    public void eliminarUltimo(){
        Docente p = cab, ant=null;
        if(cab==null){
            JOptionPane.showMessageDialog(null, "Lista vacia");
        }else{
            while(p.getLiga() != null){
                ant = p;
                p = p.getLiga();
            }
            if(cab == p){
                cab=null;
            } else{
                ant.setLiga(null);
            }
            // delete(p) aquí se libera el nodo
        }
    }
    // método para eliminar un dato dado
    public void eliminarDato(int documento){

        Docente p = cab, ant=null;
        boolean sw = false;
        if(cab==null){
            JOptionPane.showMessageDialog(null, "Lista vacia");
        }else{
            while(p!=null && sw == false){
                if(p.getDocumento() == documento){
                    sw = true;
                } else{
                    ant = p;
                    p = p.getLiga();
                }
            }
            if(sw==false){
                JOptionPane.showMessageDialog(null, "El " + documento + " no está en la lista");
            } else{
                if(p==cab){
                    cab=cab.getLiga();
                }
                else{
                    ant.setLiga(p.getLiga());
                }
            }
        }


    }
    // método para mostrar
    public void mostrar(){
        String salida = "";
        Docente p = cab;
        if(cab==null){
            JOptionPane.showMessageDialog(null, "Lista vacía");
        } else
            while(p != null){
                salida = salida+p.getUsuario() + " ";
                p = p.getLiga();

            }
            JOptionPane.showMessageDialog(null, "Datos de la lista\n" + salida);
        }

     */

