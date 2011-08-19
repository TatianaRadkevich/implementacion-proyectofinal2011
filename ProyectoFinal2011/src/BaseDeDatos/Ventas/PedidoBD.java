/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;

import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.Cliente;
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

    public static List<Pedido> getPedidos(String RazonSocial,String CUIL,String NroPedido,Date desde,Date hasta)
    {
        String auxDesde=Utilidades.parseDate(Utilidades.agregarFecha(desde, -1, 0, 0));
        String auxHasta=Utilidades.parseDate(Utilidades.agregarFecha(hasta, 1, 0, 0));

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
  
}
