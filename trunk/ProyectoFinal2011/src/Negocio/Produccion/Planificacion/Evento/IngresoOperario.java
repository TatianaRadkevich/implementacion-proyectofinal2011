/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion.Evento;

import Negocio.Exceptiones.NegocioException;
import Negocio.Produccion.Planificacion.Recursos.OperarioPlan;
import Negocio.Produccion.Planificacion.Simulador;
import java.util.Date;

/**
 *
 * @author Dicsys
 */
public class IngresoOperario extends Evento {

    private OperarioPlan operario;
    private Date ingreso;
    private Simulador sim;

    public IngresoOperario(Simulador simu, OperarioPlan op, Date ingreso) {
        if (ingreso == null) {
            throw new NegocioException("Fecha ingreso no puede ser null");
        }
        this.sim = simu;
        this.operario = op;
        this.ingreso = ingreso;
    }

    @Override
    public void ejecutar() {
        operario.setPresente(true);
        operario.setOcupado(false);
        sim.addEvento(new EgresoOperario(sim, operario, operario.getEmpleado().getProxEgreso(this.getTiempo())));
    }
}
