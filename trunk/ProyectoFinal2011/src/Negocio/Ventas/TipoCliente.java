/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

/**
 *
 * @author Rodrigo
 */
class TipoCliente {
    private String nombre;
    private String descripcion;

    public TipoCliente() {
    }

    public TipoCliente(String nombre, String descripcion) {
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

}
