/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Cargo;
import Negocio.Produccion.GestorProducto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan
 */
public class CargoBD {

     public static List<Cargo> listarCargos() throws ExceptionInInitializerError{
       
        List<Cargo> result=new ArrayList<Cargo>();

        try{
            result = HibernateUtil.ejecutarConsulta("from Cargo");

        }catch(Exception  ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);}

        return result;
    }

    public static List<Cargo> listarCargosAlta() {
        List<Cargo> result=null;
        result = HibernateUtil.ejecutarConsulta("from Cargo as p where p.fecBaja=null");
        return result;
    }

    public static Cargo getCargoPorNombre(String nombre) {
        String consulta="FROM Cargo as c WHERE LOWER(c.nombre) LIKE LOWER('"+nombre.trim()+"')";
        List<Cargo> result = HibernateUtil.ejecutarConsulta(consulta);
        return (result.isEmpty())?null:result.get(0);
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
