/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.Material;
import Negocio.Produccion.MaquinaParticular;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class MaterialBD {

    public static List<Material> getMateriales(String codigo, String nombre,boolean vigentes,boolean cancelados) {
        String HQL=String.format(
                "FROM Material as m "
                + "WHERE LOWER(m.nombre) LIKE  LOWER('%s%%') "
                + "AND LOWER(m.codigo) LIKE LOWER('%s%%') ",
                nombre,codigo);


        if(vigentes==false&&cancelados==false)
            return new ArrayList<Material>(0);

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&cancelados==false)
            HQL+="AND m.fecBaja IS NULL ";

        if(vigentes==false&&cancelados==true)
            HQL+="AND m.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static Material getMaterial(String codigo){
        List lista=HibernateUtil.ejecutarConsulta("from Material m where m.codigo like '"+codigo+"'");
        if (lista.isEmpty())
                return null;
        else
            return (Material) lista.get(0);


    }

    public static int getUltimoID()
    {
        try{
        return (Integer) HibernateUtil.ejecutarConsulta("SELECT max(idMaterial) FROM Material").get(0);
        }catch(Exception e)
        {return -1;}
    }

    public static void guardar(Material m) {
       HibernateUtil.guardarObjeto(m);
    }

     public static void modificar(Material m) {
       HibernateUtil.modificarObjeto(m);
    }

     public static List<Material> getMateriales(boolean esMateriaPrima){
          String HQL="FROM Material as m "
                + "WHERE m.esMateriaPrima = " + esMateriaPrima;

          HQL+=" AND m.fecBaja IS NULL ";
         return HibernateUtil.ejecutarConsulta(HQL);
     }

    public static boolean existeNombre(String nombre) {
         String HQL="FROM Material as m "
                + "WHERE m.nombre like '" + nombre +"'";

         if(HibernateUtil.ejecutarConsulta(HQL).isEmpty())
             return false;
         else
             return true;
    }

    public static List<Material> listarMaterialesVigentes() {
         String HQL="FROM Material as m WHERE m.fecBaja IS NULL ";
         return HibernateUtil.ejecutarConsulta(HQL);
    }
}
