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

}
