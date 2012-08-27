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
    public static EstadoDetallePlan traerEstadoFinalizado(){
        List<EstadoDetallePlan> var=HibernateUtil.ejecutarConsulta("from EstadoDetallePlan where nombre like 'Finalizado'");
        return var.get(0);
    }


    private static final String EP_Terminado="Terminado";
    private static final String EP_Pendiente="Pendiente";
    private static final String EP_Cancelado="Cancelado";
    private static final String EP_Replanificar="Replanificar";

    public static EstadoDetallePlan getEstadoTerminado()
    {
        return getEstadoDetallePlan(EP_Terminado);
    }

       public static EstadoDetallePlan getEstadoPendiente()
    {
        return getEstadoDetallePlan(EP_Pendiente);
    }

          public static EstadoDetallePlan getEstadoCancelado()
    {
        return getEstadoDetallePlan(EP_Cancelado);
    }

    public static EstadoDetallePlan getEstadoReplanificar()
    {
        return getEstadoDetallePlan(EP_Replanificar);
    }

    private static EstadoDetallePlan getEstadoDetallePlan(String nombre)
    {
        String HQL=String.format("FROM EstadoDetallePlan as ep WHERE LOWER(ep.nombre) = LOWER('%s')", nombre);
        List<EstadoDetallePlan> lst=HibernateUtil.ejecutarConsulta(HQL);
        if(lst.isEmpty())
            throw new RuntimeException("No existe un \"estado detalle plan\" con el nombre de : "+nombre);
        return lst.get(0);
    }

}
