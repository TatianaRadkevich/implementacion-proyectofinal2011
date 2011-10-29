/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.DetallePlanProduccion;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class DetallePlanProduccionBD {

    public DetallePlanProduccionBD() {

    }
    public static DetallePlanProduccion guardar(DetallePlanProduccion detallePlanProduccion){

        HibernateUtil.guardarObjeto(detallePlanProduccion);

        return detallePlanProduccion;
    }

    public static DetallePlanProduccion modificar(DetallePlanProduccion detallePlanProduccion){


        HibernateUtil.modificarObjeto(detallePlanProduccion);
        return detallePlanProduccion;
    }


    public static List<DetallePlanProduccion> listarProductos()throws ExceptionInInitializerError{

        List<DetallePlanProduccion> var=HibernateUtil.ejecutarConsulta("from DetallePlanProduccion");
        return var;
    }

    
}
