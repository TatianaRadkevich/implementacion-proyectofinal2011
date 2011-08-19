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

        String HQL=String.format(
                "FROM Pedido as p "
                + "WHERE LOWER(p.TClientes.razonSocial) like  LOWER('%s%%')  "
                + "AND  p.TClientes.cuil  like '%s%%' "
                + "AND p.idPedido like '%s%%' "
                + ((desde==null || hasta==null)?"":"AND p.fecHoraGeneracion BETWEEN '%s' AND '%s'")
                ,RazonSocial,CUIL,NroPedido,Utilidades.parseDate(desde),Utilidades.parseDate(hasta));
        return HibernateUtil.ejecutarConsulta(HQL);
    }
  
}
