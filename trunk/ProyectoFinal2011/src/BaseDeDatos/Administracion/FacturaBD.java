/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.EstadoFactura;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class FacturaBD {

    public enum Estado
    {
        Anulada,GeneradaPendienteCobro,ParcialmenteCobrada,Cobrada;
    }

    public static EstadoFactura getEstadoFactura(Estado e)
    {
        String HQL = String.format("FROM EstadoFactura as ef WHERE LOWER(ef.nombre) = LOWER('%s')", e.name());
        List<EstadoFactura> lst = HibernateUtil.ejecutarConsulta(HQL);
        if (lst.isEmpty()) {           
            HibernateUtil.guardarObjeto(new EstadoFactura( e.name()));
            return getEstadoFactura(e);
        }
        return lst.get(0);
    }

}
