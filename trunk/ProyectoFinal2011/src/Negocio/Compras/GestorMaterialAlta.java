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
public class GestorMaterialAlta extends GestorMaterial{


  

    @Override
    public void iniciarCU() {
        interfaz=new PantallaMaterialABM(this);
        material=new Material();
        interfaz.setVisible(true);
    }

    @Override
    public void ejecutarCU(Material m) throws ExceptionGestor {
         validar(m);
        MaterialBD.guardar(m);
    }

    @Override
    public void validar(Material m) throws ExceptionGestor {
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
