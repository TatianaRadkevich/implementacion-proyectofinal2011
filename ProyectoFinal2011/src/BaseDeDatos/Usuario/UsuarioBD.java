/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Usuario;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Empleado;
import Negocio.GestionUsuario.Usuario;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class UsuarioBD {
    
    public static Usuario getUsuario(String nombreUsuario, String Contrasenia){
        
        List<Usuario> var=HibernateUtil.ejecutarConsulta("from Usuario where nombreUsuario  = '" + nombreUsuario + "' and contrasenia  = '" + Contrasenia + "'");
        
        if(!var.isEmpty())        
        {
            return var.get(0);
        }
        else
        {
            return null;
        }
        
    }
            
    public static Empleado getEmpleado(Usuario uss){
        
        List<Empleado> var=HibernateUtil.ejecutarConsulta("from Empleado where TUsuarios = " + uss.getIdUsuario());
        
        if(!var.isEmpty())        
        {
            return var.get(0);
        }
        else
        {
            return null;
        }
    }
}
