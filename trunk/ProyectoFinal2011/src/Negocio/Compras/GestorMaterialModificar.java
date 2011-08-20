/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.MaterialBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaMaterialABM;

/**
 *
 * @author Rodrigo
 */
public class GestorMaterialModificar extends GestorMaterial{

    public GestorMaterialModificar(Material m) {
        material=m;
    }

    @Override
    public void iniciarCU() {
            if(material==null)
            throw new RuntimeException("GestorPedidoModificar: Se debe definir el pedido a modificar");

        interfaz=new PantallaMaterialABM(this);
        interfaz.cargar(material);
        interfaz.setVisible(true);
    }

    @Override
    public void ejecutarCU(Material p) throws ExceptionGestor {
        validar(p);
        MaterialBD.modificar(p);
    }

    @Override
    public void validar(Material p) throws ExceptionGestor {
                String mensage="";
//        String auxEstado=p.getEstadoPedido().getNombre();
//
//        if(!(auxEstado.equals(PedidoBD.EP_AutorizadoPendiente)||
//                auxEstado.equals(PedidoBD.EP_NoAutorizado)||
//                auxEstado.equals(PedidoBD.EP_Planificado)))
//            mensage+="\nNo es posible modificar un pedido con el estado de "+auxEstado;
//
//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//        if(p.getDetallePedido().isEmpty())
//            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

}
