/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion.Evento;

import Negocio.Exceptiones.NegocioException;
import Negocio.Produccion.Planificacion.Recursos.Operario;
import Negocio.Produccion.Planificacion.Simulador;
import java.util.Date;

/**
 *
 * @author Dicsys
 */
public class EgresoOperario extends Evento {

    private Operario operario;
    private Date egreso;
    private Simulador sim;

    public EgresoOperario(Simulador simu, Operario op, Date egreso) {
        if(egreso==null)
            throw new NegocioException("Fecha egreso no puede ser null");
        this.sim = simu;
        this.operario = op;
        this.egreso = egreso;
    }

    @Override
    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
