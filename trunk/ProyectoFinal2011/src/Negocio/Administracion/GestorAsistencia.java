/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Administracion;

import BaseDeDatos.Administracion.AsistenciaEmpleadoBD;
import BaseDeDatos.Administracion.EmpleadoBD;
import Negocio.Exceptiones.NegocioException;
import Presentacion.Mensajes;
import Presentacion.Utilidades;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorAsistencia {
    
    private AsistenciaEmpleado asistencia;

    public void iniciarRegistrarInbreso(Empleado e) throws NegocioException {
        if (e == null) {
            throw new NegocioException("Debe seleccionar un empleado");
        }

        if (e.getUltimaAsistencia() != null) {
            String ultimaAsist = Utilidades.parseFecha(e.getUltimaAsistencia().getFecAsistencia());
            String fechaActual = Utilidades.parseFecha(Utilidades.getFechaActual());
            if (ultimaAsist.equals(fechaActual)) {
                throw new NegocioException("Este empleado ya tiene una asistencia");
            }

            if (e.getUltimaAsistencia().getHoraEgreso().trim().equals("")) {
                throw new NegocioException("Este empleado todabía continúa en el establecimiento");
            }
        }

        if (this.asistencia != null) {
            String m = "Aun no guardo los datos, si prosigue perdera todos los cambios.\n¿Desea continuar?";
            if (Mensajes.mensajeConfirmacionGenerico(m) == false) {
                throw new NegocioException("Operacion cancelada");
            }
        }

        this.asistencia = new AsistenciaEmpleado();
        this.asistencia.setEmpleado(e);
        this.asistencia.setFecAsistencia(Utilidades.getFechaActual());
        this.asistencia.setHoraIngreso(Utilidades.parseHora(Utilidades.getFechaActual()));
        this.asistencia.setObservIngreso("");
    }

    public void iniciarRegistrarEgreso(Empleado e) {
        if (e == null) {
            throw new NegocioException("Debe seleccionar un empleado");
        }

        if (e.getUltimaAsistencia() != null) {

            if (e.getUltimaAsistencia().getHoraEgreso().trim().equals("") == false) {
                Mensajes.mensajeErrorGenerico("Este empleado todabía no ingreso al establecimiento");
                return;
            }
        } else {
            Mensajes.mensajeErrorGenerico("Este empleado todabía no ingreso al establecimiento");
            return;
        }

        if (this.asistencia != null) {
            String m = "Aun no guardo los datos, si prosigue perdera todos los cambios.\n¿Desea continuar?";
            if (Mensajes.mensajeConfirmacionGenerico(m) == false) {
                throw new NegocioException("Operacion cancelada");
            }
        }

        this.asistencia = e.getUltimaAsistencia();
        this.asistencia.setHoraEgreso(Utilidades.parseHora(Utilidades.getFechaActual()));
        this.asistencia.setObservEgreso("");
    }

    public void aceptar(String observaciones) throws NegocioException {

        if (this.asistencia == null) {
            throw new NegocioException("Gestor Asistencia: Estado Inválido");
        }
        if(this.asistencia.getHoraEgreso().trim().equals(""))
            this.asistencia.setObservIngreso(observaciones);
        else
            this.asistencia.setObservEgreso(observaciones);
        AsistenciaEmpleadoBD.guardar(asistencia);
         asistencia=null;
    }

    public void cancelar() {
        asistencia=null;
    }

    public void guardarAsistencia(AsistenciaEmpleado asistencia) {
        AsistenciaEmpleadoBD.guardar(asistencia);
    }

    public static List<Empleado> traerEmpleadosVigentes() {
        return EmpleadoBD.getEmpleadosVigentes();
    }

    public AsistenciaEmpleado getAsistencia()
    {
        return this.asistencia;
    }
}
