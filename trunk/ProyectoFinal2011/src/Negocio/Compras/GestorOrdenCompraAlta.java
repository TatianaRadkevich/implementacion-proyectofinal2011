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
public class GestorOrdenCompraAlta extends GestorOrdenCompra{

    public GestorOrdenCompraAlta()
    {
        interfaz=new PantallaOrdenCompraABM(this);
        ordenCompra=new OrdenCompra();
    }

    @Override
    public void iniciarCU() {
        interfaz.setTitle("Generar Orden de Compra");
        interfaz.setNro(getCodigo()+1);
        interfaz.setVisible(true);
    }

     private void validar(OrdenCompra oc) throws ExceptionGestor
    {
        String mensage="";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(OrdenCompra oc) throws ExceptionGestor {
        oc.setEstado(EstadoOrdenCompraBD.getEstadoPendiente());
        for(DetalleOrdenCompra doc:oc.getDetalle())
        {
            doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoPendiente());
            doc.getMaterial().getMaterial().setEsPendiente(true);
        }
        
        oc.setFecGeneracion(Utilidades.getFechaActual());

        validar(oc);
        OrdenCompraBD.guardar(oc);
        Mensajes.mensajeInformacion("La Orden de Compra \"Nro. "+oc.getId()+"\" ha sido guardado exitosamente.");
    }


}
