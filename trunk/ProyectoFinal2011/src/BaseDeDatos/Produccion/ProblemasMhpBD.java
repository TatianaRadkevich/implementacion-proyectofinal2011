/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.ProblemasMhp;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
/**
 *
 * @author Heber Parrucci
 */
public class ProblemasMhpBD {

    public static List<ProblemasMhp> listarProblemasNoResueltosConFecha(Date fechaDesde, Date fechaHasta) {
        String auxDesde=Utilidades.parseFecha(fechaDesde);
        String auxHasta=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(fechaHasta, 1, 0, 0));
        String HQL = ("from ProblemasMhp where fecHoraRealSolucion is not null AND fecHoraProblema >= '"+auxDesde+"' and fecHoraProblema <= '"+auxHasta+"' order by fecHoraRealSolucion asc");


        return HibernateUtil.ejecutarConsulta(HQL);
    }

  public ProblemasMhpBD() {

    }
    public static ProblemasMhp guardar(ProblemasMhp problema){

        HibernateUtil.guardarObjeto(problema);

        return problema;
    }

    public static ProblemasMhp modificar(ProblemasMhp problema){


        HibernateUtil.modificarObjeto(problema);
        return problema;
    }


    public static List<ProblemasMhp> listarProblemas()throws ExceptionInInitializerError{

        List<ProblemasMhp> var=HibernateUtil.ejecutarConsulta("from ProblemasMhp");
        return var;
    }
   public static List<ProblemasMhp> listarProblemasResueltos()throws ExceptionInInitializerError{

        List<ProblemasMhp> var=HibernateUtil.ejecutarConsulta("from ProblemasMhp where fecHoraRealSolucion is not null order by fecHoraRealSolucion asc");
        return var;
    }

    public static List<ProblemasMhp> listarProblemasResueltos(int id)throws ExceptionInInitializerError{

        List<ProblemasMhp> var=HibernateUtil.ejecutarConsulta("from ProblemasMhp where fecHoraRealSolucion is not null and ID_MAQUINA_PARTICULAR="+id+" "+"order by fecHoraRealSolucion asc");
        return var;
    }

   public static List<ProblemasMhp> listarProblemasNoResueltos(int id)throws ExceptionInInitializerError{

        List<ProblemasMhp> var=HibernateUtil.ejecutarConsulta("from ProblemasMhp where fecHoraRealSolucion is null and ID_MAQUINA_PARTICULAR="+id+" "+"order by fecHoraProblema asc");
        return var;
    }



    public static List<ProblemasMhp> listarProblemasNoResueltos(){
        String hql=("from ProblemasMhp where fecHoraRealSolucion is null");
        return HibernateUtil.ejecutarConsulta(hql);
    }

    public static ProblemasMhp traerProblema(int id){

        return (ProblemasMhp) HibernateUtil.getObjeto(ProblemasMhp.class, id);
    }

}
