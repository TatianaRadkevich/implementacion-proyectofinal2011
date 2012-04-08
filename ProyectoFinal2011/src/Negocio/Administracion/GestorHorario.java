/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Administracion;

import BaseDeDatos.Administracion.HorarioBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Administracion.HorarioAdministracionPantalla;
import Presentacion.Mensajes;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorHorario {

    /*************************************************************************/
    private interface gestor {

        public void iniciarCU();

        public void validar(Horarios tmh) throws ExceptionGestor;

        public void ejecutarCU(Horarios tmh) throws ExceptionGestor;
    }
    /*************************************************************************/
    private gestor comportamiento;
    protected HorarioAdministracionPantalla interfaz;
    protected Horarios elemento;

    public GestorHorario() {
        this.interfaz = new HorarioAdministracionPantalla(this);
    }

    public void administar() {
        interfaz.setVisible(true);
    }

    public void iniciarNuevo() {
        elemento = new Horarios();
        comportamiento = nuevo;
        comportamiento.iniciarCU();
    }

    public void iniciarModificar(Horarios tmh) {
        elemento = tmh;
        comportamiento = Modificar;
        comportamiento.iniciarCU();
    }

    public void iniciarBaja(Horarios tmh) {
        elemento = tmh;
        comportamiento = Baja;
        comportamiento.iniciarCU();
    }

    public void ejecutarCU(Horarios tmh) throws ExceptionGestor {
        comportamiento.ejecutarCU(tmh);
    }

    public void validar(Horarios tmh) throws ExceptionGestor {
        comportamiento.validar(tmh);
    }

    public void finalizarCU() {
        interfaz.limpiar();
        interfaz.habilitarCampos(false);
        interfaz.cargarLista();
    }

    /*************************************************************************/

    public Horarios getElemento() {
        return elemento;
    }

    public List<Horarios> listarHorarios() {
        return HorarioBD.listarHorarios();
    }

    public List<Dia> listarDias() {
        return HorarioBD.listarDias();
    }

    /*************************************************************************/
    private gestor nuevo = new gestor() {

        public void iniciarCU() {
            interfaz.limpiar();
            interfaz.habilitarCampos(true);
            //interfaz.habilitarConfirmacion(true);
            //interfaz.habilitarBaja(false, null, "");
        }

        public void validar(Horarios tmh) throws ExceptionGestor {
            String mensage = "";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";//

            if (mensage.isEmpty() == false) {
                throw new ExceptionGestor("Problemas:" + mensage);
            }

        }

        public void ejecutarCU(Horarios tmh) throws ExceptionGestor {
            validar(tmh);
            //mh.setEstadoMaquina(null);
            HorarioBD.guardar(tmh);
            Mensajes.mensajeInformacion("El Horario \"" + tmh.getNombre() + "\" ha sido guardado exitosamente.");
            finalizarCU();
        }
    };
    /*************************************************************************/
    private gestor Modificar = new gestor() {

        public void iniciarCU() {
            if (elemento == null) {
                throw new RuntimeException("GestorHorariosModificar: Se debe definir el Horario a modificar");
            }
            interfaz.cargar(elemento);
            interfaz.habilitarCampos(true);           
        }

        public void validar(Horarios tmh) throws ExceptionGestor {
            String mensage = "";

            if (mensage.isEmpty() == false) {
                throw new ExceptionGestor("Problemas:" + mensage);
            }
        }

        public void ejecutarCU(Horarios tmh) throws ExceptionGestor {
            validar(tmh);
            HorarioBD.modificar(tmh);
            Mensajes.mensajeInformacion("El Horario \"" + tmh.getNombre() + "\" ha sido modificado exitosamente.");
            finalizarCU();
        }
    };
    /*************************************************************************/
    private gestor Baja = new gestor() {

        public void iniciarCU() {    
            if (elemento == null) {
                throw new RuntimeException("GestorHorariosBaja: Se debe definir el Horario a dar de baja");
            }
            interfaz.cargar(elemento);
            interfaz.habilitarCampos(false);
        }

        public void validar(Horarios tmh) throws ExceptionGestor {
            String mensage = "";
            if (mensage.isEmpty() == false) {
                throw new ExceptionGestor("Problemas:" + mensage);
            }
        }

        public void ejecutarCU(Horarios tmh) throws ExceptionGestor {
            validar(tmh);           
            HorarioBD.elminiar(tmh);
            Mensajes.mensajeInformacion("El Horario \"" + tmh.getNombre() + "\" ha sido eliminado exitosamente.");
            finalizarCU();
        }
    };
    
}
