/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.UbicacionGeografica;

import BaseDeDatos.UbicacionGeografica.LocalidadBD;
import BaseDeDatos.UbicacionGeografica.ProvinciaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.UbicacionGeografica.PantallaABMLocalidad;
import java.awt.Dialog;
import java.util.List;
/**
 *
 * @author Heber Parrucci
 */
public class GestorLocalidad {

    public static void administrarLocalidad(Dialog parent){
        PantallaABMLocalidad pro=new PantallaABMLocalidad(null, true);
        pro.setVisible(true);
    }

     public static List<Pais> traerPaises() throws ExceptionGestor {
        return gestorPais.listarPaises();
    }

    public static List<Provincia> traerProvincias() throws ExceptionGestor {
        return GestorProvincia.listarProvincias();
    }

    public static void administrarLocalidadAgregar(Dialog parent){
        PantallaABMLocalidad loc=new PantallaABMLocalidad(null, true);
        loc.nuevo();
        loc.setVisible(true);
    }

     public static List<Provincia> listarProvincias(Pais pai){
        return GestorProvincia.listarProvincias(pai);
    }


    public static List<Localidad> listarLocalidades(){
        return LocalidadBD.listarLocalidades();
    }

    public static List<Localidad> listarLocalidades(Provincia pro){
        return LocalidadBD.listarLocalidades(pro);
    }


    public Localidad guardar(Localidad loc){
        return LocalidadBD.guardar(loc);
    }


    public Localidad modificar(Localidad loc){
        return LocalidadBD.modificar(loc);
    }

    public void eliminar(Localidad loc){
        LocalidadBD.eliminar(loc);
    }

}
