/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

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
        interfaz.habilitarTodo(false);
        interfaz.setVisible(true);
    }

    private void validar(Pedido p) throws ExceptionGestor
    {
        String mensage="";

        String auxEstado=p.getEstadoPedido().getNombre();
        if(!(auxEstado.equals(PedidoBD.EP_AutorizadoPendiente)||
                auxEstado.equals(PedidoBD.EP_NoAutorizado)||
                auxEstado.equals(PedidoBD.EP_Planificado)))
            mensage+="\nNo es posible cancelar un pedido con el estado de "+auxEstado;

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

    @Override
    public void ejecutarCU(Pedido p) throws ExceptionGestor {
        validar(p);
        
        PantallaEliminar pe=new PantallaEliminar();
        pe.setVisible(true);
        if(pe.isOk()==false)
            finalizarCU();

        p.setMotivoBaja(pe.getMotivo());
        p.setEstadoPedido(PedidoBD.getEstoadoPedido(PedidoBD.EP_Cancelado));
        p.setFecBaja(Utilidades.getFechaActual());
        PedidoBD.modificar(p);
    }



  


}
