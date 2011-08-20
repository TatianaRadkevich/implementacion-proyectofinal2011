/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.MaterialBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaMaterialABM;
import Presentacion.PantallaEliminar;
import Presentacion.Utilidades;

/**
 *
 * @author Rodrigo
 */
public class GestorMaterialBaja extends GestorMaterial{

    public GestorMaterialBaja(Material m) {
       material=m;
    }

    @Override
    public void iniciarCU() {
        if(material==null)
            throw new RuntimeException("GestorPedidoEliminar: Se debe definir el pedido a eliminar");

        interfaz=new PantallaMaterialABM(this);
        interfaz.cargar(material);
        interfaz.habilitarTodo(false);
        interfaz.setVisible(true);
    }

    @Override
    public void ejecutarCU(Material p) throws ExceptionGestor {
        validar(p);
        
        PantallaEliminar pe=new PantallaEliminar();
        pe.setVisible(true);
        if(pe.isOk()==false)
            finalizarCU();

        p.setMotivoBaja(pe.getMotivo());       
        p.setFechaBaja(Utilidades.getFechaActual());
        MaterialBD.modificar(p);
    }

    @Override
    public void validar(Material p) throws ExceptionGestor {
                String mensage="";
//
//
//        if(m.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//        if(m.getDetallePedido().isEmpty())
//            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

}
