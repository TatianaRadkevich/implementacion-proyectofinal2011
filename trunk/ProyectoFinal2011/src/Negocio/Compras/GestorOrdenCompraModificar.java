/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.OrdenCompraBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaOrdenCompraABM;

/**
 *
 * @author Rodrigo
 */
public class GestorOrdenCompraModificar extends GestorOrdenCompra{

    public GestorOrdenCompraModificar(OrdenCompra oc)
    {
        ordenCompra=oc;
        interfaz=new PantallaOrdenCompraABM(this);
    }

    @Override
    public void iniciarCU() {
        if(ordenCompra==null)
            throw new RuntimeException("GestorOrdenCompraModificar: Se debe definir la orden a modificar");

        interfaz.cargar(ordenCompra);
        interfaz.setTitle("Modificar Orden de Compra");
        interfaz.setVisible(true);
        interfaz.habilitarPanelBaja(false);
    }

       private void validar(OrdenCompra oc) throws ExceptionGestor
    {
        String mensage="";


        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(OrdenCompra oc) throws ExceptionGestor {

        validar(oc);
        OrdenCompraBD.modificar(oc);
    }

}






