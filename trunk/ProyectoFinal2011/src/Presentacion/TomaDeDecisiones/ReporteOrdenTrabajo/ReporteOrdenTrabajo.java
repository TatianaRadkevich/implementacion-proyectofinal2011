/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones.ReporteOrdenTrabajo;

import Presentacion.TomaDeDecisiones.abstractReport;
/**
 *
 * @author Fede
 */
public class ReporteOrdenTrabajo extends abstractReport{
    String rutaReporte = System.getProperty("user.dir") + "\\src\\Presentacion\\TomaDeDecisiones\\ReporteOrdenTrabajo\\Source\\OrdenTrabajo.jasper";

    public ReporteOrdenTrabajo(){
        super();
    }

    public void runReporte(){
        super.runReporte(rutaReporte);
    }
}


