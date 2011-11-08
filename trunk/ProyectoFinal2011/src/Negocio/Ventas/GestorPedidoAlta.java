/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;


import BaseDeDatos.Ventas.*;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.Utilidades;
import Presentacion.Ventas.PantallaPedidoABM;

/**
 *
 * @author Ivan
 */
public class GestorPedidoAlta extends GestorPedido
{

    @Override
    public void iniciarCU() {
        interfaz=new PantallaPedidoABM(this);
        pedido=new Pedido();
        interfaz.setTitle("Registrar Pedido");
        interfaz.setVisible(true);
    }

    private void validar(Pedido p) throws ExceptionGestor
    {
        String mensage="";
        if(p.getCliente()==null)
            mensage+="\n no se asigno un cliente al pedido";
        if(p.getDetallePedido().isEmpty())
            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(Pedido p) throws ExceptionGestor {
        p.setEstadoPedido(EstadoPedidoBD.getEstadoAutorizadoPendiente());
        for(DetallePedido dp:p.getDetallePedido())
            dp.setEstadoDetallePedido(EstadoDetallePedidoBD.getEstadoPendiente());

        p.setFechaGeneracion(Utilidades.getFechaActual());
        validar(p);
        PedidoBD.guardar(p);
        Mensajes.mensajeInformacion("El pedido \"Nro. "+p.getIdPedido()+"\" ha sido guardado exitosamente.");
    }


  


}
