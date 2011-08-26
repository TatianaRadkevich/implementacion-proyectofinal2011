/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.CargoBD;
import Presentacion.Administracion.PantallaABMCargo;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorCargo {

    public static void administarCargo(Dialog parent){
        PantallaABMCargo cargo=new PantallaABMCargo(null, true);
        cargo.setVisible(true);
    }

    public static List<Cargo> listarCargosAlta(){
        return CargoBD.listarCargosAlta();
    }
    public static List<Cargo> listarCargos(){
        return CargoBD.listarCargos();
    }
    public Cargo guardar(Cargo cargo){
        return CargoBD.guardar(cargo);
    }


    public Cargo modificar(Cargo cargo){
        return CargoBD.modificar(cargo);
    }
}
