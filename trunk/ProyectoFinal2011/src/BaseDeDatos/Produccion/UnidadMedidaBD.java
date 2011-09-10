/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Produccion;

import BaseDeDatos.HibernateUtil;
import Negocio.Produccion.UnidadMedida;
import Negocio.Produccion.GestorProducto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Heber Parrucci
 */
public class UnidadMedidaBD {
    public static List<UnidadMedida> listarUnidadMedida() throws ExceptionInInitializerError{

        List<UnidadMedida> result=new ArrayList<UnidadMedida>(0);

        try{
            result = HibernateUtil.ejecutarConsulta("from UnidadMedida");

        }catch(Exception  ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);}

        return result;
    }



    public UnidadMedidaBD() {
        super();
    }

    public static UnidadMedida guardar(UnidadMedida unidadMed){
        HibernateUtil.guardarObjeto(unidadMed);
        return unidadMed;
    }

    public static UnidadMedida modificar(UnidadMedida unidadMed){
        HibernateUtil.modificarObjeto(unidadMed);
        return unidadMed;
    }

   public static void eliminar (UnidadMedida unidadMed){
       HibernateUtil.EliminarObjeto(unidadMed);
   }


}
