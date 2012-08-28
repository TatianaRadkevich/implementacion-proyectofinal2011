/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion.Planificacion.Evento;

import Negocio.Produccion.Planificacion.Tarea;
import java.util.Date;

/**
 *
 * @author Dicsys
 */
public class FinTarea  extends Evento{

    private Tarea tarea;

    public FinTarea(Date tiempo,Tarea tarea)
    {
        super(tiempo);
        this.tarea=tarea;
    }

    @Override
    public void ejecutar() {
        
    }

}
