package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.Administracion.FacturaBD;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Administracion.Empleado;
import Negocio.Administracion.Cobro;
import Negocio.Administracion.DetalleFactura;
import Negocio.Exceptiones.NegocioException;
import Negocio.UbicacionGeografica.Domicilio;
import Negocio.Ventas.Pedido;
import Presentacion.Utilidades;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        return FacturaBD.getUltimoNroFactura();
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PEDIDO")//, nullable=false)
    private Pedido pedido;
    //
    @Column(name = "NUMERO", nullable = false, precision = 8, scale = 0)
    private int numero;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_BAJA")
    private Date fecBaja;
    //
    @Column(name = "MOTIVO_BAJA", length = 100)
    private String motivoBaja;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EFACTURA")//, nullable=false)
    private EstadoFactura TEFactura;
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
        this.TDetallesFacturas = TDetallesFacturas;
        this.TCobroses = TCobroses;
    }

    
    public int getId() {
        return this.idFactura;
    }

    public Empleado getEmpleado() {
        return this.TEmpleados;
    }

    public void setEmpleado(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }

    public BigDecimal getDescuentoPorcentaje() {
        return this.descuento;
    }

    public void setDescuentoPorcentaje(BigDecimal descuento) {
        this.descuento = descuento;
    }


    public float getDescuentoMonto() {
        float resultado = (this.getDescuentoPorcentaje().floatValue() * this.getTotalBruto()) / 100;
        return new BigDecimal(resultado).round(new MathContext(2, RoundingMode.UP)).floatValue();
    }

    public float getTotalBruto() {
        float total = 0;
        for (DetalleFactura df : this.getDetalleFactura()) {
            total += df.getCantidad() * df.getPrecio();
        }
        return new BigDecimal(total).round(new MathContext(2, RoundingMode.UP)).floatValue();
    }

    public float getTotalNeto() {
        float salida = this.getTotalBruto() - this.getDescuentoMonto();
        return new BigDecimal(salida).round(new MathContext(2, RoundingMode.UP)).floatValue();
    }

    public Date getFechaGeneracion() {
        return this.fecFactura;
    }

    public void setFechaGeneracion(Date fecFactura) {
        this.fecFactura = fecFactura;
    }

    public EstadoFactura getEstadoFactura() {
        return TEFactura;
    }

    
    public Date getFechaBaja() {
        return fecBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        pedido.setTFacturas(this);
        this.pedido = pedido;
    }

    public String getCUITCliente() {
        return pedido.getCliente().getCuit();
    }

    public String getRazonSocialCliente() {
        return pedido.getCliente().getRazonSocial();
    }

    public Domicilio getRazonDomicilio() {
        return pedido.getCliente().getDomicilio();
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<DetalleFactura> getDetalleFactura() {
        return new ArrayList<DetalleFactura>(this.TDetallesFacturas);

    }

    public void setDetalleFactura(List<DetalleFactura> detalle) {

        for (DetalleFactura df : detalle) {
            df.setTFacturas(this);
        }

        this.TDetallesFacturas.clear();
        this.TDetallesFacturas.addAll(detalle);
    }

    public List<Cobro> getCobros() {
        return new ArrayList<Cobro>(this.TCobroses);
    }

    public void addCobro(Cobro c) {
        if (c == null) {
            throw new NegocioException("Error el cobro no puede ser nulo");
        }

        if (c.getImporte().floatValue() > getTotalAdeudado()) {
            throw new NegocioException("El importe no puede superar el monto adeudado");
        }

        if (c.getImporte().floatValue() <= 0) {
            throw new NegocioException("El importe debe ser mayor que cero");
        }

        c.setFactura(this);
        this.TCobroses.add(c);

    }

    public void setCobros(List<Cobro> TCobroses) {
        this.TCobroses.clear();
        for (Cobro dt : TCobroses) {
            dt.setFactura(this);
            TCobroses.add(dt);
        }
    }

    public float getTotalCobrado() {
        BigDecimal salida = new BigDecimal(0);
        for (Cobro c : this.getCobros()) {
            salida = salida.add(c.getImporte());
        }
        return salida.round(new MathContext(2, RoundingMode.UP)).floatValue();
    }

    public float getTotalAdeudado() {
        return this.getTotalNeto() - this.getTotalCobrado();
    }

    public void generar() {
        this.setFechaGeneracion(Utilidades.getFechaActual());
        this.setEmpleado(EmpleadoBD.listarEmpleado().get(0));
        this.setTEFactura(FacturaBD.getEstadoFactura(FacturaBD.Estado.GeneradaPendienteCobro));
        this.getPedido().setEstadoPedido(EstadoPedidoBD.getEstadoPendientePagado());
        HibernateUtil.guardarObjeto(this);
        HibernateUtil.getSession().flush();
    }

    public void anular(String motivo) {
        this.fecBaja = Utilidades.getFechaActual();
        this.motivoBaja = motivo;
        this.setTEFactura(FacturaBD.getEstadoFactura(FacturaBD.Estado.Anulada));
        this.pedido.setEstadoPedido(EstadoPedidoBD.getEstadoRetirado());
        HibernateUtil.guardarObjeto(this);
    }

    public void guardar() throws NegocioException {
        HibernateUtil.modificarObjeto(this);
    }

    public void setTEFactura(EstadoFactura TEFactura) {
        this.TEFactura = TEFactura;
    }

}
