/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Produccion.MaquinaBD;
import Negocio.Administracion.AsignacionesDias;
import Negocio.Administracion.Empleado;
import Negocio.Compras.Material;
import Negocio.Compras.MaterialesXProveedor;
import Negocio.Exceptiones.NegocioException;
import Negocio.Produccion.MaquinaParticular;
import Negocio.Produccion.Planificacion.Evento.*;
import Negocio.Produccion.Planificacion.Recursos.*;
import Negocio.Produccion.ProblemasMhp;
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
                addEvento(new FinTarea(this, capo.getProximaTarea(),getTiempoActual()));
            }

        } while (eventos.isEmpty() == false);

    }

    private void cargarRecursos() {
        recursos = new Recursos();

        //Empleador
        for (Empleado e : EmpleadoBD.listarEmpleadosVigentes()) {
            try {
                OperarioPlan op;
                Evento ev;

                if (e.isPresente(tiempo)) {
                    op = new OperarioPlan(e, true);
                    ev = new EgresoOperario(this, op, e.getProxEgreso(tiempo));
                } else {
                    op = new OperarioPlan(e, false);
                    ev = new IngresoOperario(this, op, e.getProxIngreso(tiempo));
                }
                this.addEvento(ev);
                this.recursos.addOperario(op);

            } catch (NegocioException ne) {
            }
        }

        //Maquinas
        for (MaquinaParticular mp : MaquinaBD.listarMaquinasVigentes()) {
            try {

                MaquinaPlan maq;
                Evento ev;
                ProblemasMhp p=mp.getProblemaMaquina(tiempo);
                if (p==null) {//por el tema de problemas y esas cosas
                    maq = new MaquinaPlan(mp, true);
                } else {
                    maq = new MaquinaPlan(mp, false);
                    Date d=(p.getFecHoraRealSolucion()==null)?p.getFecHoraRealSolucion():p.getFecHoraEstimadaSolucion();
                   this.addEvento(new ReparacionMaquina(this, maq, d));
                }
                this.recursos.addMaquina(maq);

            } catch (NegocioException ne) {
            }
        }

        //materiales

        for (Material mat : MaterialBD.listarMaterialesVigentes()) {
            try {              
                this.recursos.addMaterial(new MaterialPlan(mat));

            } catch (NegocioException ne) {
            }
        }

        //Herramientas


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
