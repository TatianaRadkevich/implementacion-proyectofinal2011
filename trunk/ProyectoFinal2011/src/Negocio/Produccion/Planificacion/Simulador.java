/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion;

import Negocio.Produccion.Planificacion.Evento.EgresoOperario;
import Negocio.Produccion.Planificacion.Evento.Evento;
import Negocio.Produccion.Planificacion.Evento.FinTarea;
import Negocio.Produccion.Planificacion.Evento.IngresoOperario;
import Negocio.Produccion.Planificacion.Recursos.Operario;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dicsys
 */
public class Simulador {

    private Jefe capo;
    private Date tiempo;
    private ArrayList<Evento> eventos;

    public void iniciar() {

        do
        {
            eventos.remove(0).ejecutar();
            while(capo.hayTareas())
               addEvento(new FinTarea(getTiempoActual(),capo.getProximaTarea()));

        }while(eventos.isEmpty()==false);

    }

    public Date getTiempoActual() {
        return tiempo;
    }


    public void ingresoOperario(Operario op)
    {
        addEvento(new EgresoOperario(getTiempoActual(),op));
    }

    public void egresoOperario(Operario op)
    {
        addEvento(new IngresoOperario(getTiempoActual(),op));
    }

    public void addEvento(Evento e) {
        int index = 0;
        for (; index < eventos.size(); index++) {
            if (e.getTiempo().compareTo(eventos.get(index).getTiempo()) < 0) {
                break;
            }
        }
        eventos.add(index, e);
    }

    public Evento getEvento() {
        if (eventos.isEmpty()) {
            return null;
        }
        return eventos.remove(0);
    }
}
