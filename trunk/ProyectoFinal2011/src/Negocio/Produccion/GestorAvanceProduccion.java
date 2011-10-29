/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.DetallePlanProduccionBD;
import BaseDeDatos.Produccion.EstadoDetallePlanBD;
import Presentacion.Produccion.PantallaABMAvanceProduccion;
import javax.swing.JDialog;

/**
 *
 * @author Ivan
 */
public class GestorAvanceProduccion {

    public GestorAvanceProduccion() {
    }


    public void iniciarCU(JDialog dialog){
        PantallaABMAvanceProduccion pantalla=new PantallaABMAvanceProduccion(dialog, true, this);
        pantalla.setVisible(true);


    }

    public void registrarAvance(DetallePlanProduccion detalle) {
        detalle.setTEdetallePlan(EstadoDetallePlanBD.traerEstadoFinalizado());
        DetallePlanProduccionBD.modificar(detalle);
    }


}
