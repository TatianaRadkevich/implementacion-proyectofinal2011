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
public class IngresoOperario extends Evento {

    private Operario operario;
    private Date ingreso;
    private Simulador sim;

    public IngresoOperario(Simulador simu, Operario op, Date ingreso) {
        if (ingreso == null) {
            throw new NegocioException("Fecha ingreso no puede ser null");
        }
        this.sim = simu;
        this.operario = op;
        this.ingreso = ingreso;
    }

    @Override
    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
