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
import org.hibernate.Hibernate;

/**
 *
 * @author Rodrigo
 */

public abstract class GestorRecepcionMaterial {

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
        ordenCompra=OrdenCompraBD.getOrdenCompra(nroOrden);
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