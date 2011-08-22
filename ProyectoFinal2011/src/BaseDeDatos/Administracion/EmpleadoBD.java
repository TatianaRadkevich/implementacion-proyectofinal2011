/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Empleado;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class EmpleadoBD {


     public EmpleadoBD() {

    }
    public static Empleado guardar(Empleado empledo){

        HibernateUtil.guardarObjeto(empledo);

        return empledo;
    }

    public static Empleado modificar(Empleado empleado){
       HibernateUtil.modificarObjeto(empleado);
        return empleado;
    }


    public static List<Empleado> listarEmpleado()throws ExceptionInInitializerError{
        List<Empleado> var=HibernateUtil.ejecutarConsulta("from Empleado");
        return var;
    }

    



    public static Empleado traerEmpleado(int id){
        return (Empleado) HibernateUtil.getObjeto(Empleado.class, id);
    }
    
}
