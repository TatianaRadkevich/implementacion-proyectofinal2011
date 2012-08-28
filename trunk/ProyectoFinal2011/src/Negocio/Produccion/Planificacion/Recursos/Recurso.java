/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion.Recursos;

/**
 *
 * @author Dicsys
 */
public class Recurso<E, N extends Number> {

    private E recurso;
    private N cantidad;

    public Recurso(E recurso, N cantidad) {
        this.recurso = recurso;
        this.cantidad = cantidad;
    }

    public N getCantidad() {
        return cantidad;
    }

    public void setCantidad(N cantidad) {
        this.cantidad = cantidad;
    }

    public E getRecurso() {
        return recurso;
    }

    public void setRecurso(E recurso) {
        this.recurso = recurso;
    }
}
