/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.TipoHerramienta;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class TipoHerramientaBD {

    public static List<TipoHerramienta> listarTipoHerramienta() {
        return HibernateUtil.ejecutarConsulta("FROM TipoHerramienta");
    }

    public static void guardar(TipoHerramienta tp) {
        HibernateUtil.guardarObjeto(tp);
    }

    public static void modificar(TipoHerramienta tm) {
        HibernateUtil.modificarObjeto(tm);
    }

    public static void eliminar(TipoHerramienta tm) {
        HibernateUtil.EliminarObjeto(tm);
    }
}



