/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.UbicacionGeografica;

import BaseDeDatos.HibernateUtil;
import Negocio.UbicacionGeografica.Barrio;
import Negocio.UbicacionGeografica.Localidad;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class BarrioBD {
     public static Barrio guardar(Barrio barrio){

        HibernateUtil.guardarObjeto(barrio);

        return barrio;
    }

    public static Barrio modificar(Barrio barrio){


        HibernateUtil.modificarObjeto(barrio);
        return barrio;
    }

    public static void eliminar(Barrio barrio){
        HibernateUtil.EliminarObjeto(barrio);
    }


    public static List<Barrio> listarBarrios()throws ExceptionInInitializerError{

        List<Barrio> var=HibernateUtil.ejecutarConsulta("from Barrio order by nombre");
        return var;
    }


    public static List<Barrio> listarBarrios(Localidad local){
        String hql="from Barrio as l where l.TLocalidades.idLocalidad="+local.getIdLocalidad()+" order by nombre";
        return HibernateUtil.ejecutarConsulta(hql);
    }

    public static Barrio traerProvincia(int id){

        return (Barrio) HibernateUtil.getObjeto(Barrio.class, id);
    }


}
