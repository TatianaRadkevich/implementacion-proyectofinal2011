/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;

import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.TipoCliente;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class TipoClienteBD {
 public static List<TipoCliente> listarTiposClientes()
    {
        return HibernateUtil.ejecutarConsulta("from TipoCliente");
    }
}
