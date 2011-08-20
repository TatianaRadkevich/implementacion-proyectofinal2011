/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.Proveedor;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class ProveedorBD {

    public static List<Proveedor> listarProveedores() {
        return HibernateUtil.ejecutarConsulta("from Proveedor");
    }

}
