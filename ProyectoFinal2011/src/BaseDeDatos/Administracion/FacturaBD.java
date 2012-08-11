/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.EstadoFactura;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class FacturaBD {

    public enum Estado {

        Anulada, GeneradaPendienteCobro, ParcialmenteCobrada, Cobrada;
    }
    private static final Session currentSession = HibernateUtil.getNewSession();

    public static EstadoFactura getEstadoFactura(Estado e) {
        String HQL = String.format("FROM EstadoFactura as ef WHERE LOWER(ef.nombre) = LOWER('%s')", e.name());
        List<EstadoFactura> lst = HibernateUtil.ejecutarConsulta(currentSession,HQL);
        if (lst.isEmpty()) {
            HibernateUtil.guardarObjeto(currentSession, new EstadoFactura(e.name()));
            return getEstadoFactura(e);
        }
        return lst.get(0);
    }

    public static int getUltimoNroFactura() {
        try {
            return (Integer) HibernateUtil.ejecutarConsulta("SELECT max(numero) FROM Factura").get(0);
        } catch (Exception e) {
            return 0;
        }
    }
}
