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
        //TODO: traer orden según estado permitido
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

    /** Método ejecturaCU que valida si es necesario un reclamo a proveedor o no 
     * @param oc la orden de compra que estamos trabajando
     * @return devuelve true si no es necesario un reclamo y false si lo es
     */
    public boolean ejecutarCU(OrdenCompra oc) throws ExceptionGestor
    {
        this.ordenCompra = oc;
        boolean recibidoIgualPedido = true;
        for(DetalleOrdenCompra doc : oc.getDetalle())
        {
            if(!doc.getCantidadPedida().equals(doc.getCantidadRecibida()))
            {
                // Si lo pedido es distinto a lo recibido seteo estado parcial y activo la "bandera"
                recibidoIgualPedido = false;
                doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoConcretadaParcial());
            }
            else
            {                
                doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoConcretadaTotal());
            }
        }
        
        if (recibidoIgualPedido)
        {
            oc.setEstado(EstadoOrdenCompraBD.getEstadoConcretadaTotal());
            return true;
        }
        else
        {
            oc.setEstado(EstadoOrdenCompraBD.getEstadoConcretadaParcial());
            return false;
        }
    }
    
    public void registrarRecepcionMateriales(OrdenCompra oc)
    { 
        OrdenCompraBD.modificar(oc);
        //TODO: ingresar en depósito los materiales recibidos
        Mensajes.mensajeInformacion("La recepcion de materiales de la Orden de Compra \"Nro. "+oc.getId()+"\" ha sido guardado exitosamente.");
    }

    public void finalizarCU() {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }
}