/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.EstadoDetallePedidoBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
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
        interfaz.setTitle("Modificar Pedido");
        interfaz.setVisible(true);
    }

    private void validar(Pedido p) throws ExceptionGestor
    {
        String mensage="";
        EstadoPedido ep=p.getEstadoPedido();
        if(!(ep.equals(EstadoPedidoBD.getEstadoPlanificado())||
                ep.equals(EstadoPedidoBD.getEstadoAutorizadoPendiente())||
                ep.equals(EstadoPedidoBD.getEstadoNoAutorizado())))
            mensage+="\nNo es posible cancelar un pedido con el estado de "+ep.getNombre();


        if(p.getCliente()==null)
            mensage+="\n no se asigno un cliente al pedido";
        if(p.getDetallePedido().isEmpty())
            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(Pedido p) throws ExceptionGestor {
        for(DetallePedido dp:p.getDetallePedido())
            if(dp.getEstadoDetallePedido()==null)
            dp.setEstadoDetallePedido(EstadoDetallePedidoBD.getEstadoPendiente());

        validar(p);
        PedidoBD.modificar(p);
    }


  


}
