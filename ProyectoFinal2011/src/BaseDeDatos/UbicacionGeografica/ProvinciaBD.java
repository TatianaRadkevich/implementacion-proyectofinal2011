/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.UbicacionGeografica;

import BaseDeDatos.HibernateUtil;
import Negocio.UbicacionGeografica.Pais;
import Negocio.UbicacionGeografica.Provincia;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Heber Parrucci
 */
public class ProvinciaBD {

    public static Provincia guardar(Provincia pro){

        HibernateUtil.guardarObjeto(pro);

        return pro;
    }

    public static Provincia modificar(Provincia pro){


        HibernateUtil.modificarObjeto(pro);
        return pro;
    }

    public static void eliminar(Provincia pro){
        HibernateUtil.EliminarObjeto(pro);
    }


    public static List<Provincia> listarProvincias()throws ExceptionInInitializerError{

        List<Provincia> var=HibernateUtil.ejecutarConsulta("from Provincia");
        return var;
    }

    public static List<Provincia> listarProvincias(Pais p){
        String hql="from Provincia as pro where pro.TPaises.idPais="+p.getIdPais();
        return HibernateUtil.ejecutarConsulta(hql);
    }

    public static Provincia traerProvincia(int id){

        return (Provincia) HibernateUtil.getObjeto(Provincia.class, id);
    }

}
