package Negocio.Compras;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Compras.DetalleOrdenCompra;
import java.util.Date;
import java.util.HashSet;
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
 * TOrdenesCompra generated by hbm2java
 */
@Entity
@Table(name = "T_ORDENES_COMPRA", schema = "dbo", catalog = "Ramaty")
public class OrdenCompra implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_ORDEN_COMPRA", unique = true, nullable = true, precision = 8, scale = 0)
    private int idOrdenCompra;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROVEEDOR", nullable = true)
    private Proveedor TProveedores;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EORDEN_COMPRA", nullable = true)
    private EstadoOrdenCompra TEordenCompra;
    @Column(name = "CODIGO", nullable = true, length = 2)
    private String codigo;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_GENERACION", nullable = true, length = 23)
    private Date fecGeneracion;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_RECEPCION", nullable = true, length = 23)
    private Date fecRecepcion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TOrdenesCompra")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<DetalleOrdenCompra> TDetallesOrdenCompras = new HashSet<DetalleOrdenCompra>(0);
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TOrdenesCompra")
    private Set<Reclamo> TReclamoses = new HashSet<Reclamo>(0);

    public OrdenCompra() {
    }

    public OrdenCompra(int idOrdenCompra, Proveedor TProveedores, EstadoOrdenCompra TEordenCompra, String codigo, Date fecGeneracion, Date fecRecepcion) {
        this.idOrdenCompra = idOrdenCompra;
        this.TProveedores = TProveedores;
        this.TEordenCompra = TEordenCompra;
        this.codigo = codigo;
        this.fecGeneracion = fecGeneracion;
        this.fecRecepcion = fecRecepcion;
    }

    public OrdenCompra(int idOrdenCompra, Proveedor TProveedores, EstadoOrdenCompra TEordenCompra, String codigo, Date fecGeneracion, Date fecRecepcion, Set<DetalleOrdenCompra> TDetallesOrdenCompras, Set<Reclamo> TReclamoses) {
        this.idOrdenCompra = idOrdenCompra;
        this.TProveedores = TProveedores;
        this.TEordenCompra = TEordenCompra;
        this.codigo = codigo;
        this.fecGeneracion = fecGeneracion;
        this.fecRecepcion = fecRecepcion;
        this.TDetallesOrdenCompras = TDetallesOrdenCompras;
        this.TReclamoses = TReclamoses;
    }

    public int getId() {
        return this.idOrdenCompra;
    }

    public void setId(int idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Proveedor getProveedor() {
        return this.TProveedores;
    }

    public void setProveedor(Proveedor TProveedores) {
        this.TProveedores = TProveedores;
    }

    public EstadoOrdenCompra getEstado() {
        return this.TEordenCompra;
    }

    public void setEstado(EstadoOrdenCompra TEordenCompra) {
        this.TEordenCompra = TEordenCompra;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecGeneracion() {
        return this.fecGeneracion;
    }

    public void setFecGeneracion(Date fecGeneracion) {
        this.fecGeneracion = fecGeneracion;
    }

    public Date getFecRecepcion() {
        return this.fecRecepcion;
    }

    public void setFecRecepcion(Date fecRecepcion) {
        this.fecRecepcion = fecRecepcion;
    }

    public Set<DetalleOrdenCompra> getDetalle() {
        return this.TDetallesOrdenCompras;
    }

    public void setDetalle(Set<DetalleOrdenCompra> TDetallesOrdenCompras) {
        this.TDetallesOrdenCompras = TDetallesOrdenCompras;
    }

    public Set<Reclamo> getReclamos() {
        return this.TReclamoses;
    }

    public void setReclamos(Set<Reclamo> TReclamoses) {
        this.TReclamoses = TReclamoses;
    }
}
