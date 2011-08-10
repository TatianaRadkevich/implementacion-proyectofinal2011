/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaABMProducto;
import java.awt.Dialog;
import javax.swing.JDialog;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author Ivan
 */
public class GestorRegistrarProducto extends GestorProducto{


    public void nuevoProducto(Dialog parent){
        PantallaABMProducto producto=new PantallaABMProducto(parent, true, this);
        producto.nuevo();
        producto.setVisible(true);
    }

    @Override
    public void ejectarOperacion(Producto producto) throws ExceptionGestor {
      try{
          Session sesion= HibernateUtil.getSessionFactory().getCurrentSession();
        sesion.beginTransaction();
        sesion.save(producto);
        sesion.getTransaction().commit();
     }catch(ExceptionInInitializerError ex){
          throw new ExceptionGestor("No se pudo inciar ");
       }catch(Exception e){
          throw new ExceptionGestor("");
      }
     
        
        
    }

}
