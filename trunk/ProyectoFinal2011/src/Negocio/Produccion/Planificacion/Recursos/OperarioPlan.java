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
public class OperarioPlan {

    private  Empleado empleado;
    private  boolean presente,ocupado;

    public OperarioPlan(Empleado e, boolean presente) {
        this.empleado=e;
        this.presente=presente;
        this.ocupado=false;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }



}
