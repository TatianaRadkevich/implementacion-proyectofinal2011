/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.EstadoDetallePlan;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class EstadoDetallePlanBD {

      public EstadoDetallePlanBD() {

    }
    public static EstadoDetallePlan guardar(EstadoDetallePlan estadoDetallePlan){

        HibernateUtil.guardarObjeto(estadoDetallePlan);

        return estadoDetallePlan;
    }

    public static EstadoDetallePlan modificar(EstadoDetallePlan estadoDetallePlan){


        HibernateUtil.modificarObjeto(estadoDetallePlan);
        return estadoDetallePlan;
    }


    public static List<EstadoDetallePlan> listarProductos()throws ExceptionInInitializerError{

        List<EstadoDetallePlan> var=HibernateUtil.ejecutarConsulta("from EstadoDetallePlan");
        return var;
    }



    public static EstadoDetallePlan traerEstadoEnEjecucion(){
        List<EstadoDetallePlan> var=HibernateUtil.ejecutarConsulta("from EstadoDetallePlan where nombre like 'En ejecucion'");
        return var.get(0);
    }
}
