/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;
import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ivan
 */
public class HerramientaBD {   
  

    public static int getUltimoID()
    {
        try{
        return (Integer) HibernateUtil.ejecutarConsulta("SELECT   max(idHerramientaParticular) FROM HerramientaParticular").get(0);
        }catch(Exception e)
        {return 0;}
    }

    public static List<TipoHerramienta> listarTipoHerramienta() {
        return HibernateUtil.ejecutarConsulta("FROM TipoHerramienta");
    }

    public static List<HerramientaParticular> getHerramientaParticular(String modelo ,String nombre,boolean vigentes,boolean eliminados) {
        String HQL=String.format(
                "FROM HerramientaParticular as mh "
                + "WHERE LOWER(mh.nombre) LIKE LOWER('%s%%') "
                + "AND LOWER(mh.modelo) LIKE LOWER('%s%%') ",
               /// + "AND LOWER(mh.codigo) LIKE LOWER('%s%%')",
                nombre, modelo);//, codigo);

         if(vigentes==false&&eliminados==false)
            return new ArrayList<HerramientaParticular>(0);

         if(vigentes==true&&eliminados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&eliminados==false)
            HQL+="AND mh.fecBaja IS NULL ";

        if(vigentes==false&&eliminados==true)
            HQL+="AND mh.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }

        public static List<HerramientaParticular> getHerramientaParticular(TipoHerramienta tm,boolean vigentes,boolean eliminados) {
        String HQL="FROM HerramientaParticular as mh "
                + "WHERE "+((tm==null)?"1=1":"mh.TTherramienta.idTherramienta = "+tm.getIdTherramienta());

         if(vigentes==false&&eliminados==false)
            return new ArrayList<HerramientaParticular>(0);

         if(vigentes==true&&eliminados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&eliminados==false)
            HQL+=" AND mh.fecBaja IS NULL ";

        if(vigentes==false&&eliminados==true)
            HQL+=" AND mh.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static void guardar(HerramientaParticular mh) {
        HibernateUtil.guardarObjeto(mh);
    }

    public static void modificar(HerramientaParticular mh) {
        HibernateUtil.modificarObjeto(mh);
    }
}
