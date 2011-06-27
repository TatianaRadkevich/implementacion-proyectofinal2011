/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

/**
 *
 * @author Heber Parrucci
 */
public class TipoMaquinaOHerramienta {
    private String nombre;
    private boolean esHerramienta;
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsHerramienta() {
        return esHerramienta;
    }

    public void setEsHerramienta(boolean esHerramienta) {
        this.esHerramienta = esHerramienta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoMaquinaOHerramienta(String nombre, boolean esHerramienta, String descripcion) {
        this.nombre = nombre;
        this.esHerramienta = esHerramienta;
        this.descripcion = descripcion;
    }

   
    public String toString() {
        return nombre;
    }


}
