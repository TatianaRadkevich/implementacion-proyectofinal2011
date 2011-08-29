/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.EstadoMaquina;
import Negocio.Produccion.MaquinaHerramientaParticular;
import Negocio.Produccion.TipoMaquinaHerramienta;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Rodrigo
 */
public class MaquinaHerramientaBD {

    public static List<EstadoMaquina> listarEstadoMaquina()
    {
        return HibernateUtil.ejecutarConsulta("FROM EstadoMaquina");
    }

    public static List<TipoMaquinaHerramienta> listarTipoMaquinaHerramienta() {
        return HibernateUtil.ejecutarConsulta("FROM TipoMaquinaHerramienta");
    }

    public static List<MaquinaHerramientaParticular> getMaquinasHerramientas(String codigo,String modelo ,String nombre,boolean vigentes,boolean eliminados) {
        String HQL=String.format(
                "FROM MaquinaHerramientaParticular as mh "
                + "WHERE LOWER(mh.nombre) LIKE LOWER('%s%%') "
                + "AND LOWER(mh.modelo) LIKE LOWER('%s%%') "
                + "AND LOWER(mh.codigo) LIKE LOWER('%s%%')",
                nombre, modelo, codigo);

         if(vigentes==false&&eliminados==false)
            return new ArrayList<MaquinaHerramientaParticular>(0);

         if(vigentes==true&&eliminados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&eliminados==false)
            HQL+="AND mh.fecBaja IS NULL ";

        if(vigentes==false&&eliminados==true)
            HQL+="AND mh.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static void guardar(MaquinaHerramientaParticular mh) {
        HibernateUtil.guardarObjeto(mh);
    }

    public static void modificar(MaquinaHerramientaParticular mh) {
        HibernateUtil.modificarObjeto(mh);
    }

}
