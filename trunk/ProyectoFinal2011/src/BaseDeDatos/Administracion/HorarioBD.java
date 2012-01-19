/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
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

    public static List<Horarios> listarDias(){

    return HibernateUtil.ejecutarConsulta("from Dias");

    }


    public static void guardarHoraios(Horarios hora){

    HibernateUtil.guardarObjeto(hora);


    }

    public static List<Horarios> getHorarios(String codigo, String nombre) {
        String hql="from Horarios h WHERE h.idHorario LIKE '%s%%' AND h.nombre LIKE '%s%%'";
        //el String.format() reemplaza los '%s' por los argumentos, el codigo queda mas limpio!!.
        return HibernateUtil.ejecutarConsulta(String.format(hql, codigo,nombre));
    }

}
