/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.EstadoEtapaProduccion;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class EtapaProduccionBD {
 public static List<EstadoEtapaProduccion> listarEtapaProduccion() throws ExceptionInInitializerError{

        List<EstadoEtapaProduccion> result=null;
        result = HibernateUtil.ejecutarConsulta("from EstadoEtapaProduccion");
        return result;
    }

    public static List<EstadoEtapaProduccion> listarEtapaProduccionAlta() {
        List<EstadoEtapaProduccion> result=null;
        result = HibernateUtil.ejecutarConsulta("from EstadoEtapaProduccion as p where p.fecBaja=null");
        return result;

    }

    public EtapaProduccionBD() {

    }

    public static EstadoEtapaProduccion guardar(EstadoEtapaProduccion tipoProducto){
        HibernateUtil.guardarObjeto(tipoProducto);
        return tipoProducto;
    }

    public static EstadoEtapaProduccion modificar(EstadoEtapaProduccion tipoProducto){
        HibernateUtil.modificarObjeto(tipoProducto);
        return tipoProducto;
    }

}
