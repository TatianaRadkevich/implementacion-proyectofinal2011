/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;

import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.Cliente;
import Negocio.Ventas.Pedido;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class PedidoBD
{
    public static void guardar(Pedido p)
    {
        HibernateUtil.guardarObjeto(p);
    }
  
}
