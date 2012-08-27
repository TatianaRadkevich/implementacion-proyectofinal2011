/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.AsistenciaEmpleado;
import Negocio.Administracion.Empleado;
import Presentacion.Utilidades;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 *
 * @author Rodrigo
 */
public class AsistenciaEmpleadoBD {

    public static List<AsistenciaEmpleado> getAsistencia(String legajo, Date fechDesde, Date fechHasta) {
        String auxDesde = Utilidades.parseFecha(fechDesde);
        String auxHasta = Utilidades.parseFecha(fechHasta);
        String HQL =
                "FROM AsistenciaEmpleado as asist "
                + "WHERE LOWER(asist.TEmpleados.id) LIKE  LOWER('" + legajo + "%') "
                //+ "AND  LOWER(asist.TEmpleados.nombre) LIKE  LOWER('" + nombre + "%') "
                + ((fechDesde == null) ? "" : " AND asist.fecAsistencia >= '" + auxDesde + "' ")
                + ((fechHasta == null) ? "" : " AND asist.fecAsistencia <= '" + auxHasta + "' ");

        return HibernateUtil.ejecutarConsulta(HQL);
    }
    
    public static AsistenciaEmpleado guardar(AsistenciaEmpleado asist){
        HibernateUtil.guardarObjeto(asist);
        return asist;
    }

    public static Boolean estaPresente(Empleado empleado) {
        String HQL =
                  " FROM AsistenciaEmpleado as asist " +
                  " WHERE asist.TEmpleados.id = " + empleado.getId() +
                  "   AND asist.horaEgreso is null ";

        List<AsistenciaEmpleado> asistencias = HibernateUtil.ejecutarConsulta(HQL);
        if(asistencias.size() == 0) return false;
        return true;
    }
}
