/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.FormaDePagoBD;
import Presentacion.Administracion.FormaDePagoPantalla;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Gabriela
 */
public class GestorFormaPago {



    public static void administarFormPago(Dialog parent){
        FormaDePagoPantalla FormaPago=new FormaDePagoPantalla(null, true);
        FormaPago.setVisible(true);
    }
    public static void administrarFormaPagoAgregar(Dialog parent){
        FormaDePagoPantalla FormaPago1=new FormaDePagoPantalla(null, true);
        FormaPago1.nuevo();
        FormaPago1.setVisible(true);
    }


    public static List<FormaPago> listarFormaPago(){
        return FormaDePagoBD.listarFormaPago();
    }
    public FormaPago guardar(FormaPago forPag1){
        return FormaDePagoBD.guardar(forPag1);
    }


    public FormaPago modificar(FormaPago forPag1){
        return FormaDePagoBD.modificar(forPag1);
    }

    public void eliminar(FormaPago forPag1){
        FormaDePagoBD.eliminar(forPag1);
    }


}
