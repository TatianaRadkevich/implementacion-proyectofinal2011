/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.EtapaProduccionBD;
import Presentacion.Produccion.PantallaABMEtapaProduccion;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorEtapaProduccion {

    public static void administarTipoProducto(Dialog parent){
        PantallaABMEtapaProduccion tipoProducto=new PantallaABMEtapaProduccion(null, true);
        tipoProducto.setVisible(true);
    }
    public static void administarTipoProductoAgregar(Dialog parent){
        PantallaABMEtapaProduccion tipoProducto=new PantallaABMEtapaProduccion(null, true);
        tipoProducto.nuevo();
        tipoProducto.setVisible(true);
    }

    public static List<EtapaProduccion> listarTiposProductoAlta(){
        return EtapaProduccionBD.listarEtapaProduccionAlta();
    }
    public static List<EtapaProduccion> listarTipoProducto(){
        return EtapaProduccionBD.listarEtapaProduccion();
    }
    public EtapaProduccion guardar(EtapaProduccion etapaProduccion){
        return EtapaProduccionBD.guardar(etapaProduccion);
    }


    public EtapaProduccion modificar(EtapaProduccion etapaProduccion){
        return EtapaProduccionBD.modificar(etapaProduccion);
    }
}
