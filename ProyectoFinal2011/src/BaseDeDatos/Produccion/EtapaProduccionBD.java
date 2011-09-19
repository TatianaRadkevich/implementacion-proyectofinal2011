/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.EtapaProduccion;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class EtapaProduccionBD {
 public static List<EtapaProduccion> listarEtapaProduccion() throws ExceptionInInitializerError{

        List<EtapaProduccion> result=null;
        result = HibernateUtil.ejecutarConsulta("from EtapaProduccion");
        return result;
    }

    public static List<EtapaProduccion> listarEtapaProduccionAlta() {
        List<EtapaProduccion> result=null;
        result = HibernateUtil.ejecutarConsulta("from EtapaProduccion as p where p.fecBaja=null");
        return result;

    }

    public EtapaProduccionBD() {

    }

    public static EtapaProduccion guardar(EtapaProduccion tipoProducto){
        HibernateUtil.guardarObjeto(tipoProducto);
        return tipoProducto;
    }

    public static EtapaProduccion modificar(EtapaProduccion tipoProducto){
        HibernateUtil.modificarObjeto(tipoProducto);
        return tipoProducto;
    }

}