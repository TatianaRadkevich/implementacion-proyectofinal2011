/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;
import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.FormaPago;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriela
 */
public class FormaDePagoBD {



    public static List<FormaPago> listarFormaPago() throws ExceptionInInitializerError{

        List<FormaPago> result=new ArrayList<FormaPago>(0);

        try{
            result = HibernateUtil.ejecutarConsulta("from FormaPago");

        }catch(Exception  ex) {}

        return result;
    }



    public FormaDePagoBD() {
        super();
    }

    public static FormaPago guardar(FormaPago forPag){
        HibernateUtil.guardarObjeto(forPag);
        return forPag;
    }

    public static FormaPago modificar(FormaPago forPag){
        HibernateUtil.modificarObjeto(forPag);
        return forPag;
    }

   public static void eliminar (FormaPago forPag){
       HibernateUtil.EliminarObjeto(forPag);
   }


}


