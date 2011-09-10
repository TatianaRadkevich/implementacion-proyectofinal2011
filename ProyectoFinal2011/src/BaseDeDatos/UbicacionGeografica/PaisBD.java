/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.UbicacionGeografica;

import BaseDeDatos.HibernateUtil;
import Negocio.UbicacionGeografica.Pais;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ivan
 */
public class PaisBD {

     public static List<Pais> listarPaises() throws ExceptionInInitializerError{
         Session usuario = null;
        List<Pais> result= new ArrayList<Pais>(0);

        try{

            result = HibernateUtil.ejecutarConsulta("from Pais");

        }catch(Exception  ex) {}
        return result;

    }


    public static Pais guardar(Pais pai){


        HibernateUtil.guardarObjeto(pai);
        return pai;
    }

    public static Pais modificar(Pais pai){
        HibernateUtil.modificarObjeto(pai);

        return pai;
    }

    public static void eliminar(Pais pai){
        HibernateUtil.EliminarObjeto(pai);
    }

}
