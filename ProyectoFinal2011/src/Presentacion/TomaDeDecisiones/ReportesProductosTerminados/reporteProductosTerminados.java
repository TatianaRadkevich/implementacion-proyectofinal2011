/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones.ReportesProductosTerminados;

import Presentacion.TomaDeDecisiones.ReportesMateriales.*;
import Presentacion.TomaDeDecisiones.abstractReport;

/**
 *
 * @author Heber Parrucci
 */
public class reporteProductosTerminados extends abstractReport {

String master = System.getProperty("user.dir") + "\\src\\Presentacion\\TomaDeDecisiones\\ReportesProductosTerminados\\ArchivosGeneradoresReportes\\reporteProductosTerminados.jasper";

public reporteProductosTerminados(){
    super();
}

public void runReporte(){
super.runReporte(master);
}

}
