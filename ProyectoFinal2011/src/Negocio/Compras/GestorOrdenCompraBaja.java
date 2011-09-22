/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.EstadoDetalleOrdenCompraBD;
import BaseDeDatos.Compras.EstadoOrdenCompraBD;
import BaseDeDatos.Compras.OrdenCompraBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaOrdenCompraABM;

/**
 *
 * @author Rodrigo
 */
public class GestorOrdenCompraBaja extends GestorOrdenCompra{

    public GestorOrdenCompraBaja(OrdenCompra p)
    {
        ordenCompra=p;
        interfaz=new PantallaOrdenCompraABM(this);
    }

    @Override
    public void iniciarCU() {
        if(ordenCompra==null)
            throw new RuntimeException("GestorOrdenCompraEliminar: Se debe definir la \"orden de compra\" a eliminar");

        interfaz.cargar(ordenCompra);
        //interfaz.habilitarBaja(true, true, Utilidades.getFechaActual(), "");
        interfaz.habilitarCarga(false);
        interfaz.setTitle("Eliminar Orden de Compra");
        interfaz.setVisible(true);
    }

    private void validar(OrdenCompra p) throws ExceptionGestor
    {
        String mensage="";



        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

    @Override
    public void ejecutarCU(OrdenCompra oc) throws ExceptionGestor {
        validar(oc);

        oc.setEstado(EstadoOrdenCompraBD.getEstadoCancelada());
        for(DetalleOrdenCompra doc:oc.getDetalle())
            doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoCancelada());
        OrdenCompraBD.modificar(oc);
    }

}
