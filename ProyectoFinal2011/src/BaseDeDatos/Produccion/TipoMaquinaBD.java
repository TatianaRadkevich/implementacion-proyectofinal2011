/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.TipoMaquina;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class TipoMaquinaBD {



    public static List<TipoMaquina> listarTipoMaquina() {
        return HibernateUtil.ejecutarConsulta("FROM TipoMaquina");
    }

    public static void guardar(TipoMaquina tp) {
        HibernateUtil.guardarObjeto(tp);
    }

    public static void modificar(TipoMaquina tm) {
        HibernateUtil.modificarObjeto(tm);
    }

    public static void eliminar(TipoMaquina tm) {
        HibernateUtil.EliminarObjeto(tm);
    }

}
