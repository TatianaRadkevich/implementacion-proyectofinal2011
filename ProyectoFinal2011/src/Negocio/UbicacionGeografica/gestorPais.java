/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.UbicacionGeografica;

import BaseDeDatos.UbicacionGeografica.PaisBD;
import Presentacion.UbicacionGeografica.PantallaABMPais;
import java.awt.Dialog;
import java.util.List;
/**
 *
 * @author Heber Parrucci
 */
public class gestorPais {

 public static void administrarPais(Dialog parent){
        PantallaABMPais pais=new PantallaABMPais(null, true);
        pais.setVisible(true);
    }
    public static void administrarPaisAgregar(Dialog parent){
        PantallaABMPais pais=new PantallaABMPais(null, true);
        pais.nuevo();
        pais.setVisible(true);
    }


    public static List<Pais> listarPaises(){
        return PaisBD.listarPaises();
    }

    public Pais guardar(Pais pai){
        return PaisBD.guardar(pai);
    }


    public Pais modificar(Pais pai){
        return PaisBD.modificar(pai);
    }

    public void eliminar(Pais pai){
        PaisBD.eliminar(pai);
    }
}
