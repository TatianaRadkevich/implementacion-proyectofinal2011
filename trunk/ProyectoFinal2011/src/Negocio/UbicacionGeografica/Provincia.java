/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.UbicacionGeografica;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
class Provincia {
    private String nombre;
    private String descripcion;
    private ArrayList<Localidad> localidades;

    public Provincia() {
    }

    public Provincia(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Localidad> getLocalidades() {
        return localidades;
    }

    public Localidad getLocalidad(String nombre)
    {
        int index=localidades.indexOf(new Localidad(nombre, ""));
        return localidades.get(index);
    }

    public Localidad eliminarLocalidad(String nombre)
    {
        int index=localidades.indexOf(new Localidad(nombre, ""));
        return localidades.remove(index);
    }

    public void addLocalidad(Localidad localidad) {
        this.localidades.add(localidad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Provincia)
        {
            Provincia aux=(Provincia) obj;
            if(nombre.compareToIgnoreCase(aux.getNombre())==0)
                return true;
        }

        return false;
    }


}
