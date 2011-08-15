/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Ventas.PantallaRegistrarPedido;
import java.util.GregorianCalendar;

/**
 *
 * @author Ivan
 */
public class GestorRegistrarPedido extends GestorPedido
{

    @Override
    public void iniciar() {
        interfaz=new PantallaRegistrarPedido(null, true,this);
        pedido=new Pedido();
        pedido.setFechaGeneracion(GregorianCalendar.getInstance().getTime());

    }

    @Override
    public void ejecutar(Pedido p) throws ExceptionGestor {
        PedidoBD.guardar(p);
    }


  


}
