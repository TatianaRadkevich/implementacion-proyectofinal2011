/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;


import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Produccion.ProductoBD;
import Negocio.Produccion.GestorProducto;
import Negocio.Produccion.TipoProducto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Rodrigo
 */
public class TipoProductoBD{

    public static List<TipoProducto> listarTiposProductos() throws ExceptionInInitializerError{
         Session usuario = null;
        List<TipoProducto> result=null;
        
        try{
            AnnotationConfiguration conf=new AnnotationConfiguration().configure();
            SessionFactory factory= conf.buildSessionFactory();
            usuario= factory.openSession();
            usuario.beginTransaction();       
           
            result = usuario.createQuery("from TipoProducto").list();
            usuario.getTransaction().commit();
                usuario.close();
        }catch(Exception  ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);}
        
        return result;
    }

    public TipoProductoBD() {
        super();
    }

   


 
   
}
