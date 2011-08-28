/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;

import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.EstadoDetallePedido;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class EstadoDetallePedidoBD {

    private final static String EstPendiente="Pendiente";
    private final static String EstProduccion="Producción";
    private final static String EstTerminado="Terminado";


    public static EstadoDetallePedido getEstadoPendiente()
    {
        return getEstado(EstPendiente);
    }
    public static EstadoDetallePedido getEstadoProduccion()
    {
        return getEstado(EstProduccion);
    }
    public static EstadoDetallePedido getEstadoTerminado()
    {
        return getEstado(EstTerminado);
    }

    private static EstadoDetallePedido getEstado(String nombre)
    {
        String HQL=String.format("FROM EstadoDetallePedido as e WHERE LOWER(e.nombre) = LOWER('%s')", nombre);
        List<EstadoDetallePedido> lst=HibernateUtil.ejecutarConsulta(HQL);
        if(lst.isEmpty())
            throw new RuntimeException("No existe un \"estado de detalle pedido\" con el nombre de : "+nombre);
        return lst.get(0);
    }

  

}
