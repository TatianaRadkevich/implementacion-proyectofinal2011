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
class Pais {
    private String nombre;
    private String descripcion;
    private ArrayList<Provincia> provincias;

    public Pais() {
        provincias=new ArrayList<Provincia>();
    }

    public Pais(String nombre, String descripcion) {
        this();
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

    public ArrayList<Provincia> getProvincias() {
        return provincias;
    }

    public Provincia getProvincia(String nombre)
    {
        int index=provincias.indexOf(new Provincia(nombre, ""));
        return provincias.get(index);
    }

    public Provincia eliminarProvincia(String nombre)
    {
        int index=provincias.indexOf(new Provincia(nombre, ""));
        return provincias.remove(index);
    }

    public void addProvincia(Provincia Provincia) {
        this.provincias.add(Provincia);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pais)
        {
            Pais aux=(Pais) obj;
            if(nombre.compareToIgnoreCase(aux.getNombre())==0)
                return true;
        }

        return false;
    }


}
