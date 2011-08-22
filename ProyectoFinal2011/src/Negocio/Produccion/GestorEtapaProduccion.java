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

    public static List<EstadoEtapaProduccion> listarTiposProductoAlta(){
        return EtapaProduccionBD.listarEtapaProduccionAlta();
    }
    public static List<EstadoEtapaProduccion> listarTipoProducto(){
        return EtapaProduccionBD.listarEtapaProduccion();
    }
    public EstadoEtapaProduccion guardar(EstadoEtapaProduccion etapaProduccion){
        return EtapaProduccionBD.guardar(etapaProduccion);
    }


    public EstadoEtapaProduccion modificar(EstadoEtapaProduccion etapaProduccion){
        return EtapaProduccionBD.modificar(etapaProduccion);
    }
}
