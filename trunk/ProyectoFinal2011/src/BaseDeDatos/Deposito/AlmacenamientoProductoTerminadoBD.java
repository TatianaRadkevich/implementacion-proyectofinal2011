/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Deposito;

import BaseDeDatos.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fbobbio
 */
public class AlmacenamientoProductoTerminadoBD {

     public static List<AlmacenamientoProductoTerminado> listarAlmacenamientos() throws ExceptionInInitializerError{

        List<AlmacenamientoProductoTerminado> result=new ArrayList<AlmacenamientoProductoTerminado>(0);

        try{
            result = HibernateUtil.ejecutarConsulta("from AlmacenamientoProductoTerminado");

        }catch(Exception  ex) {}

        return result;
    }

    public static short getCodigo() {

        try{
        return (Short) HibernateUtil.ejecutarConsulta("SELECT max(idAlmacenamiento) FROM AlmacenamientoProductoTerminado").get(0);
        }catch(Exception e)
        {return 0;}

    }



    public AlmacenamientoProductoTerminadoBD() {
        super();
    }

    public static AlmacenamientoProductoTerminado guardar(AlmacenamientoProductoTerminado r){
        HibernateUtil.guardarObjeto(r);
        return r;
    }

    public static AlmacenamientoProductoTerminado modificar(AlmacenamientoProductoTerminado r){
        HibernateUtil.modificarObjeto(r);
        return r;
    }

   public static void eliminar (AlmacenamientoProductoTerminado r){
       HibernateUtil.EliminarObjeto(r);
   }
    
}
