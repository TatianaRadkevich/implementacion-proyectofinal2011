package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Administracion.Empleado;
import Negocio.Administracion.Cobro;
import Negocio.Administracion.DetalleFactura;
import Negocio.Ventas.Pedido;
import Presentacion.Utilidades;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.rmi.CORBA.Util;
import org.hibernate.annotations.Cascade;

/**
 * TFacturas generated by hbm2java
 */
@Entity
@Table(name = "T_FACTURAS", schema = "dbo", catalog = "Ramaty")
public class Factura implements java.io.Serializable {

    public static int getUltimoNro() {
        return PedidoBD.getUltimoNroFactura();
    }

    @Id
    @GeneratedValue
    @Column(name = "ID_FACTURA", unique = true, nullable = false, precision = 8, scale = 0)
    private int idFactura;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", nullable = false)
    private Empleado TEmpleados;
    //
    @Column(name = "DESCUENTO", precision = 6, scale = 4)
    private BigDecimal descuento;
    //
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_FACTURA", nullable = false, length = 23)
    private Date fecFactura;
    //
    @OneToOne(mappedBy="TFacturas")
    private Pedido pedido;
    //
    @Column(name = "NUMERO", nullable = false, precision = 8, scale = 0)
    private int numero;
    //
    @Column(name = "RECARGO", precision = 6, scale = 4)
    private BigDecimal recargo;
    //
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TFacturas")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<DetalleFactura> TDetallesFacturas = new HashSet<DetalleFactura>(0);
    //
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TFacturas")
    private Set<Cobro> TCobroses = new HashSet<Cobro>(0);

    public Factura() {
    }

    public Factura(int idFactura, Empleado TEmpleados, Date fecFactura, int numero) {
        this.idFactura = idFactura;
        this.TEmpleados = TEmpleados;
        this.fecFactura = fecFactura;
        this.numero = numero;
    }

    public Factura(int idFactura, Empleado TEmpleados, BigDecimal descuento, Date fecFactura, int numero, BigDecimal recargo, Set<DetalleFactura> TDetallesFacturas, Set<Cobro> TCobroses) {
        this.idFactura = idFactura;
        this.TEmpleados = TEmpleados;
        this.descuento = descuento;
        this.fecFactura = fecFactura;
        this.numero = numero;
        this.recargo = recargo;
        this.TDetallesFacturas = TDetallesFacturas;
        this.TCobroses = TCobroses;
    }

    public int getIdFactura() {
        return this.idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Empleado getTEmpleados() {
        return this.TEmpleados;
    }

    public void setTEmpleados(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }

    public BigDecimal getDescuento() {
        return this.descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Date getFecFactura() {
        return this.fecFactura;
    }

    public void setFecFactura(Date fecFactura) {
        this.fecFactura = fecFactura;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        pedido.setTFacturas(this);
        this.pedido = pedido;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public BigDecimal getRecargo() {
        return this.recargo;
    }

    public void setRecargo(BigDecimal recargo) {
        this.recargo = recargo;
    }

    public List<DetalleFactura> getDetalleFactura() {
        return new ArrayList<DetalleFactura>(this.TDetallesFacturas);

    }

    public void setTDetallesFacturas(List<DetalleFactura> detalle) {

        for (DetalleFactura df : detalle) {
            df.setTFacturas(this);
        }

        this.TDetallesFacturas.clear();
        this.TDetallesFacturas.addAll(detalle);
    }

    public List<Cobro> getTCobroses() {
        return new ArrayList<Cobro>(this.TCobroses);
    }

    public void setTCobroses(List<Cobro> TCobroses) {
        this.TCobroses.clear();
        for (Cobro dt : TCobroses) {
            dt.setTFacturas(this);
            TCobroses.add(dt);
        }

    }

    public void guardar() {
        this.setFecFactura(Utilidades.getFechaActual());
       HibernateUtil.guardarObjeto(this);
    }
}
