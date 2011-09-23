/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.UbicacionGeografica;

import BaseDeDatos.HibernateUtil;
import Negocio.UbicacionGeografica.Pais;
import Negocio.UbicacionGeografica.Provincia;
import Negocio.UbicacionGeografica.Localidad;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
/**
 *
 * @author Heber Parrucci
 */
public class LocalidadBD {

    public static Localidad guardar(Localidad loc){

        HibernateUtil.guardarObjeto(loc);

        return loc;
    }

    public static Localidad modificar(Localidad loc){


        HibernateUtil.modificarObjeto(loc);
        return loc;
    }

    public static void eliminar(Localidad loc){
        HibernateUtil.EliminarObjeto(loc);
    }


    public static List<Provincia> listarProvincias()throws ExceptionInInitializerError{

        List<Provincia> var=HibernateUtil.ejecutarConsulta("from Provincia");
        return var;
    }

    public static List<Localidad> listarLocalidades()throws ExceptionInInitializerError{

        List<Localidad> var=HibernateUtil.ejecutarConsulta("from Localidad");
        return var;
    }

    public static List<Provincia> listarProvincias(Pais p){
        String hql="from Provincia as pro where pro.TPaises.idPais="+p.getIdPais();
        return HibernateUtil.ejecutarConsulta(hql);
    }

    public static List<Localidad> listarLocalidades(Provincia p){
        String hql="from Localidad as loc where loc.TProvincias.idProvincia="+p.getIdProvincia();
        return HibernateUtil.ejecutarConsulta(hql);
    }

    public static Localidad traerLocalidad(int id){

        return (Localidad) HibernateUtil.getObjeto(Localidad.class, id);
    }

}
