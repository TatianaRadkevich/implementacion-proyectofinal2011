/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import Negocio.Produccion.Producto;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ivan
 */
public class Pedido {

    private Cliente cliente;
    private ArrayList<DetallePedido> detalle;
    private boolean entregaMaterial;
    private int numero;
    private int prioridad;
    private Date fechaGeneracion;
    private Date fechaNecesidad;
    private Date fechaEntrega;
    private Date fechaEstimada;
    private TipoPedido tipo;

    public Pedido() {
        detalle=new ArrayList<DetallePedido>();
    }

    public Pedido(Cliente cliente, ArrayList<DetallePedido> detalle, boolean entregaMaterial, int numero, int prioridad, Date fechaGeneracion, TipoPedido tipo) {
        this.cliente = cliente;
        this.detalle = detalle;
        this.entregaMaterial = entregaMaterial;
        this.numero = numero;
        this.prioridad = prioridad;
        this.fechaGeneracion = fechaGeneracion;
        this.tipo = tipo;
    }



    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<DetallePedido> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<DetallePedido> detalle) {
        this.detalle = detalle;
    }

    public boolean isEntregaMaterial() {
        return entregaMaterial;
    }

    public void setEntregaMaterial(boolean entregaMaterial) {
        this.entregaMaterial = entregaMaterial;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Date getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
    }



}
