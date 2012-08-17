/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.DetalleEtapaProduccion;
import Negocio.Produccion.DetalleProducto;
import Negocio.Produccion.EtapaProduccionEspecifica;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;
import java.util.ArrayList;

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

        for(EtapaProduccionEspecifica epe : producto.getEtapasProduccionEspecificas())
        {
            for(DetalleEtapaProduccion dep : epe.getDetalleEtapaProduccion())
            {
                if(dep.getTDetallesProducto() != null)
                    HibernateUtil.guardarObjeto(dep.getTDetallesProducto());
            }
        }

        HibernateUtil.guardarObjeto(producto);

        return producto;
    } 

    public static Producto modificar(Producto producto){


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
        
        return (Producto) HibernateUtil.getObjeto(Producto.class, id);
    }

    public static Producto traerProducto(String codigo){
        String id_codigo=codigo.split("-")[1];
        int id=Integer.parseInt(id_codigo);
        return (Producto) HibernateUtil.getObjeto(Producto.class, id);
    }



    public static List<Producto> getProducto(
            String nombre,TipoProducto tipo, boolean vigentes,boolean cancelados, String codTipo, String id ){

        if(vigentes==false&&cancelados==false)
            return new ArrayList<Producto>(0);



        String HQL=String.format(
                "FROM Producto as p "
                + "WHERE LOWER(p.nombre) like  LOWER('%s%%') "
                + "AND p.idProducto like '%s%%' "
                + "AND LOWER(p.TTproducto.codigo) like  LOWER('%s%%') "
               ,nombre,id,codTipo);

       if(tipo.getIdTproducto()!=-1){
            HQL+="AND p.TTproducto.idTproducto="+tipo.getIdTproducto();
       }

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&cancelados==false)
            HQL+="AND p.fecBaja IS NULL ";

        if(vigentes==false&&cancelados==true)
            HQL+="AND p.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }

}
