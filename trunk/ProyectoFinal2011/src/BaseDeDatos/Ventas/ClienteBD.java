/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;
import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.Cliente;

/**
 *
 * @author Rodrigo
 */
public class ClienteBD {


    public static void guardar(Cliente c)
    {
        HibernateUtil.guardarObjeto(c);
    }

    public static void modificar(Cliente c)
    {
        HibernateUtil.guardarObjeto(c);
    }

}
