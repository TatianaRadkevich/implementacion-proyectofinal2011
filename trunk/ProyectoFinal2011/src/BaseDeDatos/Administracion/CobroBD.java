/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Cobro;

/**
 *
 * @author Ivan
 */
public class CobroBD {


    public static Cobro guardar(Cobro cobro){

        HibernateUtil.guardarObjeto(cobro);

        return cobro;
    }

    public static Cobro modificar(Cobro cobro){
       HibernateUtil.modificarObjeto(cobro);
        return cobro;
    }


//    public static List<Empleado> listarEmpleado()throws ExceptionInInitializerError{
//        List<Empleado> var=HibernateUtil.ejecutarConsulta("from Empleado");
//        return var;
//    }
//
//   public static List<Empleado> getEmpleados(
//            Cargo c,boolean vigentes,boolean cancelados){
//
//        if(vigentes==false&&cancelados==false)
//            return new ArrayList<Empleado>(0);
//
//        String HQL="SELECT e.TEmpleados FROM EmpleadosXCargo as e "
//                + "WHERE "+((c==null)?"1=1":"e.TCargos.idCargo = "+c.getIdCargo());
//
//        if(vigentes==true&&cancelados==true)
//            return HibernateUtil.ejecutarConsulta(HQL);
//
//        if(vigentes==true&&cancelados==false)
//            HQL+=" AND e.TEmpleados.fecBaja IS NULL ";
//
//        if(vigentes==false&&cancelados==true)
//            HQL+=" AND e.TEmpleados.fecBaja IS NOT NULL ";
//
//        return HibernateUtil.ejecutarConsulta(HQL);
//    }
//
//
//
//    public static Empleado traerEmpleado(int id){
//        return (Empleado) HibernateUtil.getObjeto(Empleado.class, id);
//    }
//
//    public static List<Empleado> getEmpleados(
//            String nombre, String apellido,String legajo,TipoDocumento tipo, String numeroDoc, boolean vigentes,boolean cancelados){
//
//        if(vigentes==false&&cancelados==false)
//            return new ArrayList<Empleado>(0);
//
//
//
//        String HQL=String.format(
//                "FROM Empleado as p "
//                + "WHERE LOWER(p.nombre) like  LOWER('%s%%') "
//                + "AND LOWER(p.apellido) like  LOWER('%s%%') "
//                + "AND p.idEmpleado like '%s%%' "
//               ,nombre,apellido,legajo);
//
//       if(tipo.getIdTdocumento()!=-1){
//            HQL+="AND p.TTdocumento.idTdocumento="+tipo.getIdTdocumento();
//       }
//       if(vigentes==true&&cancelados==true)
//            return HibernateUtil.ejecutarConsulta(HQL);
//
//        if(vigentes==true&&cancelados==false)
//            HQL+="AND p.fecBaja IS NULL ";
//
//        if(vigentes==false&&cancelados==true)
//            HQL+="AND p.fecBaja IS NOT NULL ";
//
//        return HibernateUtil.ejecutarConsulta(HQL);
//    }
//
//    public static List<Empleado> getEmpleadosVigentes(){
//        return HibernateUtil.ejecutarConsulta("FROM Empleado as p WHERE p.fecBaja IS NULL ");
//    }
}
