/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones.ReportesMateriales;

import Presentacion.TomaDeDecisiones.abstractReport;

/**
 *
 * @author Heber Parrucci
 */
public class reporteMateriales extends abstractReport {

String master = System.getProperty("user.dir") + "\\src\\Presentacion\\TomaDeDecisiones\\ReportesMateriales\\ArchivosGeneradoresReportes\\reporteMateriales.jasper";

public reporteMateriales(){
    super();
}

public void runReporte(){
super.runReporte(master);
}

}
