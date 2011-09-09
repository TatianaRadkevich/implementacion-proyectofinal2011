/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.TipoDocumentoBD;
import Presentacion.Administracion.PantallaABMTipoDocumento;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Heber Parrucci
 */
public class GestorTipoDocumento {


    public static void administarTipoDocumento(Dialog parent){
        PantallaABMTipoDocumento tipoDocumento=new PantallaABMTipoDocumento(null, true);
        tipoDocumento.setVisible(true);
    }
    public static void administrarTipoDocumentoAgregar(Dialog parent){
        PantallaABMTipoDocumento tipoDocumento=new PantallaABMTipoDocumento(null, true);
        tipoDocumento.nuevo();
        tipoDocumento.setVisible(true);
    }

    
    public static List<TipoDocumento> listarTipoDocumentos(){
        return TipoDocumentoBD.listarTipoDocumento();
    }
    public TipoDocumento guardar(TipoDocumento tipoDoc){
        return TipoDocumentoBD.guardar(tipoDoc);
    }


    public TipoDocumento modificar(TipoDocumento tipoDocumento){
        return TipoDocumentoBD.modificar(tipoDocumento);
    }

    public void eliminar(TipoDocumento tipoDoc){
        TipoDocumentoBD.eliminar(tipoDoc);
    }

}
