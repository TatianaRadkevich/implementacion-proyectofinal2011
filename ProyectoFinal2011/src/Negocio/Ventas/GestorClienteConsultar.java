/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Ventas;

import BaseDeDatos.Ventas.*;
import Negocio.Exceptiones.NegocioException;
import Presentacion.Mensajes;
import Presentacion.ZLinkers.validationTag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class GestorClienteConsultar {

    //filtros
    // <editor-fold defaultstate="collapsed" desc="comment">
// </editor-fold>
    protected String cuit = "", razonSocial = "", nombreResponsable = "", apellidoResponsable = "";
    protected boolean vigentes = true, cancelados = false, autoSearch = false;
    protected Cliente selecionado;
    protected List<Cliente> resultado;

    public GestorClienteConsultar() {
        this.buscar();
    }

    public List<Cliente> getResultado() {
        return resultado;
    }

    public Cliente getClienteSelecionado() {
        return selecionado;
    }

    public void setClienteSelecionado(Cliente selecionado) {
        this.selecionado = selecionado;
    }


    // <editor-fold defaultstate="collapsed" desc="set/get filtros">
    @validationTag(length = 50)
    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
        filterChange();
    }

    public boolean isCancelados() {
        return cancelados;
    }

    public void setCancelados(boolean cancelados) {
        this.cancelados = cancelados;
        filterChange();
    }

    @validationTag(length = 20)//cuit
    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
        filterChange();
    }

    @validationTag(length = 50)
    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
        filterChange();
    }

    @validationTag(length = 50)
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
        filterChange();
    }

    public boolean isVigentes() {
        return vigentes;
    }

    public void setVigentes(boolean vigentes) {
        this.vigentes = vigentes;
        filterChange();
    }

    // </editor-fold>
    public void filterChange() {
        if (autoSearch == true) {
            buscar();
        }
    }

    public void buscar() {
        this.resultado = ClienteBD.getClientes(cuit, razonSocial, nombreResponsable, apellidoResponsable, vigentes, cancelados);
    }

    public static GestorClienteConsultar getGestorClienteAlta() {
        return new GestorClienteConsultar();
    }
}
