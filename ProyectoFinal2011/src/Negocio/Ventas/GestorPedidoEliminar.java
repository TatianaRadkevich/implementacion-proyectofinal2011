/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Utilidades;
import Presentacion.Ventas.PantallaRegistrarPedido;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Ivan
 */
public class GestorPedidoEliminar extends GestorPedido
{

    public GestorPedidoEliminar(Pedido p)
    {
        pedido=p;
    }

    @Override
    public void iniciar() {
        if(pedido==null)
            throw new RuntimeException("GestorPedidoEliminar: Se debe definir el pedido a eliminar");
        
        interfaz=new PantallaRegistrarPedido(this);
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
            mensage+="\nNo es posible cancelar un pedido con el estado "+auxEstado;

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

    @Override
    public void ejecutar(Pedido p) throws ExceptionGestor {
        validar(p);
        p.setEstadoPedido(PedidoBD.getEstoadoPedido(PedidoBD.EP_Cancelado));
        PedidoBD.modificar(p);
    }



  


}
