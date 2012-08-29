/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.EstadoPlanProduccion;
import Negocio.Produccion.PlanProduccion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlanProduccionBD {

    /* Consulta realizada para cumplir con el paso 2 del CU: Generar Orden de Trabajo */
    public static List<PlanProduccion> listarPlanesPendientesActual()throws ExceptionInInitializerError, ParseException{
        /* Recordar: Hacer la consulta HQL bien, por ahora la hago así para testing */
        String HQL = String.format(" FROM PlanProduccion as pp ");
        List<PlanProduccion> var = HibernateUtil.ejecutarConsulta(HQL);
        List<PlanProduccion> ret = new ArrayList<PlanProduccion>();
        for(PlanProduccion pp : var)
        {
            if(pp.getFecHoraPrevistaInicio().getDate() == Calendar.getInstance().getTime().getDate() && pp.getFecHoraPrevistaInicio().getMonth() == Calendar.getInstance().getTime().getMonth() && pp.getFecHoraPrevistaInicio().getYear() == Calendar.getInstance().getTime().getYear() && pp.getEstado().getIdEplanProduccion() == 1)
            {
                Boolean bandera = false;
                for(DetallePlanProduccion dpp : pp.getDetallePlan())
                {
                    if(dpp.getTEdetallePlan().getIdEdetallePlan() == 5 && dpp.getTOrdenesTrabajo() == null)
                    {
                        bandera = true;
                    }
                }

                if(bandera)
                {
                    ret.add(pp);
                }
            }
        }

        return ret;
    }

    public static PlanProduccion guardar(PlanProduccion plan)
    {
        HibernateUtil.guardarObjeto(plan);
        return plan;
    }
    
    public static PlanProduccion Modificar(PlanProduccion plan)
    {
        HibernateUtil.modificarObjeto(plan);
        return plan;
    }
}
