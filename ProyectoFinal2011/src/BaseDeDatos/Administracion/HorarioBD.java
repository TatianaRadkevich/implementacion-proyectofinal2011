/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Dia;
import Negocio.Administracion.Horarios;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class HorarioBD {

    public static List<Horarios> listarHorarios(){

        return HibernateUtil.ejecutarConsulta("from Horarios");


    }

    public static List<Dia> listarDias(){
        List<Dia> l=HibernateUtil.ejecutarConsulta("from Dia");
        if(l.isEmpty())
        {
            HibernateUtil.guardarObjeto(new Dia("Lunes"));
            HibernateUtil.guardarObjeto(new Dia("Martes"));
            HibernateUtil.guardarObjeto(new Dia("Miercoles"));
            HibernateUtil.guardarObjeto(new Dia("Jueves"));
            HibernateUtil.guardarObjeto(new Dia("Viernes"));
            HibernateUtil.guardarObjeto(new Dia("Sabado"));
            HibernateUtil.guardarObjeto(new Dia("Domingo"));
        }
        return HibernateUtil.ejecutarConsulta("from Dia");
    }


    public static void guardar(Horarios hora){

    HibernateUtil.guardarObjeto(hora);


    }

    public static List<Horarios> getHorarios(String codigo, String nombre) {
        String hql="from Horarios h WHERE h.idHorario LIKE '%s%%' AND h.nombre LIKE '%s%%'";
        //el String.format() reemplaza los '%s' por los argumentos, el codigo queda mas limpio!!.
        return HibernateUtil.ejecutarConsulta(String.format(hql, codigo,nombre));
    }

    public static void modificar(Horarios tmh) {
        HibernateUtil.modificarObjeto(tmh);
    }

    public static void elminiar(Horarios tmh) {
        HibernateUtil.EliminarObjeto(tmh);
    }

}
