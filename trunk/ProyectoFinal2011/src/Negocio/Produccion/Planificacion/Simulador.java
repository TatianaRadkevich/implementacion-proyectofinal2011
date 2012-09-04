/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion;

import BaseDeDatos.Administracion.EmpleadoBD;
import Negocio.Administracion.AsignacionesDias;
import Negocio.Administracion.Empleado;
import Negocio.Produccion.Planificacion.Evento.*;
import Negocio.Produccion.Planificacion.Recursos.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dicsys
 */
public class Simulador {

    private Recursos recursos;
    private Jefe capo;
    private Date tiempo;
    private ArrayList<Evento> eventos;

    public void iniciar() {
        
        cargarRecursos();

        do
        {
            eventos.remove(0).ejecutar();
            while(capo.hayTareas())
               addEvento(new FinTarea(getTiempoActual(),capo.getProximaTarea()));

        }while(eventos.isEmpty()==false);

    }
    
    private void cargarRecursos()
    {
        recursos =new Recursos();
        List<Empleado> le=EmpleadoBD.listarEmpleado();
        for(Empleado e: le)
        {
            if(e.getAsignacionHorariaVigente()!=null) 
            {
                recursos.addOperario(e);
                this.addEvento(getProximoIngresoEgreso(e));
            }
        }
    }
    
    private Evento getProximoIngresoEgreso(Empleado e)
    {
        throw new UnsupportedOperationException();
//        for(AsignacionesDias ad:e.getAsignacionHorariaVigente().getHorario().getTAsignacionesDiases())
//        {
//
//
//        }
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
