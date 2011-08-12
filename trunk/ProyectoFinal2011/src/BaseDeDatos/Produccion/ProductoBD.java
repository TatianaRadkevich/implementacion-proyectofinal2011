/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class ProductoBD{


   
    public ProductoBD() {

    }
    public static ProductoBD guardar(ProductoBD producto){
        try{
            Session sesion= HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            sesion.save(producto);
            sesion.getTransaction().commit();
        }catch(ExceptionInInitializerError ex){
//            throw new ExceptionGestor("No se pudo inciar ");
        }catch(Exception e){
//            throw new ExceptionGestor("");
      }
         return producto;
    }


   

   


}
