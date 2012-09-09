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
    
    static
    {
        List<Dia> l=HibernateUtil.ejecutarConsulta("from Dia");
        if(l.isEmpty())
        {
            HibernateUtil.guardarObjeto(new Dia(DiaSemana.Lunes.name()));
            HibernateUtil.guardarObjeto(new Dia(DiaSemana.Martes.name()));
            HibernateUtil.guardarObjeto(new Dia(DiaSemana.Miercoles.name()));
            HibernateUtil.guardarObjeto(new Dia(DiaSemana.Jueves.name()));
            HibernateUtil.guardarObjeto(new Dia(DiaSemana.Viernes.name()));
            HibernateUtil.guardarObjeto(new Dia(DiaSemana.Sabado.name()));
            HibernateUtil.guardarObjeto(new Dia(DiaSemana.Domingo.name()));
        }
    }

    public static List<Horarios> listarHorarios(){
        return HibernateUtil.ejecutarConsulta("from Horarios");
    }

    public static enum DiaSemana
    {
        Lunes,Martes,Miercoles,Jueves,Viernes,Sabado,Domingo
    }

    public static List<Dia> listarDias(){
        return HibernateUtil.ejecutarConsulta("from Dia");
    }

    public static Dia getDia(DiaSemana dia){   
        return (Dia) HibernateUtil.ejecutarConsulta("from Dia d WHERE d.nombre LIKE '"+dia.name()+"'").get(0);
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
