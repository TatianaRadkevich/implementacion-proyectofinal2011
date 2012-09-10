/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion.Recursos;

/**
 *
 * @author Rodrigo
 */
public class Detalle<R, N extends Number> {

    private R recurso;
    private N cantidad;

    public Detalle(R recurso, N cantidad) {
        this.recurso = recurso;
        this.cantidad = cantidad;
    }

    public N getCantidad() {
        return cantidad;
    }

    public void setCantidad(N cantidad) {
        this.cantidad = cantidad;
    }

    public R getRecurso() {
        return recurso;
    }

    public void setRecurso(R recurso) {
        this.recurso = recurso;
    }
}
