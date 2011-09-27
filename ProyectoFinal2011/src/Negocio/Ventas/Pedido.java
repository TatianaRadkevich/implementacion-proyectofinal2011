package Negocio.Ventas;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Produccion.PlanProduccion;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;

/**
 * TPedidos generated by hbm2java
 */
@Entity
@Table(name = "T_PEDIDOS", schema = "dbo", catalog = "Ramaty")
public class Pedido implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_PEDIDO", unique = true, nullable = false, precision = 8, scale = 0)
    private int idPedido;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TPEDIDO")//, nullable=false)
    private TipoPedido TTpedido;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE")//, nullable=false)
    private Cliente TClientes;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EPEDIDO")//, nullable=false)
    private EstadoPedido TEpedido;
//    @Column(name = "ENTREGA_MATERIAL")
//    private Boolean entregaMaterial;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_ESTIMADA_ENTREGA")
    private Date fecHoraEstimadaEntrega;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_GENERACION")
    private Date fecHoraGeneracion;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_CLI_REC")
    private Date fechaClienteRecep;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_REAL_ENTREGA")
    private Date fecHoraRealEntrega;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_SOLICITADA")
    private Date fecNecesidad;
    @Column(name = "PRIORIDAD")
    private byte prioridad;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_BAJA")
    private Date fecBaja;
    @Column(name = "MOTIVO_BAJA", length = 100)
    private String motivoBaja;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TPedidos")
    private Set<PlanProduccion> TPlanesProduccions = new HashSet<PlanProduccion>(0);
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TPedidos")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<DetallePedido> TDetallesPedidos = new HashSet<DetallePedido>(0);

    public Pedido() {
    }

    public Pedido(int idPedido, TipoPedido TTpedido, Cliente TClientes, EstadoPedido TEpedido, Date fecHoraEstimadaEntrega, Date fecHoraGeneracion, Date fecHoraRealEntrega, Date fecSolicitada, byte prioridad) {
        this.idPedido = idPedido;
        this.TTpedido = TTpedido;
        this.TClientes = TClientes;
        this.TEpedido = TEpedido;
        this.fecHoraEstimadaEntrega = fecHoraEstimadaEntrega;
        this.fecHoraGeneracion = fecHoraGeneracion;
        this.fecHoraRealEntrega = fecHoraRealEntrega;
        this.fecNecesidad = fecSolicitada;
        this.prioridad = prioridad;
    }

    public Pedido(int idPedido, TipoPedido TTpedido, Cliente TClientes, EstadoPedido TEpedido, Date fecHoraEstimadaEntrega, Date fecHoraGeneracion, Date fecHoraRealEntrega, Date fecSolicitada, byte prioridad, Set<DetallePedido> TDetallesPedidos) {
        this.idPedido = idPedido;
        this.TTpedido = TTpedido;
        this.TClientes = TClientes;
        this.TEpedido = TEpedido;

        this.fecHoraEstimadaEntrega = fecHoraEstimadaEntrega;
        this.fecHoraGeneracion = fecHoraGeneracion;
        this.fecHoraRealEntrega = fecHoraRealEntrega;
        this.fecNecesidad = fecSolicitada;
        this.prioridad = prioridad;
        //this.TPlanesProduccions = TPlanesProduccions;
        this.TDetallesPedidos = TDetallesPedidos;
    }

    /*******************************************************/
    public int getIdPedido() {
        return this.idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public TipoPedido getTipoPedido() {
        return this.TTpedido;
    }

    public void setTipoPedido(TipoPedido tipo) {
        this.TTpedido = tipo;
    }

    public Cliente getCliente() {
        return this.TClientes;
    }

    public void setCliente(Cliente cliente) {
        this.TClientes = cliente;
    }

    public EstadoPedido getEstadoPedido() {
        return this.TEpedido;
    }

    public void setEstadoPedido(EstadoPedido estado) {
        this.TEpedido = estado;
    }

    public Date getFechaEstimadaEntrega() {
        return this.fecHoraEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(Date fecha) {
        this.fecHoraEstimadaEntrega = fecha;
    }

    public Date getFechaGeneracion() {
        return this.fecHoraGeneracion;
    }

    public void setFechaGeneracion(Date fecha) {
        this.fecHoraGeneracion = fecha;
    }

    public Date getFechaRealEntrega() {
        return this.fecHoraRealEntrega;
    }

    public void setFechaRealEntrega(Date fecha) {
        this.fecHoraRealEntrega = fecha;
    }

    public Date getFechaNecesidad() {
        return this.fecNecesidad;
    }

    public void setFechaNecesidad(Date fecha) {
        this.fecNecesidad = fecha;
    }

    public byte getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(byte prioridad) {
        this.prioridad = prioridad;
    }

    public PlanProduccion getPlanProduccion() {
        if (this.TPlanesProduccions.isEmpty()) {
            return null;
        } else {
            return this.TPlanesProduccions.iterator().next();
        }
    }

    public void setPlanProduccion(PlanProduccion planes) {
        this.TPlanesProduccions.clear();
        this.TPlanesProduccions.add(planes);
    }

    public List<DetallePedido> getDetallePedido() {
        return new ArrayList<DetallePedido>(TDetallesPedidos);
    }

    public void setDetallePedido(List<DetallePedido> detalle) {
        this.TDetallesPedidos.clear();
        for (DetallePedido dt : detalle) {
            dt.setPedido(this);
            TDetallesPedidos.add(dt);
        }

    }

    public Date getFecBaja() {
        return this.fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public String getMotivoBaja() {
        return this.motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Date getFechaClienteRecep() {
        return fechaClienteRecep;
    }

    public void setFechaClienteRecep(Date fechaClienteRecep) {
        this.fechaClienteRecep = fechaClienteRecep;
    }
}
