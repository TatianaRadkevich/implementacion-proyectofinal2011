/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Sexo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class SexoBD {

     public static List<Sexo> listarSexo() throws ExceptionInInitializerError{

        List<Sexo> result=new ArrayList<Sexo>(0);

        try{
            result = HibernateUtil.ejecutarConsulta("from Sexo");

        }catch(Exception  ex) {}


        return result;

    }




    public static Sexo guardar(Sexo sexo){
        HibernateUtil.guardarObjeto(sexo);
        return sexo;
    }

    public static Sexo modificar(Sexo sexo){
        HibernateUtil.modificarObjeto(sexo);
        return sexo;
    }

   public static void eliminar (Sexo sexo){
       HibernateUtil.EliminarObjeto(sexo);
   }

}
