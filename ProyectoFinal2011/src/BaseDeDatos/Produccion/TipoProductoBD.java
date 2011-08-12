/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;


import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Produccion.ProductoBD;
import Negocio.Produccion.GestorProducto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class TipoProductoBD{

    static List<ProductoBD> listarTiposProductos() throws ExceptionInInitializerError{
         Session usuario = null;
        List<ProductoBD> result=null;
        List results=null;
        try{
//            Configuration conf=new Configuration().configure();
//            SessionFactory factory= conf.buildSessionFactory();
//            usuario= factory.openSession();
//            usuario=HibernateUtil.getSessionFactory().openSession();
            usuario=HibernateUtil.getNewSession();
            usuario.beginTransaction();
            result = usuario.createQuery("from Producto").list();
            usuario.getTransaction().commit();
                usuario.close();
        }catch(Exception  ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);}
        
        return results;
    }

    public TipoProductoBD() {
        super();
    }

   


 
   
}
