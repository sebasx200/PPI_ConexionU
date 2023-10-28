package com.clases;

/**
 *
 * @author sebas
 */

public class ListaDocentes {

    private Usuario cab;

    public ListaDocentes() {
        cab = null;
    }

    //método para obtener la cabeza

    public Usuario getCab() {
        return cab;
    }

    public void insertarInicio(Usuario usuarioNuevo) {
        Usuario x = new Usuario(usuarioNuevo);
        x.setLiga(cab);
        cab = x;

    }

    public void insertarFinal(Usuario usuarioNuevo) {

        Usuario p = cab, x = new Usuario(usuarioNuevo);
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
        Usuario p = cab, ant=null;
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

        Usuario p = cab, ant=null;
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
        Usuario p = cab;
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

