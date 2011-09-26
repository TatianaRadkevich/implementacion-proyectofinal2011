/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.PantallaEliminar;
import Presentacion.Utilidades;
import Presentacion.Ventas.PantallaPedidoABM;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Ivan
 */
public class GestorPedidoBaja extends GestorPedido
{

    public GestorPedidoBaja(Pedido p)
    {
        pedido=p;
    }

    @Override
    public void iniciarCU() {
        if(pedido==null)
            throw new RuntimeException("GestorPedidoEliminar: Se debe definir el pedido a eliminar");
        
        interfaz=new PantallaPedidoABM(this);
        interfaz.cargar(pedido);
        interfaz.habilitarBaja(true, true, Utilidades.getFechaActual(), "");
        interfaz.habilitarCarga(false);
        interfaz.setTitle("Eliminar Pedido");
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

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

    @Override
    public void ejecutarCU(Pedido p) throws ExceptionGestor {        
        validar(p);
        p.setFecBaja(Utilidades.getFechaActual());
        p.setEstadoPedido(EstadoPedidoBD.getEstadoCancelado());
        PedidoBD.modificar(p);
    }



  


}
