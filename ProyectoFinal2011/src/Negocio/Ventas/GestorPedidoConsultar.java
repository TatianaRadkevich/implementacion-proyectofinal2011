/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Ventas;

import BaseDeDatos.Ventas.*;
import Negocio.Exceptiones.NegocioException;
import Presentacion.Mensajes;
import Presentacion.Utilidades;
import Presentacion.ZLinkers.ZLTag;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class GestorPedidoConsultar {

    //filtros
    // <editor-fold defaultstate="collapsed" desc="comment">
// </editor-fold>
    protected String cuit = "", razonSocial = "",nroPedido="";
    protected boolean vigentes = true, cancelados = false;
    private Date fechaHasta=Utilidades.getFechaActual();
    private Date fechaDesde=Utilidades.agregarTiempoFecha(fechaHasta, 0, 0, -1);

    protected Pedido selecionado;
    protected List<Pedido> resultado;

    public GestorPedidoConsultar() {
        this.buscar();
    }

    public List<Pedido> getResultado() {
        return resultado;
    }

    public Pedido getClienteSelecionado() {
        return selecionado;
    }

    public void setClienteSelecionado(Pedido selecionado) {
        this.selecionado = selecionado;
    }

     // <editor-fold defaultstate="collapsed" desc="set/get filtros">
    public boolean isCancelados() {
        return cancelados;
    }

    public void setCancelados(boolean cancelados) {
        this.cancelados = cancelados;
    }
    @ZLTag(length= 20)
    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    @ZLTag(precision = 8)
    public String getNroPedido() {
        return nroPedido;
    }

    public void setNroPedido(String nroPedido) {
        this.nroPedido = nroPedido;
    }
     @ZLTag(length= 50)
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public boolean isVigentes() {
        return vigentes;
    }

    public void setVigentes(boolean vigentes) {
        this.vigentes = vigentes;
    }

    // </editor-fold>
    public List<Pedido> buscar() {
        return this.resultado = PedidoBD.getPedidos(razonSocial, cuit,  nroPedido, fechaDesde,fechaHasta, vigentes, cancelados);
    }
}
