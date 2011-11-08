/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Compras;

import BaseDeDatos.Compras.EstadoOrdenCompraBD;
import BaseDeDatos.Compras.OrdenCompraBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaOrdenCompraEnvio;

/**
 *
 * @author Rodrigo
 */
public class GestorOrdenCompraEnviar extends GestorOrdenCompra {

    PantallaOrdenCompraEnvio interfaz;
    
    public GestorOrdenCompraEnviar(OrdenCompra oc) {
        ordenCompra = oc;
        interfaz = new PantallaOrdenCompraEnvio(this,null,true);        
    }

    @Override
    public void iniciarCU() {

        if (ordenCompra == null) {
            throw new RuntimeException("GestorOrdenCompraEnviar: Se debe definir la \"orden de compra\" a enviar");
        }

        interfaz.cargar(ordenCompra);
        //interfaz.habilitarBaja(true, true, Utilidades.getFechaActual(), "");
       
        interfaz.setTitle("Registrar Envío De Orden de Compra");
        interfaz.setVisible(true);

    }

    @Override
    public void finalizarCU() {
        interfaz.setVisible(false);
        interfaz.dispose();
    }



    private void validar(OrdenCompra oc) throws ExceptionGestor {
        String mensage = "";


        if (mensage.isEmpty() == false) {
            throw new ExceptionGestor("Problemas:" + mensage);
        }

    }

    @Override
    public void ejecutarCU(OrdenCompra oc) throws ExceptionGestor {
     
        validar(oc);
        oc.setEstado(EstadoOrdenCompraBD.getEstadoEnviada());      

        OrdenCompraBD.modificar(oc);
    }
    
}