/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.Proveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class ProveedorBD {

    public static List<Proveedor> listarProveedores() {
        return HibernateUtil.ejecutarConsulta("FROM Proveedor");
    }
    public static List<Proveedor> listarProveedoresDisponibles() {
        return HibernateUtil.ejecutarConsulta("FROM Proveedor where fecBaja is null");
    }

    public static void guardar(Proveedor p) {
        HibernateUtil.guardarObjeto(p.getDomicilio());
        HibernateUtil.guardarObjeto(p);
    }

    public static void modificar(Proveedor p) {
        HibernateUtil.modificarObjeto(p.getDomicilio());
        HibernateUtil.modificarObjeto(p);
    }

    public static List<Proveedor> getProveedores(String cuit, String rz,
        String nombre, String apellido,boolean vigentes,boolean cancelados){

        if(vigentes==false&&cancelados==false)
            return new ArrayList<Proveedor>(0);

        String HQL=String.format(
                "FROM Proveedor as p "
                + "WHERE LOWER(p.cuit) like  LOWER('%s%%') "
                + "AND LOWER(p.razonSocial) like  LOWER('%s%%') "
                + "AND LOWER(p.nombreResponsable) like  LOWER('%s%%') "
                + "AND LOWER(p.apellidoResponsable) like  LOWER('%s%%') "
                ,cuit,rz,nombre,apellido);

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&cancelados==false)
            HQL+="AND p.fecBaja IS NULL ";

        if(vigentes==false&&cancelados==true)
            HQL+="AND p.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static List<Proveedor> getProveedor(String cuit) {

        String HQL= "FROM Proveedor where cuit like '"+ cuit+"'";
        return HibernateUtil.ejecutarConsulta(HQL);
    }

}
