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
public class EgresoOperario extends Evento {

    private OperarioPlan operario;
    private Date egreso;
    private Simulador sim;

    public EgresoOperario(Simulador simu, OperarioPlan op, Date egreso) {
        if(egreso==null)
            throw new NegocioException("Fecha egreso no puede ser null");
        this.sim = simu;
        this.operario = op;
        this.egreso = egreso;
    }

    @Override
    public void ejecutar() {
        operario.setPresente(false);
        sim.addEvento( new IngresoOperario(sim, operario, operario.getEmpleado().getProxIngreso(this.getTiempo())));
    }
}
