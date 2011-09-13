/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Cargo;
import Negocio.Administracion.Empleado;
import java.util.ArrayList;
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

   public static List<Empleado> getEmpleados(
            Cargo c,boolean vigentes,boolean cancelados){

        if(vigentes==false&&cancelados==false)
            return new ArrayList<Empleado>(0);

        String HQL="SELECT e.TEmpleados FROM TEmpleadosXCargo as e "
                + "WHERE "+((c==null)?"1=1":"e.TCargos.idCargo = "+c.getIdCargo());

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&cancelados==false)
            HQL+=" AND e.TEmpleados.fecBaja IS NULL ";

        if(vigentes==false&&cancelados==true)
            HQL+=" AND e.TEmpleados.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }



    public static Empleado traerEmpleado(int id){
        return (Empleado) HibernateUtil.getObjeto(Empleado.class, id);
    }
    
}
