/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones.ReporteFactura;

import Presentacion.TomaDeDecisiones.abstractReport;
/**
 *
 * @author Fede
 */
public class ReporteFactura extends abstractReport{
    String rutaReporte = System.getProperty("user.dir") + "\\src\\Presentacion\\TomaDeDecisiones\\ReporteFactura\\Source\\Factura.jasper";

    public ReporteFactura(){
        super();
    }

    public void runReporte(){
        super.runReporte(rutaReporte);
    }
}