/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;
import Negocio.Produccion.TipoMaquinaHerramienta;
import java.util.List;
import BaseDeDatos.HibernateUtil;

/**
 *
 * @author Rodrigo
 */
public class TipoMaquinaHerramientaBD {

    public static List<TipoMaquinaHerramienta> listarTipoMaquinaHerramienta()
    {
        return HibernateUtil.ejecutarConsulta("FROM TipoMaquinaHerramienta ORDER BY nombre");
    }

    public static List<TipoMaquinaHerramienta> listarTipoMaquina()
    {
        return HibernateUtil.ejecutarConsulta("FROM TipoMaquinaHerramienta where ES_HERRAMIENTA=0 ORDER BY nombre");
    }

      public static List<TipoMaquinaHerramienta> listarTipoHerramienta()
    {
        return HibernateUtil.ejecutarConsulta("FROM TipoMaquinaHerramienta where ES_HERRAMIENTA=1 ORDER BY nombre");
    }
    
    public static void guardar(TipoMaquinaHerramienta tmh)
    {
        HibernateUtil.guardarObjeto(tmh);
    }

    public static void modificar(TipoMaquinaHerramienta tmh)
    {
        HibernateUtil.modificarObjeto(tmh);
    }

}