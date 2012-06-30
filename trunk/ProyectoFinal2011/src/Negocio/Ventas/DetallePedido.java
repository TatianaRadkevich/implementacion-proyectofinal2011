package Negocio.Ventas;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Produccion.Producto;
import Negocio.Administracion.DetalleFactura;
import BaseDeDatos.Deposito.AlmacenamientoProductoTerminado;
import Negocio.Exceptiones.NegocioException;
import Presentacion.Utilidades;
import java.math.BigDecimal;
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
import org.hibernate.annotations.Cascade;

/**
 * TDetallesPedido generated by hbm2java
 */
@Entity
@Table(name = "T_DETALLES_PEDIDO", schema = "dbo", catalog = "Ramaty")
public class DetallePedido implements java.io.Serializable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue
    @Column(name = "ID_DETALLE_PEDIDO", unique = true, nullable = false, precision = 8, scale = 0)
    private int idDetallePedido;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PEDIDO", nullable = false)
    private Pedido TPedidos;
    //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EDETALLE_PEDIDO")//, nullable=false)
    private EstadoDetallePedido TEdetallePedido;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private Producto TProductos;
    //
    @Column(name = "CANTIDAD", nullable = false, precision = 5, scale = 0)
    private int cantidad;
    //
    @Column(name = "PRECIO", nullable = false, precision = 6)
    private BigDecimal precio;
    //
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TDetallesPedido")
    private Set<DetalleFactura> TDetallesFacturas = new HashSet<DetalleFactura>(0);
    //
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TDetallesPedido")
    private Set<AlmacenamientoProductoTerminado> almacenado = new HashSet<AlmacenamientoProductoTerminado>(0);
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Constructores">

    public DetallePedido() {
    }

    public DetallePedido(Producto productos, int cantidad, float precio) {
        this.TProductos = productos;
        this.cantidad = cantidad;
        this.precio = new BigDecimal(precio);
    }

    public DetallePedido(int idDetallePedido, Pedido TPedidos, Producto TProductos, int cantidad, BigDecimal precio) {
        this.idDetallePedido = idDetallePedido;
        this.TPedidos = TPedidos;
        this.TProductos = TProductos;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetallePedido(DetallePedido dp) {
        this.idDetallePedido = dp.idDetallePedido;
        this.TPedidos = dp.TPedidos;
        this.TProductos = dp.TProductos;
        this.cantidad = dp.cantidad;
        this.precio = dp.precio;
        this.TDetallesFacturas = new HashSet<DetalleFactura>(dp.TDetallesFacturas);
        this.almacenado = new HashSet<AlmacenamientoProductoTerminado>(dp.almacenado);
    }
    // </editor-fold>

    public int getIdDetallePedido() {
        return this.idDetallePedido;
    }

    public Pedido getPedido() {
        return this.TPedidos;
    }

    public void setPedido(Pedido pedido) {
        this.TPedidos = pedido;
    }

    public Producto getProducto() {
        return this.TProductos;
    }

    public void setProducto(Producto producto) {
        Utilidades.validarNULL(producto);
        this.TProductos = producto;
        this.precio = producto.getPrecioUnitario();
    }

    public int getCantidad() {
return this.cantidad;
    }

    public void setCantidad(int cantidad) {
    if(cantidad<=0)
            throw new NegocioException("Solo se permite valores mayores a cero");
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return this.precio.floatValue();
    }

    public EstadoDetallePedido getEstadoDetallePedido() {
        return TEdetallePedido;
    }

    public void setEstadoDetallePedido(EstadoDetallePedido estado) {
        this.TEdetallePedido = estado;
    }

    public Set<DetalleFactura> getDetalleFactura() {
        return this.TDetallesFacturas;
    }

    public Set<AlmacenamientoProductoTerminado> getAlmacenado() {
        if (this.almacenado == null) {
            almacenado = new HashSet<AlmacenamientoProductoTerminado>();
        }
        return almacenado;
    }
}
