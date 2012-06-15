package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Administracion.Factura;
import Negocio.Ventas.DetallePedido;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TDetallesFactura generated by hbm2java
 */
@Entity
@Table(name = "T_DETALLES_FACTURA", schema = "dbo", catalog = "Ramaty")
public class DetalleFactura implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_DETALLE_FACTURA", unique = true, nullable = false, precision = 8, scale = 0)
    private int idDetalleFactura;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FACTURA", nullable = false)
    private Factura TFacturas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DETALLE_PEDIDO", nullable = false)
    private DetallePedido TDetallesPedido;
    @Column(name = "CANTIDAD", nullable = false, precision = 5, scale = 0)
    private Integer cantidad;
    @Column(name = "PRECIO", nullable = false, precision = 6)
    private Float precio;

    public DetalleFactura() {
    }

    public DetalleFactura(int idDetalleFactura, Factura TFacturas, DetallePedido TDetallesPedido, int cantidad, Float precio) {
        this.idDetalleFactura = idDetalleFactura;
        this.TFacturas = TFacturas;
        this.TDetallesPedido = TDetallesPedido;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdDetalleFactura() {
        return this.idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public Factura getTFacturas() {
        return this.TFacturas;
    }

    public void setTFacturas(Factura TFacturas) {
        this.TFacturas = TFacturas;
    }

    public DetallePedido getDetallePedido() {
        return this.TDetallesPedido;
    }

    public void setTDetallesPedido(DetallePedido TDetallesPedido) {
        this.TDetallesPedido = TDetallesPedido;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
