/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.EstadoOrdenCompra;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class EstadoOrdenCompraBD {

    private static final String Est_Pendiente = "Pendiente";
    private static final String Est_Cancelada = "Cancelada";
    private static final String Est_Concretada_Parcial = "Concretada Parcial";
    private static final String Est_Concretada_Total = "Concretada Total";
    private static final String Est_Enviada = "Enviada";

    public static EstadoOrdenCompra getEstadoPendiente() {
        return getEstoadoPedido(Est_Pendiente);
    }

    public static EstadoOrdenCompra getEstadoCancelada() {
        return getEstoadoPedido(Est_Cancelada);
    }

    public static EstadoOrdenCompra getEstadoConcretadaParcial() {
        return getEstoadoPedido(Est_Concretada_Parcial);
    }

    public static EstadoOrdenCompra getEstadoConcretadaTotal() {
        return getEstoadoPedido(Est_Concretada_Total);
    }

    public static EstadoOrdenCompra getEstadoEnviada() {
        return getEstoadoPedido(Est_Enviada);
    }

    private static EstadoOrdenCompra getEstoadoPedido(String nombre) {
        String HQL = String.format("FROM EstadoOrdenCompra as oc WHERE LOWER(oc.nombre) = LOWER('%s')", nombre);
        List<EstadoOrdenCompra> lst = HibernateUtil.ejecutarConsulta(HQL);
        if (lst.isEmpty()) {
            throw new RuntimeException("No existe un \"estado\" con el nombre de : " + nombre);
        }
        return lst.get(0);
    }
}
