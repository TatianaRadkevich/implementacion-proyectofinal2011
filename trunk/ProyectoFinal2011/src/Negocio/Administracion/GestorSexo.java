/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.SexoBD;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorSexo {

       public static void administarSexo(Dialog parent){
//         tipoDocumento=new PantallaABMTipoDocumento(null, true);
//        tipoDocumento.setVisible(true);
    }
    public static void administrarTipoDocumentoAgregar(Dialog parent){
//        PantallaABMTipoDocumento tipoDocumento=new PantallaABMTipoDocumento(null, true);
//        tipoDocumento.nuevo();
//        tipoDocumento.setVisible(true);
    }


    public static List<Sexo> listarSexos(){
        return SexoBD.listarSexo();
    }
    public Sexo guardar(Sexo sexo){
        return SexoBD.guardar(sexo);
    }


    public Sexo modificar(Sexo sexo){
        return SexoBD.modificar(sexo);
    }

    public void eliminar(Sexo sexo){
        SexoBD.eliminar(sexo);
    }
}
