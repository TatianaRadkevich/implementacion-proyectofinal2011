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

    private static final String EP_NoAutorizado = "No Autorizado";
    private static final String EP_AutorizadoPendiente = "Autorizado-Pendiente";
    private static final String EP_Planificado = "Planificado";
    private static final String EP_Cancelado = "Cancelado";
    private static final String EP_Produccion = "Produccion";
    private static final String EP_Pagado = "Pagado";
    private static final String EP_PendientePagado = "Pendiente de Pago";
    private static final String EP_PagoParcial = "Pago Parcial";
    private static final String EP_Retirado = "Retirado";

    public static EstadoPedido getEstadoProduccion() {
        return getEstoadoPedido(EP_Produccion);
    }

    public static EstadoPedido getEstadoAutorizadoPendiente() {
        return getEstoadoPedido(EP_AutorizadoPendiente);
    }

    public static EstadoPedido getEstadoCancelado() {
        return getEstoadoPedido(EP_Cancelado);
    }

    public static EstadoPedido getEstadoPagoParcialo() {
        return getEstoadoPedido(EP_PagoParcial);
    }
    public static EstadoPedido getEstadoPendientePagado() {
        return getEstoadoPedido(EP_PendientePagado);
    }

    public static EstadoPedido getEstadoRetirado() {
        return getEstoadoPedido(EP_Retirado);
    }

    public static EstadoPedido getEstadoNoAutorizado() {
        return getEstoadoPedido(EP_NoAutorizado);
    }

    public static EstadoPedido getEstadoPlanificado() {
        return getEstoadoPedido(EP_Planificado);
    }

    public static EstadoPedido getEstadoPagado() {
        return getEstoadoPedido(EP_Pagado);
    }

    private static EstadoPedido getEstoadoPedido(String nombre) {
        String HQL = String.format("FROM EstadoPedido as ep WHERE LOWER(ep.nombre) = LOWER('%s')", nombre);
        List<EstadoPedido> lst = HibernateUtil.ejecutarConsulta(HQL);
        if (lst.isEmpty()) {
            //throw new RuntimeException("No existe un \"estado de pedido\" con el nombre de : "+nombre);
            HibernateUtil.guardarObjeto(new EstadoPedido(nombre));
            return getEstoadoPedido(nombre);
        }

        return lst.get(0);
    }
}
