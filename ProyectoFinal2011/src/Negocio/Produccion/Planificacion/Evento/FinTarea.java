/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion.Evento;

import Negocio.Produccion.Planificacion.Recursos.OperarioPlan;
import Negocio.Produccion.Planificacion.Simulador;
import Negocio.Produccion.Planificacion.Tarea;
import java.util.Date;

/**
 *
 * @author Dicsys
 */
public class FinTarea extends Evento {

    private Tarea tarea;
    private Simulador sim;

    public FinTarea(Simulador sim, Tarea tarea, Date fin) {
        super(fin);
        this.sim = sim;
        this.tarea = tarea;
        tarea.setFechaInicio(sim.getTiempoActual());
    }

    @Override
    public void ejecutar() {
        tarea.setFechaFin(this.getTiempo());
        tarea.liberarRecusos();
    }
}
