/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.EstadoOrdenTrabajo;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class EstadoOrdenTrabajoBD {

          public EstadoOrdenTrabajoBD() {

    }
    public static EstadoOrdenTrabajo guardar(EstadoOrdenTrabajo estadoOrdenTrabajo){

        HibernateUtil.guardarObjeto(estadoOrdenTrabajo);

        return estadoOrdenTrabajo;
    }

    public static EstadoOrdenTrabajo modificar(EstadoOrdenTrabajo estadoOrdenTrabajo){


        HibernateUtil.modificarObjeto(estadoOrdenTrabajo);
        return estadoOrdenTrabajo;
    }


    public static List<EstadoOrdenTrabajo> listarProductos()throws ExceptionInInitializerError{

        List<EstadoOrdenTrabajo> var=HibernateUtil.ejecutarConsulta("from EstadoOrdenTrabajo");
        return var;
    }

}
