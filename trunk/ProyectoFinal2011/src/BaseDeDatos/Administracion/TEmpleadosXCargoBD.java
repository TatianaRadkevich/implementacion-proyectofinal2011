/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.EmpleadosXCargo;

/**
 *
 * @author Ivan
 */
public class TEmpleadosXCargoBD {



    public static EmpleadosXCargo guardar(EmpleadosXCargo empXcargo){

        HibernateUtil.guardarObjeto(empXcargo);

        return empXcargo;
    }

    public static EmpleadosXCargo modificar(EmpleadosXCargo empXcargo){


        HibernateUtil.modificarObjeto(empXcargo);
        return empXcargo;
    }




  
}
