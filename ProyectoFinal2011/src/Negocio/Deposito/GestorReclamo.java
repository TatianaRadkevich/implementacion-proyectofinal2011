/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Deposito;

import BaseDeDatos.Deposito.ReclamoBD;
import java.util.List;
/**
 *
 * @author Heber Parrucci
 */
public class GestorReclamo {

    public static List<Reclamo> listarReclamos(){
        return ReclamoBD.listarReclamos();
    }

    public Reclamo guardar(Reclamo r){
        return ReclamoBD.guardar(r);
    }

    public Reclamo modificar(Reclamo r){
        return ReclamoBD.modificar(r);
    }
    
    public void eliminar(Reclamo r){
        ReclamoBD.eliminar(r);
    }
}
