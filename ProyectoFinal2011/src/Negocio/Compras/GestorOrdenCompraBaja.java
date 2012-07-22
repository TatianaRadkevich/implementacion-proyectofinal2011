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
import Presentacion.Mensajes;
import Presentacion.Utilidades;

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
        interfaz.cargarFechaBaja();
        interfaz.habilitarCargaDetalle(false);
        interfaz.habilitarPanelBaja(true);
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
        oc.setFecCancelacion(Utilidades.getFechaActual());
//        oc.setEstado(EstadoOrdenCompraBD.getEstadoCancelada());
//        for(DetalleOrdenCompra doc:oc.getDetalle())
//        {
//            doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoCancelada());
//            doc.getMaterial().getMaterial().setEsPendiente(false);
//        }

//        OrdenCompraBD.modificar(oc);
        OrdenCompraBD.guardarBaja(oc);
        Mensajes.mensajeInformacion("La Orden de Compra \"Nro. "+oc.getId()+"\" ha sido cancelado exitosamente.");
    }

}
