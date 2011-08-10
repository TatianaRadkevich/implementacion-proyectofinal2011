/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.TProductos;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author Rodrigo
 */
public class Producto extends TProductos{

    public Producto() {
    }

    public Producto(int idProducto, BaseDeDatos.Produccion.TTproducto TTproducto, String nombre, BigDecimal precioUnitario, String codigo) {
        super(idProducto, TTproducto, nombre, precioUnitario, codigo);
    }

    public Producto(int idProducto, BaseDeDatos.Produccion.TTproducto TTproducto, String descripcion, String nombre, BigDecimal precioUnitario, String codigo, Set TEtapasProduccionEspecificas, Set TDetallesPedidos, Set TDetallesProductos) {
        super(idProducto, TTproducto, descripcion, nombre, precioUnitario, codigo, TEtapasProduccionEspecificas, TDetallesPedidos, TDetallesProductos);
    }



   

   


}
