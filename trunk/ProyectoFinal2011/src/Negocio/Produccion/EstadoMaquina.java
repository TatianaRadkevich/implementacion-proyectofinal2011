/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

/**
 *
 * @author Heber Parrucci
 */
public class EstadoMaquina {

    private String Nombre;
    private String Descripcion;

    public EstadoMaquina(String Nombre, String Descripcion) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "EstadoMaquina{" + "Nombre=" + Nombre + "Descripcion=" + Descripcion + '}';
    }



}
