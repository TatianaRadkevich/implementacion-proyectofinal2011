/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;

import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.EstadoPedido;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class EstadoPedidoBD {

    private static final String EP_NoAutorizado="No Autorizado";
    private static final String EP_AutorizadoPendiente="Autorizado-Pendiente";
    private static final String EP_Planificado="Planificado";
    private static final String EP_Cancelado="Cancelado";

    public static EstadoPedido getEstadoAutorizadoPendiente()
    {
        return getEstoadoPedido(EP_AutorizadoPendiente);
    }

       public static EstadoPedido getEstadoCancelado()
    {
        return getEstoadoPedido(EP_Cancelado);
    }

          public static EstadoPedido getEstadoNoAutorizado()
    {
        return getEstoadoPedido(EP_NoAutorizado);
    }

             public static EstadoPedido getEstadoPlanificado()
    {
        return getEstoadoPedido(EP_Planificado);
    }

    private static EstadoPedido getEstoadoPedido(String nombre)
    {
        String HQL=String.format("FROM EstadoPedido as ep WHERE LOWER(ep.nombre) = LOWER('%s')", nombre);
        List<EstadoPedido> lst=HibernateUtil.ejecutarConsulta(HQL);
        if(lst.isEmpty())
            throw new RuntimeException("No existe un \"estado de pedido\" con el nombre de : "+nombre);
        return lst.get(0);
    }

}
