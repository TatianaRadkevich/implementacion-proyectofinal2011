/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Deposito;

import BaseDeDatos.HibernateUtil;
import Negocio.Deposito.Reclamo;
import java.util.List;

/**
 *
 * @author Heber Parrucci
 */
public class EstadoReclamoBD {

    private static final String Est_Generado = "Generado";
    private static final String Est_Solucionado = "Solucionado";

    public static EstadoReclamo getEst_Generado() {
        return getEstadoReclamo(Est_Generado);
    }

    public static EstadoReclamo getEst_Solucionado() {
        return getEstadoReclamo(Est_Solucionado);
    }

     public static List<Reclamo> listarReclamos()
    {
        return HibernateUtil.ejecutarConsulta("FROM Reclamo");
    }


    private static EstadoReclamo getEstadoReclamo(String nombre) {
        String HQL = String.format("FROM EstadoReclamo as rec WHERE LOWER(rec.nombre) = LOWER('%s')", nombre);
        List<EstadoReclamo> lst = HibernateUtil.ejecutarConsulta(HQL);
        if (lst.isEmpty()) {
            throw new RuntimeException("No existe un \"estado\" con el nombre de : " + nombre);
        }
        return lst.get(0);
    }

}
