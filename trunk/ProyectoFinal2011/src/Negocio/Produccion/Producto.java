/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Produccion.TProductos;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class Producto extends TProductos{

   
    public Producto() {

    }
    public static TProductos guardar(TProductos producto){
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
