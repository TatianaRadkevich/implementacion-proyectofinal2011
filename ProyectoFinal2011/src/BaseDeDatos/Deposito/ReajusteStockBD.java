/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Deposito;

import BaseDeDatos.HibernateUtil;
import Negocio.Deposito.ReajusteStock;

/**
 *
 * @author Sebastian
 */
public class ReajusteStockBD {


    public static void guardar(ReajusteStock reajuste){
    HibernateUtil.guardarObjeto(reajuste);



    }


}
