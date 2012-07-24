/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.DetalleOrdenCompra;
import Negocio.Compras.EstadoOrdenCompra;
import Negocio.Compras.OrdenCompra;
import Negocio.Compras.Proveedor;
import Negocio.Deposito.Faltante;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class OrdenCompraBD {

    public static void guardar(OrdenCompra oc)
    {
        HibernateUtil.guardarObjeto(oc);
    }

    public static void guardarAlta(OrdenCompra oc){
        Session sesion=HibernateUtil.getSession();
        sesion.beginTransaction();
        oc.setEstado(EstadoOrdenCompraBD.getEstadoPendiente());
        for(DetalleOrdenCompra doc:oc.getDetalle())
        {
            doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoPendiente());
            //doc.getMaterial().getMaterial().setEsPendiente(true);
            sesion.update(doc.getMaterial().getMaterial());
            for(Faltante falt: doc.getMaterial().getMaterial().faltantesPendientes())
           {
               falt.setDetalleOrdenCompra(doc);
               sesion.update(falt);
           }
        }
        sesion.save(oc);
        sesion.getTransaction().commit();
        sesion.flush();
    }
    public static void guardarBaja(OrdenCompra oc){
         Session sesion=HibernateUtil.getSession();
        sesion.beginTransaction();
       
        oc.setEstado(EstadoOrdenCompraBD.getEstadoCancelada());
        for(DetalleOrdenCompra doc:oc.getDetalle())
        {
            doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoCancelada());
            //doc.getMaterial().getMaterial().setEsPendiente(false);
            sesion.update(doc.getMaterial().getMaterial());

            for(Faltante falt: doc.getFaltantes())
           {
               falt.setDetalleOrdenCompra(null);
               sesion.update(falt);
           }
        }
        sesion.update(oc);
        sesion.getTransaction().commit();
        sesion.flush();
    }

    public static void modificar(OrdenCompra oc) {
        HibernateUtil.modificarObjeto(oc);
    }
    
    public static OrdenCompra getOrdenCompra(int id){        
            return (OrdenCompra) HibernateUtil.getObjeto(OrdenCompra.class, id);
    }

    public static int getUltimoNro(){
              try{
        return (Integer) HibernateUtil.ejecutarConsulta("SELECT   max(idOrdenCompra) FROM OrdenCompra").get(0);
        }catch(Exception e)
        {return 0;}
    }

    
    public static List<OrdenCompra> getOrdenes(
            String nro,EstadoOrdenCompra estado,Proveedor proveedor,
            Date desde,Date hasta,boolean vigentes,boolean cancelados){

        if(vigentes==false&&cancelados==false)
            return new ArrayList<OrdenCompra>(0);


        String auxDesde=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(desde, -1, 0, 0));
        String auxHasta=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(hasta, 1, 0, 0));
        String HQL=String.format(
                "FROM OrdenCompra as oc "
                + "WHERE oc.idOrdenCompra LIKE '%s%%' "
                + ((proveedor==null)?"":" AND oc.TProveedores.idProveedor = "+proveedor.getId())
                 + ((estado==null)?"":" AND oc.TEordenCompra.idEordenCompra = "+estado.getId())
                + ((desde==null)?"":" AND oc.fecGeneracion >= '%2$s' ")
                + ((hasta==null)?"":" AND oc.fecGeneracion <= '%3$s' ")
                ,nro,auxDesde,auxHasta);

        if(vigentes==true && cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);
        else
        {
            if(vigentes ==true)
                HQL+="AND oc.fecCancelacion IS NULL ";
            else
                HQL+="AND oc.fecCancelacion IS NOT NULL ";
        }
        
//        if(vigentes==true&&cancelados==false)
//            
//
//        if(vigentes==false&&cancelados==true)
//            H
             
        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static void eliminarDetalle(DetalleOrdenCompra seletedObject) {
         Session sesion=HibernateUtil.getSession();
        sesion.beginTransaction();
        //seletedObject.getMaterial().getMaterial().setEsPendiente(true);
        sesion.update(seletedObject.getMaterial().getMaterial());
        sesion.getTransaction().commit();
        sesion.flush();
    }
    
    
    public static void guardarModificacion(OrdenCompra oc, ArrayList<DetalleOrdenCompra> detalle){
        Session sesion=HibernateUtil.getSession();
        sesion.beginTransaction();

        for(DetalleOrdenCompra det:detalle){
           // det.getMaterial().getMaterial().setEsPendiente(false);
            sesion.update(det.getMaterial().getMaterial());
            for(Faltante falt: det.getFaltantes())
           {
               falt.setDetalleOrdenCompra(null);
               sesion.update(falt);
           }
        }

        sesion.update(oc);
       for(DetalleOrdenCompra doc:oc.getDetalle())
        {
            //doc.getMaterial().getMaterial().setEsPendiente(true);
            sesion.update(doc.getMaterial().getMaterial());

            for(Faltante falt: doc.getMaterial().getMaterial().faltantesPendientes())
           {
               falt.setDetalleOrdenCompra(doc);
               sesion.update(falt);
           }
        }
        sesion.getTransaction().commit();
        sesion.flush();
        
        
        
    }


}
