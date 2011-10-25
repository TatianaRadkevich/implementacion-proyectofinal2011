/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Compras;

import BaseDeDatos.HibernateUtil;
import Negocio.Compras.Reclamo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heber Parrucci
 */
public class ReclamoBD {

     public static List<Reclamo> listarReclamos() throws ExceptionInInitializerError{

        List<Reclamo> result=new ArrayList<Reclamo>(0);

        try{
            result = HibernateUtil.ejecutarConsulta("from Reclamo");

        }catch(Exception  ex) {}

        return result;
    }

    public static short getCodigo() {

        try{
        return (Short) HibernateUtil.ejecutarConsulta("SELECT max(idReclamo) FROM Reclamo").get(0);
        }catch(Exception e)
        {return 0;}

    }



    public ReclamoBD() {
        super();
    }

    public static Reclamo guardar(Reclamo r){
        HibernateUtil.guardarObjeto(r);
        return r;
    }

    public static Reclamo modificar(Reclamo r){
        HibernateUtil.modificarObjeto(r);
        return r;
    }

   public static void eliminar (Reclamo r){
       HibernateUtil.EliminarObjeto(r);
   }
}
