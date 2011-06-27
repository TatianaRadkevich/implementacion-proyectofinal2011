/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

/**
 *
 * @author Rodrigo
 */
class Tipo {

    private String nombre;
    private String descripcion;

    public Tipo() {
    }

    public Tipo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tipo)
        {
            Tipo aux=(Tipo) obj;
            if(nombre.compareToIgnoreCase(aux.getNombre())==0)
                return true;
        }

        return false;
    }

}
