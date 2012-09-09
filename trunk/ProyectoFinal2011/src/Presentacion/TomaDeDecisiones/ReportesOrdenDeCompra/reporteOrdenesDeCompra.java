/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones.ReportesOrdenDeCompra;

import Presentacion.TomaDeDecisiones.ReportesProductosTerminados.*;
import Presentacion.TomaDeDecisiones.ReportesMateriales.*;
import Presentacion.TomaDeDecisiones.abstractReport;

/**
 *
 * @author Heber Parrucci
 */
public class reporteOrdenesDeCompra extends abstractReport {

String master = System.getProperty("user.dir") + "\\src\\Presentacion\\TomaDeDecisiones\\ReportesOrdenDeCompra\\ArchivosGeneradoresReportes\\reporteOrdenesDeCompraPendientesDeEnvio.jasper";

public reporteOrdenesDeCompra(){
    super();
}

public void runReporte(){
super.runReporte(master);
}

}
