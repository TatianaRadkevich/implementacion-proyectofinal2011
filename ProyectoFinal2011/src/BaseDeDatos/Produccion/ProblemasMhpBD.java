/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.ProblemasMhp;
import java.util.ArrayList;

import java.util.List;
/**
 *
 * @author Heber Parrucci
 */
public class ProblemasMhpBD {

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
   public static List<ProblemasMhp> listarProblemasResueltos(boolean herramienta)throws ExceptionInInitializerError{

        List<ProblemasMhp> var=HibernateUtil.ejecutarConsulta("from ProblemasMhp where fecHoraRealSolucion is not null AND TMaquinasHerramientaParticular.TTmaquinaHerramienta.esHerramienta="+herramienta);
        return var;
    }

    public static List<ProblemasMhp> listarProblemasResueltos(int id)throws ExceptionInInitializerError{

        List<ProblemasMhp> var=HibernateUtil.ejecutarConsulta("from ProblemasMhp where fecHoraRealSolucion is not null and ID_MAQUINA_HERRAMIENTA_PARTICULAR="+id);
        return var;
    }

   public static List<ProblemasMhp> listarProblemasNoResueltos(int id)throws ExceptionInInitializerError{

        List<ProblemasMhp> var=HibernateUtil.ejecutarConsulta("from ProblemasMhp where fecHoraRealSolucion is null and ID_MAQUINA_HERRAMIENTA_PARTICULAR="+id);
        return var;
    }

    public static List<ProblemasMhp> listarProblemasNoResueltos(boolean herramienta){
        String hql=("from ProblemasMhp where fecHoraRealSolucion is null AND TMaquinasHerramientaParticular.TTmaquinaHerramienta.esHerramienta="+herramienta);
        return HibernateUtil.ejecutarConsulta(hql);
    }

    public static ProblemasMhp traerProblema(int id){

        return (ProblemasMhp) HibernateUtil.getObjeto(ProblemasMhp.class, id);
    }

}
