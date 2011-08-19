/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;

import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.TipoPedido;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class TipoPedidoBD {

    public static List<TipoPedido> getTipoPedidos()
    {
        return HibernateUtil.ejecutarConsulta("from TipoPedido");
    }
}
