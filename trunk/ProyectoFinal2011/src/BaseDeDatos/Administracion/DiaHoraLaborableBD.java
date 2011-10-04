/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.DiaHoraLaborable;

/**
 *
 * @author Ivan
 */
public class DiaHoraLaborableBD {


      public static DiaHoraLaborable guardar(DiaHoraLaborable diaHoraLaborable){
        HibernateUtil.guardarObjeto(diaHoraLaborable);
        return diaHoraLaborable;
    }

    public static DiaHoraLaborable modificar(DiaHoraLaborable diaHoraLaborable){
        HibernateUtil.modificarObjeto(diaHoraLaborable);
        return diaHoraLaborable;
    }
}
