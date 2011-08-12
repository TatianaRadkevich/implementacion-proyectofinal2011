/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.TTproducto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class TipoProducto extends TTproducto{

    static List<TTproducto> listarTiposProductos() throws ExceptionInInitializerError{
         Session usuario = null;
        List<TTproducto> result=null;
        List results=null;
        try{
//            Configuration conf=new Configuration().configure();
//            SessionFactory factory= conf.buildSessionFactory();
//            usuario= factory.openSession();
//            usuario=HibernateUtil.getSessionFactory().openSession();
            usuario=HibernateUtil.getsession();
            usuario.beginTransaction();
            result = usuario.createQuery("from TTproducto").list();
            usuario.getTransaction().commit();
                usuario.close();
        }catch(Exception  ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);}
        
        return results;
    }

    public TipoProducto() {
        super();
    }

    @Override
    public String toString() {
        return super.getNombre();
    }


 
   
}
