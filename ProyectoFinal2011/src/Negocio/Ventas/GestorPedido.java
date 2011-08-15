/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Produccion.TipoProductoBD;
import BaseDeDatos.Ventas.TipoPedidoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Ventas.PantallaRegistrarPedido;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorPedido {

    protected PantallaRegistrarPedido interfaz;
    protected Pedido pedido;

    public abstract void iniciar();
    public abstract void ejecutar(Pedido p) throws ExceptionGestor;

    public Pedido getPedido() {
        return pedido;
    }

    public List getTipoProductos() {
        return TipoProductoBD.listarTiposProductos();
    }

     public List getTipoPedidos() {
        return TipoPedidoBD.getTipoPedidos();
    }
     

}
