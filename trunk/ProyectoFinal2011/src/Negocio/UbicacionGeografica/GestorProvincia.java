/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.UbicacionGeografica;

import BaseDeDatos.UbicacionGeografica.ProvinciaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.UbicacionGeografica.PantallaABMProvincia;
import java.awt.Dialog;
import java.util.List;
/**
 *
 * @author Heber Parrucci
 */
public class GestorProvincia {

     public static void administrarProvincia(Dialog parent){
        PantallaABMProvincia pro=new PantallaABMProvincia(null, true);
        pro.setVisible(true);
    }

     public static List<Pais> traerPaises() throws ExceptionGestor {
        return gestorPais.listarPaises();
    }

    public static void administrarPaisAgregar(Dialog parent){
        PantallaABMProvincia pro=new PantallaABMProvincia(null, true);
        pro.nuevo();
        pro.setVisible(true);
    }


    public static List<Provincia> listarProvincias(){
        return ProvinciaBD.listarProvincias();
    }

    public static List<Provincia> listarProvincias(Pais pai){
        return ProvinciaBD.listarProvincias(pai);
    }


    public Provincia guardar(Provincia pro){
        return ProvinciaBD.guardar(pro);
    }


    public Provincia modificar(Provincia pro){
        return ProvinciaBD.modificar(pro);
    }

    public void eliminar(Provincia pro){
        ProvinciaBD.eliminar(pro);
    }

}
