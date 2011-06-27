/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import Negocio.Produccion.Producto;

/**
 *
 * @author Rodrigo
 */
class DetallePedido {
    private int cantidad;
    private Producto producto;
    private float precio;

    public DetallePedido(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public DetallePedido() {
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    private void setPrecio(float precio) {
        this.precio = precio;
    }


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }



}
