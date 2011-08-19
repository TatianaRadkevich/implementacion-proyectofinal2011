/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Ventas.PantallaRegistrarPedido;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Ivan
 */
public class GestorRegistrarPedido extends GestorPedido
{

    @Override
    public void iniciar() {
        interfaz=new PantallaRegistrarPedido(this);
        pedido=new Pedido();
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
    public void ejecutar(Pedido p) throws ExceptionGestor {
        validar(p);
        p.setEstadoPedido(PedidoBD.getEstoadoPedido(""));
        PedidoBD.guardar(p);
    }


  


}
