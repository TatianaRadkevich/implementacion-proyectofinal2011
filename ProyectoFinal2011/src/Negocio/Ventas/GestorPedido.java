/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Produccion.*;
import BaseDeDatos.Ventas.*;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;
import Presentacion.Ventas.PantallaPedidoABM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorPedido {

    protected PantallaPedidoABM interfaz;
    protected Pedido pedido;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(Pedido p) throws ExceptionGestor;

    public void finalizarCU()
    {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public List<TipoProducto> getTipoProductos() {
        return TipoProductoBD.listarTiposProductos();
    }

     public List<TipoPedido> getTipoPedidos() {
        return TipoPedidoBD.getTipoPedidos();
    }

     public List<Producto> getProductos(TipoProducto tp)
    {
         if(tp==null)
             return new ArrayList();
         return BaseDeDatos.Produccion.ProductoBD.listarProductos(tp);
     }

    public List getPrioridades()
    {
        ArrayList salida=new ArrayList();
        salida.add("Muy Alta");
        salida.add("Alta");
        salida.add("Media");
        salida.add("Baja");
        return salida;
    }
     

}
