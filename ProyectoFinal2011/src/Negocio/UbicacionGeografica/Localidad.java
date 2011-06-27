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
class Localidad {
    private String nombre;
    private String descripcion;    
    private ArrayList<Barrio> barrios;

    public Localidad() {
    }

    public Localidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public ArrayList<Barrio> getBarrios() {
        return barrios;
    }

    public Barrio getBarrio(String nombre)
    {
        int index=barrios.indexOf(new Barrio(nombre, ""));
        return barrios.get(index);
    }

    public Barrio eliminarBarrio(String nombre)
    {
        int index=barrios.indexOf(new Barrio(nombre, ""));
        return barrios.remove(index);
    }

    public void addBarrio(Barrio barrio) {
        this.barrios.add(barrio);
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
        if (obj instanceof Localidad)
        {
            Localidad aux=(Localidad) obj;
            if(nombre.compareToIgnoreCase(aux.getNombre())==0)
                return true;
        }

        return false;
    }

}
