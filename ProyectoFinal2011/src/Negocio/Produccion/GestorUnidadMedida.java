/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.UnidadMedidaBD;
import Presentacion.Produccion.PantallaABMUnidadMedida;
import java.awt.Dialog;
import java.util.List;
/**
 *
 * @author Heber Parrucci
 */
public class GestorUnidadMedida {
 public static void administrarUnidadMedida(Dialog parent){
        PantallaABMUnidadMedida unidadMed=new PantallaABMUnidadMedida(null, true);
        unidadMed.setVisible(true);
    }
    public static void administrarUnidadMedidaAgregar(Dialog parent){
        PantallaABMUnidadMedida unidadMed=new PantallaABMUnidadMedida(null, true);
        unidadMed.nuevo();
        unidadMed.setVisible(true);
    }


    public static List<UnidadMedida> listarUnidadMedida(){
        return UnidadMedidaBD.listarUnidadMedida();
    }

    public UnidadMedida guardar(UnidadMedida unidadMed){
        return UnidadMedidaBD.guardar(unidadMed);
    }


    public UnidadMedida modificar(UnidadMedida unidadMed){
        return UnidadMedidaBD.modificar(unidadMed);
    }

    public void eliminar(UnidadMedida unidadMed){
        UnidadMedidaBD.eliminar(unidadMed);
    }
}
