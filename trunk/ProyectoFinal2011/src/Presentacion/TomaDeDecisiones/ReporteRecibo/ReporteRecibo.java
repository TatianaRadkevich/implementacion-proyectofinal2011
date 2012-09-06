/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones.ReporteRecibo;

import Presentacion.TomaDeDecisiones.abstractReport;
/**
 *
 * @author Fede
 */
public class ReporteRecibo extends abstractReport{
    String rutaReporte = System.getProperty("user.dir") + "\\src\\Presentacion\\TomaDeDecisiones\\ReporteRecibo\\Source\\Recibo.jasper";

    public ReporteRecibo(){
        super();
    }

    public void runReporte(){
        super.runReporte(rutaReporte);
    }
}