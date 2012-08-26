/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Compras.OrdenCompraBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaOrdenCompraABM;
import Presentacion.Mensajes;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class GestorOrdenCompraModificar extends GestorOrdenCompra{

    private ArrayList<DetalleOrdenCompra> estado=new ArrayList<DetalleOrdenCompra>();
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
        interfaz.habilitarPanelBaja(false);
        interfaz.setTitle("Modificar Orden de Compra");
        interfaz.setVisible(true);
        interfaz.setLocationRelativeTo(null);
        
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
        OrdenCompraBD.guardarModificacion(oc, estado);
//        for(DetalleOrdenCompra doc:oc.getDetalle())
//        {
//            doc.getMaterial().getMaterial().setEsPendiente(true);
//            MaterialBD.modificar(doc.getMaterial().getMaterial());
//        }
        Mensajes.mensajeInformacion("La Orden de Compra \"Nro. "+oc.getId()+"\" ha sido modificado exitosamente.");
    }

    public void eliminarDetalle(DetalleOrdenCompra seletedObject) {
        estado.add(seletedObject);
           }


}






