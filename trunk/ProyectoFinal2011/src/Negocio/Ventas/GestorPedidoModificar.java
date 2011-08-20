/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Utilidades;
import Presentacion.Ventas.PantallaPedidoABM;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Ivan
 */
public class GestorPedidoModificar extends GestorPedido
{


    public GestorPedidoModificar(Pedido p)
    {
        pedido=p;
    }

    @Override
    public void iniciarCU() {
         if(pedido==null)
            throw new RuntimeException("GestorPedidoModificar: Se debe definir el pedido a modificar");

        interfaz=new PantallaPedidoABM(this);
        interfaz.cargar(pedido);
        interfaz.setVisible(true);
    }

    private void validar(Pedido p) throws ExceptionGestor
    {
        String mensage="";
        String auxEstado=p.getEstadoPedido().getNombre();

        if(!(auxEstado.equals(PedidoBD.EP_AutorizadoPendiente)||
                auxEstado.equals(PedidoBD.EP_NoAutorizado)||
                auxEstado.equals(PedidoBD.EP_Planificado)))
            mensage+="\nNo es posible modificar un pedido con el estado de "+auxEstado;

        if(p.getCliente()==null)
            mensage+="\n no se asigno un cliente al pedido";
        if(p.getDetallePedido().isEmpty())
            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(Pedido p) throws ExceptionGestor {
        validar(p);
        PedidoBD.modificar(p);
    }


  


}
