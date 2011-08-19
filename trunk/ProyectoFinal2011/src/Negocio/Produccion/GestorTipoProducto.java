/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.TipoProductoBD;
import Presentacion.Produccion.PantallaABMTipoProducto;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorTipoProducto {


    public static void administarTipoProducto(Dialog parent){
        PantallaABMTipoProducto tipoProducto=new PantallaABMTipoProducto(null, true);
        tipoProducto.setVisible(true);
    }

    public static List<TipoProducto> listarTiposProductoAlta(){
        return TipoProductoBD.listarTiposProductosAlta();
    }
    public static List<TipoProducto> listarTipoProducto(){
        return TipoProductoBD.listarTiposProductos();
    }
    public TipoProducto guardar(TipoProducto tipoProducto){
        return TipoProductoBD.guardar(tipoProducto);
    }


    public TipoProducto modificar(TipoProducto tipoProducto){
        return TipoProductoBD.modificar(tipoProducto);
    }
}

    
