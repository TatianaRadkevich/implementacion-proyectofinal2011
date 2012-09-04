/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.Produccion.MaquinaBD;
import Negocio.Administracion.AsignacionesDias;
import Negocio.Administracion.Empleado;
import Negocio.Exceptiones.NegocioException;
import Negocio.Produccion.MaquinaParticular;
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

        do {
            eventos.remove(0).ejecutar();
            while (capo.hayTareas()) {
                addEvento(new FinTarea(getTiempoActual(), capo.getProximaTarea()));
            }

        } while (eventos.isEmpty() == false);

    }

    private void cargarRecursos() {
        recursos = new Recursos();

        //Empleador
        for (Empleado e : EmpleadoBD.listarEmpleadosVigentes()) {
            try {
                Operario op;
                Evento ev;

                if (e.isPresente(tiempo)) {
                    op = new Operario(e, true);
                    ev = new EgresoOperario(this, op, e.getEgreso(tiempo));
                } else {
                    op = new Operario(e, false);
                    ev = new IngresoOperario(this, op, e.getIngreso(tiempo));
                }
                this.addEvento(ev);
                this.recursos.addOperario(op);

            } catch (NegocioException ne) {
            }
        }

        //Maquinas
        for (MaquinaParticular mp : MaquinaBD.listarMaquinasVigentes()) {
            try {

                Maquina maq;
                Evento ev;

//                if (mp.isOperativa(tiempo)) {//por el tema de problemas y esas cosas
//                    op = new Operario(e, true);
//                    ev = new EgresoOperario(this, op, e.getEgreso(tiempo));
//                } else {
//                    op = new Operario(e, false);
//                    ev = new IngresoOperario(this, op, e.getIngreso(tiempo));
//                }
//
//                this.addEvento(ev);
//                this.recursos.addOperario(op);


            } catch (NegocioException ne) {
            }
        }


    }

    public Date getTiempoActual() {
        return tiempo;
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
