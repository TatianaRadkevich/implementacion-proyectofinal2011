/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.AsistenciaEmpleado;
import Presentacion.Utilidades;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class AsistenciaEmpleadoBD {

    public static List<AsistenciaEmpleado> getAsistencia(String nombre, String legajo, Date fechDesde, Date fechHasta) {
        String auxDesde = Utilidades.parseFecha(fechDesde);
        String auxHasta = Utilidades.parseFecha(fechHasta);
        String HQL =
                "FROM AsistenciaEmpleado as asist "
                + "WHERE LOWER(asist.TEmpleados.id) LIKE  LOWER('" + legajo + "%') "
                + "AND  LOWER(asist.TEmpleados.nombre) LIKE  LOWER('" + nombre + "%') "
                + ((fechDesde == null) ? "" : " AND asist.fecAsistencia >= '" + auxDesde + "' ")
                + ((fechHasta == null) ? "" : " AND asist.fecAsistencia <= '" + auxHasta + "' ");

        return HibernateUtil.ejecutarConsulta(HQL);
    }
}
