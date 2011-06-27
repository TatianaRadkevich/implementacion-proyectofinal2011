/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.UbicacionGeografica;


/**
 *
 * @author Rodrigo
 */
class Barrio {
    private String nombre;
    private String descripcion;

    public Barrio() {
    }

    public Barrio(String nombre, String descripcion) {
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
        if (obj instanceof Barrio)
        {
            Barrio aux=(Barrio) obj;
            if(nombre.compareToIgnoreCase(aux.getNombre())==0)
                return true;
        }

        return false;
    }
}
