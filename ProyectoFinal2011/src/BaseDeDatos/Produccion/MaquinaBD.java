/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.EstadoMaquina;
import Negocio.Produccion.MaquinaParticular;
import Negocio.Produccion.TipoMaquina;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yo
 */
public class MaquinaBD {

    public static List<EstadoMaquina> listarEstadoMaquina() {
        return HibernateUtil.ejecutarConsulta("FROM EstadoMaquina");
    }

    public static int getUltimoID() {
        try {
            return (Integer) HibernateUtil.ejecutarConsulta("SELECT max(idMaquinaParticular) FROM MaquinaParticular").get(0);
        } catch (Exception e) {
            return 0;
        }
    }

    public static List<TipoMaquina> listarTipoMaquina() {
        return HibernateUtil.ejecutarConsulta("FROM TipoMaquina");
    }

    public static List<MaquinaParticular> getMaquinas(String codigo, String modelo, String nombre, boolean vigentes, boolean eliminados) {
        String HQL = String.format(
                "FROM MaquinaParticular as mh "
                + "WHERE LOWER(mh.nombre) LIKE LOWER('%s%%') "
                + "AND LOWER(mh.modelo) LIKE LOWER('%s%%') "
                + "AND LOWER(mh.codigo) LIKE LOWER('%s%%')",
                nombre, modelo, codigo);

        if (vigentes == false && eliminados == false) {
            return new ArrayList<MaquinaParticular>(0);
        }

        if (vigentes == true && eliminados == true) {
            return HibernateUtil.ejecutarConsulta(HQL);
        }

        if (vigentes == true && eliminados == false) {
            HQL += "AND mh.fecBaja IS NULL ";
        }

        if (vigentes == false && eliminados == true) {
            HQL += "AND mh.fecBaja IS NOT NULL ";
        }

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static List<MaquinaParticular> getMaquinas(TipoMaquina tm, boolean vigentes, boolean eliminados) {
        String HQL = "FROM MaquinaParticular as mp "
                + "WHERE " + ((tm == null) ? "1=1" : "mp.TTmaquina.idTmaquina = " + tm.getIdTmaquina());

        if (vigentes == false && eliminados == false) {
            return new ArrayList<MaquinaParticular>(0);
        }

        if (vigentes == true && eliminados == true) {
            return HibernateUtil.ejecutarConsulta(HQL);
        }

        if (vigentes == true && eliminados == false) {
            HQL += " AND mp.fecBaja IS NULL ";
        }

        if (vigentes == false && eliminados == true) {
            HQL += " AND mp.fecBaja IS NOT NULL ";
        }

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static List<MaquinaParticular> listarMaquinasVigentes() {

        return getMaquinas("", "", "", true, false);
    }

    public static void guardar(MaquinaParticular mh) {
        HibernateUtil.guardarObjeto(mh);
    }

    public static void modificar(MaquinaParticular mh) {
        HibernateUtil.modificarObjeto(mh);
    }
}
