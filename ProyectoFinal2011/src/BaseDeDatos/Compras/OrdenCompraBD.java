/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.EstadoOrdenCompra;
import Negocio.Compras.OrdenCompra;
import Negocio.Compras.Proveedor;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class OrdenCompraBD {

    public static void guardar(OrdenCompra oc)
    {
        HibernateUtil.guardarObjeto(oc);
    }

    public static void modificar(OrdenCompra oc) {
        HibernateUtil.modificarObjeto(oc);
    }
    
    public static OrdenCompra getOrdenCompra(int id){        
            return (OrdenCompra) HibernateUtil.getObjeto(OrdenCompra.class, id);
    }

    public static int getUltimoNro(){
      return OrdenCompraBD.getUltimoNro();
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

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);
        
//        if(vigentes==true&&cancelados==false)
//            HQL+="AND oc.fecBaja IS NULL ";
//        
//        if(vigentes==false&&cancelados==true)
//            HQL+="AND oc.fecBaja IS NOT NULL ";
             
        return HibernateUtil.ejecutarConsulta(HQL);
    }


}
