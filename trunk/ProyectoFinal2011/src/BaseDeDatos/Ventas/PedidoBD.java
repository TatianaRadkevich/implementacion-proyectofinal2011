/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;

import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.Cliente;
import Negocio.Ventas.EstadoPedido;
import Negocio.Ventas.Pedido;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.rmi.CORBA.Util;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class PedidoBD
{

    public static void guardar(Pedido p)
    {
        HibernateUtil.guardarObjeto(p);
    }

    public static void modificar(Pedido p)
    {
        HibernateUtil.guardarObjeto(p);
    }

     public static Pedido getPedido(int id)
    {
         return (Pedido) HibernateUtil.getObjeto(Pedido.class, id);
     }

    public static List<Pedido> getPedidos(String RazonSocial,String CUIL,String NroPedido,Date desde,Date hasta)
    {
        String auxDesde=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(desde, -1, 0, 0));
        String auxHasta=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(hasta, 1, 0, 0));

        String HQL=String.format(
                "FROM Pedido as p "
                + "WHERE LOWER(p.TClientes.razonSocial) like  LOWER('%s%%') "
                + "AND  p.TClientes.cuil  like '%s%%' "
                + "AND p.idPedido like '%s%%' "
                + ((desde==null)?"":"AND p.fecHoraGeneracion >= '%4$s' ")
                + ((hasta==null)?"":"AND p.fecHoraGeneracion <= '%5$s' ")
                ,RazonSocial,CUIL,NroPedido,auxDesde,auxHasta);       
        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static final String EP_NoAutorizado="No Autorizado";
    public static final String EP_AutorizadoPendiente="Autorizado-Pendiente";
    public static final String EP_Planificado="Planificado";
    public static final String EP_Cancelado="Cancelado";

    public static EstadoPedido getEstoadoPedido(String nombre)
    {
        String HQL=String.format("FROM EstadoPedido as ep WHERE LOWER(ep.nombre) = LOWER('%s')", nombre);
        List<EstadoPedido> lst=HibernateUtil.ejecutarConsulta(HQL);
        if(lst.isEmpty())
            throw new RuntimeException("No existe un \"estado de pedido\" con el nombre de : "+nombre);
        return lst.get(0);
    }
  
}
