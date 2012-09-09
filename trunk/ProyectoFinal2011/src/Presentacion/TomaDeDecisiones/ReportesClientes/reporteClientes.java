/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones.ReportesClientes;

import Presentacion.TomaDeDecisiones.ReportesMateriales.*;
import Presentacion.TomaDeDecisiones.abstractReport;

/**
 *
 * @author Heber Parrucci
 */
public class reporteClientes extends abstractReport {

String master = System.getProperty("user.dir") + "\\src\\Presentacion\\TomaDeDecisiones\\ReportesClientes\\ArchivosGeneradoresReportes\\reporteClientesQueMasCompraron.jasper";

public reporteClientes(){
    super();
}

public void runReporte(){
super.runReporte(master);
}

}
