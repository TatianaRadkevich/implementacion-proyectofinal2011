/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones.ReportesCobro;

import Presentacion.TomaDeDecisiones.abstractReport;

/**
 *
 * @author Heber Parrucci
 */
public class reporteCobro extends abstractReport {

String master = System.getProperty("user.dir") + "\\src\\Presentacion\\TomaDeDecisiones\\ReportesCobro\\ArchivosGeneradoresReportes\\reporteCobro.jasper";

public reporteCobro(){
    super();
}

public void runReporte(){
super.runReporte(master);
}

}
