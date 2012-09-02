/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TomaDeDecisiones;

import BaseDeDatos.HibernateUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;

/**
 *
 * @author Heber Parrucci
 */
public abstract class abstractReport {

private Connection conn;
private final String login = HibernateUtil.getUser();
private final String password = HibernateUtil.getPass();
private final String URL = HibernateUtil.getUrl();

Map parametro = new HashMap();

public abstractReport(){
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(URL, login, password);
    } catch (ClassNotFoundException ex) {
        ex.getMessage();
    } catch (SQLException ex) {
        ex.getMessage();
    }
}

public void addParameter(Object key, Object value){
    parametro.put(key, value);
}

public void runReporte(String master){
    try {

        System.out.println("master: " + master);
        if (master == null) {
            System.out.println("No se encuentra el archivo de reporte maestro");
            System.exit(2);
        }

        JasperReport masterReport = null;
        try {
            masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);
        } catch (JRException e) {
            System.out.println("Error cargando el reporte maestro " + e.getMessage());
            System.exit(3);
        }
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conn);
        JasperViewer jviewer = new JasperViewer(jasperPrint, false);
        jviewer.setVisible(true);
    } catch (Exception j) {
        System.out.println("Mensaje de error: " + j.getMessage());
    }
}

}
