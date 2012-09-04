/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion.Planificacion.Recursos;

import Negocio.Administracion.Empleado;

/**
 *
 * @author Dicsys
 */
public class Operario {

    private  Empleado empleado;
    private  boolean presente;

    public Operario(Empleado e, boolean b) {
        this.empleado=e;
        this.presente=b;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
