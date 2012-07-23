/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Deposito;

import BaseDeDatos.HibernateUtil;
import Negocio.Deposito.ReajusteStock;
import org.hibernate.Session;

/**
 *
 * @author Sebastian
 */
public class ReajusteStockBD {


    public static void guardar(ReajusteStock reajuste){
        Session sesion=HibernateUtil.getSession();
        sesion.beginTransaction();


        sesion.save(reajuste);
        reajuste.getMaterial().setStockActual(reajuste.getCantidad());
        sesion.update(reajuste.getMaterial());

        sesion.getTransaction().commit();
        sesion.flush();

   



    }


}
