/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.TEmpleadosXCargo;

/**
 *
 * @author Ivan
 */
public class TEmpleadosXCargoBD {



    public static TEmpleadosXCargo guardar(TEmpleadosXCargo empXcargo){

        HibernateUtil.guardarObjeto(empXcargo);

        return empXcargo;
    }

    public static TEmpleadosXCargo modificar(TEmpleadosXCargo empXcargo){


        HibernateUtil.modificarObjeto(empXcargo);
        return empXcargo;
    }




  
}
