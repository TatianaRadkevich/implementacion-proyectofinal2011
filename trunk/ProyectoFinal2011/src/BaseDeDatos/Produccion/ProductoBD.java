/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo
 */
public class ProductoBD{


   
    public ProductoBD() {

    }
    public static Producto guardar(Producto producto){
//        Session usuario = null;
//        try{
//            usuario= HibernateUtil.getNewSession();
//            usuario.beginTransaction();
//
//            usuario.save(producto);
//            usuario.getTransaction().commit();
//            usuario.close();
//        }catch(Exception  ex) {  }
        HibernateUtil.guardarObjeto(producto);

        return producto;
    } 

    public static Producto modificar(Producto producto){
//        Session usuario = null;
//        try{
//            usuario= HibernateUtil.getNewSession();
//            usuario.beginTransaction();
//
//            usuario.update(producto);
//            usuario.getTransaction().commit();
//            usuario.close();
//        }catch(Exception  ex) {  }

        HibernateUtil.modificarObjeto(producto);
        return producto;
    }


    public static List<Producto> listarProductos()throws ExceptionInInitializerError{

        List<Producto> var=HibernateUtil.ejecutarConsulta("from Producto");
        return var;
    }

    public static List<Producto> listarProductos(TipoProducto tp){
        String hql="from Producto as pd where pd.TTproducto.idTproducto="+tp.getIdTproducto();
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
        return (Producto) HibernateUtil.getObjeto(Producto.class, id);
    }

//    public static List<Pedido> getProducto(
//            String RazonSocial,String CUIL,String NroPedido,
//            boolean vigentes,boolean cancelados){
//
//        if(vigentes==false&&cancelados==false)
//            return new ArrayList<Pedido>(0);
//
//
//
//        String HQL=String.format(
//                "FROM Pedido as p "
//                + "WHERE LOWER(p.TClientes.razonSocial) like  LOWER('%s%%') "
//                + "AND  p.TClientes.cuit  like '%s%%' "
//                + "AND p.idPedido like '%s%%' "
//                + ((desde==null)?"":"AND p.fecHoraGeneracion >= '%4$s' ")
//                + ((hasta==null)?"":"AND p.fecHoraGeneracion <= '%5$s' ")
//                ,RazonSocial,CUIL,NroPedido,auxDesde,auxHasta);
//
//        if(vigentes==true&&cancelados==true)
//            return HibernateUtil.ejecutarConsulta(HQL);
//
//        if(vigentes==true&&cancelados==false)
//            HQL+="AND p.fecBaja IS NULL ";
//
//        if(vigentes==false&&cancelados==true)
//            HQL+="AND p.fecBaja IS NOT NULL ";
//
//        return HibernateUtil.ejecutarConsulta(HQL);
//    }

}
