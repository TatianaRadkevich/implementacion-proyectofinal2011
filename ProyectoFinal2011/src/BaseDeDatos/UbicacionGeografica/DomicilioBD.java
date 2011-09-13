/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.UbicacionGeografica;

import BaseDeDatos.HibernateUtil;
import Negocio.UbicacionGeografica.Domicilio;

/**
 *
 * @author Ivan
 */
public class DomicilioBD {




    public static Domicilio guardar(Domicilio domicilio){


        HibernateUtil.guardarObjeto(domicilio);
        return domicilio;
    }

    public static Domicilio modificar(Domicilio domicilio){
        HibernateUtil.modificarObjeto(domicilio);

        return domicilio;
    }

    public static void eliminar(Domicilio domicilio){
        HibernateUtil.EliminarObjeto(domicilio);
    }
}
