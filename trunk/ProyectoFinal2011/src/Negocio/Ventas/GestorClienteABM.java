/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Ventas;

import BaseDeDatos.Ventas.*;
import Negocio.Exceptiones.NegocioException;
import Presentacion.Mensajes;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorClienteABM {

    protected Cliente cliente;
    protected String motivoBaja;
    protected boolean correcto=false;

    public GestorClienteABM(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public List<TipoCliente> getTipoClientes() {
        return TipoClienteBD.listarTiposClientes();
    }

    public static List<Cliente> listarClientes(){
    return ClienteBD.listarClientes();
    }

    public abstract void aceptar() throws NegocioException;

    // <editor-fold defaultstate="collapsed" desc="Iniciadores">
    public static GestorClienteABM getGestorClienteAlta() {
        return new GestorClienteABM(new Cliente()) {

            @Override
            public void aceptar() throws NegocioException {
                this.getCliente().guardar();
                Mensajes.mensajeInformacion("El Cliente \"" + this.getCliente().getRazonSocial() + "\" ha sido guardado exitosamente.");
                this.correcto=true;
            }
        };
    }

    public boolean isCorrecto() {
        return correcto;
    }


    public static GestorClienteABM getGestorClienteModificar(Cliente cli) {
        return new GestorClienteABM(cli) {

            @Override
            public void aceptar() throws NegocioException {
                this.getCliente().guardar();
                Mensajes.mensajeInformacion("El Cliente \"" + this.getCliente().getRazonSocial() + "\" ha sido modificado exitosamente.");
            }
        };

    }

    public static GestorClienteABM getGestorClienteBaja(Cliente cli) {
        return new GestorClienteABM(cli) {

            @Override
            public void aceptar() throws NegocioException {
                this.getCliente().eliminar(motivoBaja);
                Mensajes.mensajeInformacion("El Cliente \"" + this.getCliente().getRazonSocial() + "\" ha sido eliminado exitosamente.");

            }
        };

    }

    public void cancelar() {
        this.correcto=false;
        this.cliente=null;
    }
    // </editor-fold>
}
