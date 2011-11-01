/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.EstadoPlanProduccion;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class EstadoPlanBD {
   private static final String EP_Generado="Generado";
    private static final String EP_Terminado="Terminado";
    private static final String EP_Iniciado="Iniciado";
    private static final String EP_Cancelado="Cancelado";

    public static EstadoPlanProduccion getEstadoGenerado()
    {
        return getEstoadoPlan(EP_Generado);
    }

       public static EstadoPlanProduccion getEstadoTerminado()
    {
        return getEstoadoPlan(EP_Terminado);
    }

          public static EstadoPlanProduccion getEstadoIniciado()
    {
        return getEstoadoPlan(EP_Iniciado);
    }

             public static EstadoPlanProduccion getEstadoCancelado()
    {
        return getEstoadoPlan(EP_Cancelado);
    }

    private static EstadoPlanProduccion getEstoadoPlan(String nombre)
    {
        String HQL=String.format("FROM EstadoPlanProduccion as ep WHERE LOWER(ep.nombre) = LOWER('%s')", nombre);
        List<EstadoPlanProduccion> lst=HibernateUtil.ejecutarConsulta(HQL);
        if(lst.isEmpty())
            throw new RuntimeException("No existe un \"estado de plan\" con el nombre de : "+nombre);
        return lst.get(0);
    }
}
