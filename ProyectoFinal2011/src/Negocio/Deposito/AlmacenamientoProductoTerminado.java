/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Deposito;

import Negocio.Ventas.DetallePedido;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fbobbio
 */
@Entity
@Table(name = "T_ALMACENAMIENTOS_PRODUCTO_TERMINADO", schema = "dbo", catalog = "Ramaty")
public class AlmacenamientoProductoTerminado implements Serializable {
        
    @Id
    @GeneratedValue
    @Column(name = "ID_ALMACENAMIENTO_PRODUCTO_TERMINADO", unique = true, nullable = false, precision = 8, scale = 0)
    private int idAlmacenamiento;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_ALMACENAMIENTO", nullable = false, length = 23)
    private Date fechaAlmacenamiento;
    
    @Column(name = "CANTIDAD", nullable = false, precision = 5)
    private int cantidad;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_DETALLE_PEDIDO", nullable=false)
    private DetallePedido TDetallesPedido;
    
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_EALMACENAMIENTO_PRODUCTO_TERMINADO")//, nullable=false)
    private EAlmacenamientoProductoTerminado estado;

    public DetallePedido getTDetallesPedido() {
        return TDetallesPedido;
    }

    public void setTDetallesPedido(DetallePedido TDetallesPedido) {
        this.TDetallesPedido = TDetallesPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public EAlmacenamientoProductoTerminado getEstado() {
        return estado;
    }

    public void setEstado(EAlmacenamientoProductoTerminado estado) {
        this.estado = estado;
    }

    public Date getFechaAlmacenamiento() {
        return fechaAlmacenamiento;
    }

    public void setFechaAlmacenamiento(Date fechaAlmacenamiento) {
        this.fechaAlmacenamiento = fechaAlmacenamiento;
    }

    public int getIdAlmacenamiento() {
        return idAlmacenamiento;
    }

    public void setIdAlmacenamiento(int idAlmacenamiento) {
        this.idAlmacenamiento = idAlmacenamiento;
    }    
}
