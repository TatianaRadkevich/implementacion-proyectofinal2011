/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Cargo;
import Negocio.Administracion.Empleado;
import Negocio.Administracion.TipoDocumento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class EmpleadoBD {

    public static List<Empleado> getEmpleadosSHorario() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public EmpleadoBD() {
    }

    public static Empleado guardar(Empleado empledo) {

        HibernateUtil.guardarObjeto(empledo);

        return empledo;
    }

    public static Empleado modificar(Empleado empleado) {
        HibernateUtil.modificarObjeto(empleado);
        return empleado;
    }

    public static List<Empleado> listarEmpleado() throws ExceptionInInitializerError {
        List<Empleado> var = HibernateUtil.ejecutarConsulta("from Empleado");
        return var;
    }

    public static List<Empleado> getEmpleados(Cargo c, boolean vigentes, boolean cancelados) {

        if (vigentes == false && cancelados == false) {
            return new ArrayList<Empleado>(0);
        }

        String HQL = "SELECT e.TEmpleados FROM EmpleadosXCargo as e "
                + "WHERE " + ((c == null) ? "1=1" : "e.TCargos.idCargo = " + c.getIdCargo());

        if (vigentes == true && cancelados == true) {
            return HibernateUtil.ejecutarConsulta(HQL);
        }

        if (vigentes == true && cancelados == false) {
            HQL += " AND e.TEmpleados.fecBaja IS NULL ";
        }

        if (vigentes == false && cancelados == true) {
            HQL += " AND e.TEmpleados.fecBaja IS NOT NULL ";
        }

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static List<Empleado> getEmpleados(String legajo, String nombre, String apellido,
            boolean vigentes, boolean cancelados) {
        
        if (vigentes == false && cancelados == false) {
            return new ArrayList<Empleado>();
        }

        String HQL = "FROM Empleado e WHERE ";
        HQL += " e.idEmpleado LIKE '" + legajo + "%'";
        HQL += " AND e.nombre LIKE '" + nombre + "%'";
        HQL += " AND e.apellido LIKE '" + apellido + "%'";
        HQL += " AND e.apellido LIKE '" + apellido + "%'";

        if (vigentes == true && cancelados == false) {
            HQL += " AND e.TEmpleados.fecBaja IS NULL ";
        }

        if (vigentes == false && cancelados == true) {
            HQL += " AND e.TEmpleados.fecBaja IS NOT NULL ";
        }

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static Empleado traerEmpleado(int id) {
        return (Empleado) HibernateUtil.getObjeto(Empleado.class, id);
    }

    public static boolean existeEmpleado(short tipoDoc, int numeroDoc) {

        String HQL = String.format("FROM Empleado as p WHERE p.TTdocumento.idTdocumento=" + tipoDoc + "AND p.numeroDocumento=" + numeroDoc);
        List temp = HibernateUtil.ejecutarConsulta(HQL);
        if (temp.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public static List<Empleado> getEmpleados(
            String nombre, String apellido, String legajo, TipoDocumento tipo, String numeroDoc, boolean vigentes, boolean cancelados) {

        if (vigentes == false && cancelados == false) {
            return new ArrayList<Empleado>(0);
        }



        String HQL = String.format(
                "FROM Empleado as p "
                + "WHERE LOWER(p.nombre) like  LOWER('%s%%') "
                + "AND LOWER(p.apellido) like  LOWER('%s%%') "
                + "AND p.idEmpleado like '%s%%' ", nombre, apellido, legajo);

        if (tipo.getIdTdocumento() != -1) {
            HQL += "AND p.TTdocumento.idTdocumento=" + tipo.getIdTdocumento();
        }
        if (numeroDoc.compareTo("") != 0) {
            HQL += "AND p.numeroDocumento=" + Integer.parseInt(numeroDoc);
        }
        if (vigentes == true && cancelados == true) {
            return HibernateUtil.ejecutarConsulta(HQL);
        }

        if (vigentes == true && cancelados == false) {
            HQL += "AND p.fecBaja IS NULL ";
        }

        if (vigentes == false && cancelados == true) {
            HQL += "AND p.fecBaja IS NOT NULL ";
        }

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static List<Empleado> getEmpleadosVigentes() {
        return HibernateUtil.ejecutarConsulta("FROM Empleado as p WHERE p.fecBaja IS NULL ");
    }
}
