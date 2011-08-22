/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Cargo;
import Negocio.Produccion.GestorProducto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan
 */
public class CargoBD {

     public static List<Cargo> listarCargos() throws ExceptionInInitializerError{
       
        List<Cargo> result=null;

        try{
            result = HibernateUtil.ejecutarConsulta("from Cargo");

        }catch(Exception  ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);}

        return result;
    }

    public static List<Cargo> listarCargosAlta() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public CargoBD() {
        super();
    }

    public static Cargo guardar(Cargo cargo){
        HibernateUtil.guardarObjeto(cargo);
        return cargo;
    }

    public static Cargo modificar(Cargo cargo){
        HibernateUtil.modificarObjeto(cargo);
        return cargo;
    }


}
