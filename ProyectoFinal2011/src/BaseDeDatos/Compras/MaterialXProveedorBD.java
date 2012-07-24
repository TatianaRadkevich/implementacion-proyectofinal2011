/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.Material;
import Negocio.Compras.MaterialesXProveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heber
 */
public class MaterialXProveedorBD {

 
    public static int getUltimoID()
    {
        try{
        return (Integer) HibernateUtil.ejecutarConsulta("SELECT max(idMaterialXProveedor) FROM MaterialesXProveedor").get(0);
        }catch(Exception e)
        {return -1;}
    }

    public static MaterialesXProveedor guardar(MaterialesXProveedor mxp) {
       HibernateUtil.guardarObjeto(mxp);
       return mxp;
    }

     public static MaterialesXProveedor modificar(MaterialesXProveedor mxp) {
       HibernateUtil.modificarObjeto(mxp);
       return mxp;
    }

}
