/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.EstadoDetalleOrdenCompra;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class EstadoDetalleOrdenCompraBD {

       private static final String Est_Pendiente = "Pendiente";
    private static final String Est_Cancelada = "Cancelada";
    private static final String Est_Concretada_Parcial = "Concretada Parcial";
    private static final String Est_Concretada_Total = "Concretada Total";

    public static EstadoDetalleOrdenCompra getEstadoPendiente() {
        return getEstadoDetalleOrden(Est_Pendiente);
    }

    public static EstadoDetalleOrdenCompra getEstadoCancelada() {
        return getEstadoDetalleOrden(Est_Cancelada);
    }

    public static EstadoDetalleOrdenCompra getEstadoConcretadaParcial() {
        return getEstadoDetalleOrden(Est_Concretada_Parcial);
    }

    public static EstadoDetalleOrdenCompra getEstadoConcretadaTotal() {
        return getEstadoDetalleOrden(Est_Concretada_Total);
    }

    private static EstadoDetalleOrdenCompra getEstadoDetalleOrden(String nombre) {
        String HQL = String.format("FROM EstadoDetalleOrdenCompra AS det WHERE LOWER(det.nombre) = LOWER('%s')", nombre);
        List<EstadoDetalleOrdenCompra> lst = HibernateUtil.ejecutarConsulta(HQL);
        if (lst.isEmpty()) {
            throw new RuntimeException("No existe un \"estado\" con el nombre de : " + nombre);
        }
        return lst.get(0);
    }

}
