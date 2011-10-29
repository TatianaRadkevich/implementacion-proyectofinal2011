/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.OrdenTrabajo;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class OrdenTrabajoBD {

       public OrdenTrabajoBD() {

    }
    public static OrdenTrabajo guardar(OrdenTrabajo ordenTrabajo){

        HibernateUtil.guardarObjeto(ordenTrabajo);

        return ordenTrabajo;
    }

    public static OrdenTrabajo modificar(OrdenTrabajo ordenTrabajo){


        HibernateUtil.modificarObjeto(ordenTrabajo);
        return ordenTrabajo;
    }


    public static List<OrdenTrabajo> listarProductos()throws ExceptionInInitializerError{

        List<OrdenTrabajo> var=HibernateUtil.ejecutarConsulta("from OrdenTrabajo");
        return var;
    }

    public static OrdenTrabajo traerOrdenTrabajo(Long id){

        return (OrdenTrabajo) HibernateUtil.getObjeto(OrdenTrabajo.class, id);
    }
}
