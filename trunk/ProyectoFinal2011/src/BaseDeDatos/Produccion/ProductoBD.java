/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.Producto;
import Negocio.Ventas.TipoPedido;

import java.util.List;
import java.util.logging.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Rodrigo
 */
public class ProductoBD{


   
    public ProductoBD() {

    }
    public static Producto guardar(Producto producto){
        Session usuario = null;
        try{
            usuario= HibernateUtil.getNewSession();
            usuario.beginTransaction();

            usuario.save(producto);
            usuario.getTransaction().commit();
            usuario.close();
        }catch(Exception  ex) {  }

        return producto;
    } 


    public static List<Producto> listarProductos()throws ExceptionInInitializerError{

         Session usuario = null;
        List<Producto> result=null;

        try{           
            usuario=HibernateUtil.getNewSession();
            usuario.beginTransaction();

            result = usuario.createQuery("from ProductoLista").list();
            usuario.getTransaction().commit();
                usuario.close();
        }catch(Exception  ex) {}
           

        return result;
    }

    public static List<Producto> listarProductos(TipoPedido tp){
        String hql="from Producto as pd where pd.TTproducto.idTproducto="+tp.getIdTipoPedido();
        return HibernateUtil.ejecutarConsulta(hql);
    }

    public static Producto traerProducto(int id){
        Session usuario = null;
        Producto producto=null;

        try{            
            usuario= HibernateUtil.getNewSession();
            producto =(Producto) usuario.get(Producto.class,id);
            usuario.close();
        }catch(Exception  ex) {}


        return producto;
    }

    public static Producto traerProducto(String codigo){
        String id_codigo=codigo.split("-")[1];
        int id=Integer.parseInt(id_codigo);
        return traerProducto(id);
    }

}
