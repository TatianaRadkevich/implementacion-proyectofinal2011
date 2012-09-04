/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion.Planificacion.Recursos;

import Negocio.Administracion.Empleado;
import java.util.ArrayList;

/**
 *
 * @author Dicsys
 */
public class Recursos {

    private ArrayList<Empleado> lEmpleados=new ArrayList<Empleado>(10);

    public void addOperario(Empleado e) {
        lEmpleados.add(e);
    }


}
