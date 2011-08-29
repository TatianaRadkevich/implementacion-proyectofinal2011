/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.EstadoMaquina;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class EstadoMaquinaBD {

    private static final String EM_Disponible="Disponible";
    private static final String EM_NoDisponible="No Disponible";
    private static final String EM_Baja="Dada de Baja";


    public static EstadoMaquina getEstadoDisponible()
    {
        return getEstoadoMaquina(EM_Disponible);
    }

       public static EstadoMaquina getEstadoCancelado()
    {
        return getEstoadoMaquina(EM_NoDisponible);
    }

          public static EstadoMaquina getEstadoBaja()
    {
        return getEstoadoMaquina(EM_Baja);
    }



    private static EstadoMaquina getEstoadoMaquina(String nombre)
    {
        String HQL=String.format("FROM EstadoMaquina as e WHERE LOWER(e.nombre) = LOWER('%s')", nombre);
        List<EstadoMaquina> lst=HibernateUtil.ejecutarConsulta(HQL);
        if(lst.isEmpty())
            throw new RuntimeException("No existe un \"estado de maquina\" con el nombre de : "+nombre);
        return lst.get(0);
    }

}
