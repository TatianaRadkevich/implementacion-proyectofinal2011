/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.EstadosEmpleado;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class EstadoEmpleadoBD {

      public EstadoEmpleadoBD() {

    }
    public static EstadosEmpleado guardar(EstadosEmpleado estado){

        HibernateUtil.guardarObjeto(estado);

        return estado;
    }

    public static EstadosEmpleado modificar(EstadosEmpleado empleado){
       HibernateUtil.modificarObjeto(empleado);
        return empleado;
    }


    public static List<EstadosEmpleado> listarEmpleado()throws ExceptionInInitializerError{
        List<EstadosEmpleado> var=HibernateUtil.ejecutarConsulta("from EstadosEmpleado");
        return var;
    }




    public static EstadosEmpleado traerEstadoEmpleado(int id){
        return (EstadosEmpleado) HibernateUtil.getObjeto(EstadosEmpleado.class, id);
    }
    public static EstadosEmpleado traerEstadoDisponible(){
        return (EstadosEmpleado) HibernateUtil.getObjeto(EstadosEmpleado.class, 1);
    }



}
