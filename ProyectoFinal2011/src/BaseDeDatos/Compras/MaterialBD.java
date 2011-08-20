/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.Material;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class MaterialBD {

    public static List<Material> getMateriales(String codigo, String nombre) {
        String HQL=String.format("FROM Material as m WHERE LOWER(m.nombre) LIKE  LOWER('%s%%') "
                + "AND LOWER(m.codigo) LIKE LOWER('%s%%')",
                codigo,nombre);
        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static void guardar(Material m) {
       HibernateUtil.guardarObjeto(m);
    }

     public static void modificar(Material m) {
       HibernateUtil.modificarObjeto(m);
    }
}
