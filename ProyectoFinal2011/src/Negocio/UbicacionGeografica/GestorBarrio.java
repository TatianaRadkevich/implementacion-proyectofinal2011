/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.UbicacionGeografica;

import BaseDeDatos.UbicacionGeografica.BarrioBD;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorBarrio {

     public static void administrarBarrio(Dialog parent){
//        PantallaABMProvincia pro=new PantallaABMProvincia(null, true);
//        pro.setVisible(true);
    }

     public static List<Barrio> traerBarrios() {
        return BarrioBD.listarBarrios();
    }

    public static List<Barrio> listarBarrios(Localidad local){
        return BarrioBD.listarBarrios(local);
    }


    public Barrio guardar(Barrio barr){
        return BarrioBD.guardar(barr);
    }


    public Barrio modificar(Barrio barr){
        return BarrioBD.modificar(barr);
    }

    public void eliminar(Barrio barr){
        BarrioBD.eliminar(barr);
    }


}
