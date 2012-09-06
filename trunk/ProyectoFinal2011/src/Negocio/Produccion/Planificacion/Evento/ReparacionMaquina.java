/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion.Evento;

import Negocio.Exceptiones.NegocioException;
import Negocio.Produccion.Planificacion.Recursos.MaquinaPlan;
import Negocio.Produccion.Planificacion.Recursos.OperarioPlan;
import Negocio.Produccion.Planificacion.Simulador;
import java.util.Date;

/**
 *
 * @author Dicsys
 */
public class ReparacionMaquina extends Evento {

    private MaquinaPlan maq;
    private Date reparacion;
    private Simulador sim;

    public ReparacionMaquina(Simulador simu, MaquinaPlan maq, Date reparacion) {
        if(reparacion==null)
            throw new NegocioException("Fecha egreso no puede ser null");
        this.sim = simu;
        this.maq = maq;
        this.reparacion = reparacion;
    }

    @Override
    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
