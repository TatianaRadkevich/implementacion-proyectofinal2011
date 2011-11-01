/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Deposito;
import BaseDeDatos.Compras.EstadoDetalleOrdenCompraBD;
import BaseDeDatos.Compras.EstadoOrdenCompraBD;
import BaseDeDatos.Compras.OrdenCompraBD;
import BaseDeDatos.HibernateUtil;
import Negocio.Compras.DetalleOrdenCompra;
import Negocio.Compras.OrdenCompra;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Deposito.PantalleRecepcionDeMateriales;
import Presentacion.Mensajes;
import org.hibernate.Hibernate;

/**
 *
 * @author Rodrigo
 */

public class GestorRecepcionMaterial {

    protected PantalleRecepcionDeMateriales interfaz;
    protected OrdenCompra ordenCompra;
    
    public GestorRecepcionMaterial()
    {
        ordenCompra=null;
        interfaz=new PantalleRecepcionDeMateriales(this,null,true);
        interfaz.setTitle("Registrar Recepción de Materiales");
        interfaz.setVisible(true);
    }

    public  void iniciarCU(int nroOrden)
    {
        String mensaje="";
        ordenCompra=OrdenCompraBD.getOrdenCompra(nroOrden);
        
        if(ordenCompra==null)
        {
            mensaje="No existe ningunna orden de compra con el nro. "+nroOrden+
                    "\nPor favor pruebe con otro numero";
            Mensajes.mensajeErrorGenerico(mensaje);
            return;
        }
//        if(ordenCompra.getEstado().equals(EstadoOrdenCompraBD.getEstadoEnviada())!=true
//                ||ordenCompra.getEstado().equals(EstadoOrdenCompraBD.getEstadoConcretadaParcial())!=true)
//        {
//            mensaje="La orden de comprada con el nro. "+ordenCompra.getId()+" es invalida.\n"+
//                "Solo las ordenes enviadas o con recepcion parcial pueden ser recibidas";
//            Mensajes.mensajeErrorGenerico(mensaje);
//            return;
//        }
        /************************************************/

        interfaz.cargarOrden(ordenCompra);   
        interfaz.habilitarCarga(true);
    }

    public  void ejecutarCU(OrdenCompra oc) throws ExceptionGestor
    {
        //validar(oc);
        boolean ok=true;
        for(DetalleOrdenCompra doc:oc.getDetalle())
            if(doc.getEstado().equals(EstadoDetalleOrdenCompraBD.getEstadoConcretadaTotal())!=true)
                ok=false;

        if(ok)
            oc.setEstado(EstadoOrdenCompraBD.getEstadoConcretadaTotal());
        else
            oc.setEstado(EstadoOrdenCompraBD.getEstadoConcretadaParcial());
 
        OrdenCompraBD.modificar(oc);
    }

    public void finalizarCU() {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }
}