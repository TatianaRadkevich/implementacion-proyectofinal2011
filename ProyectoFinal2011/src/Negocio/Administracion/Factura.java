package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Administracion.Empleado;
import Negocio.Administracion.Cobro;
import Negocio.Administracion.DetalleFactura;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TFacturas generated by hbm2java
 */
@Entity
@Table(name="T_FACTURAS"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class Factura  implements java.io.Serializable {


     private int idFactura;
     private Empleado TEmpleados;
     private BigDecimal descuento;
     private Date fecFactura;
     private int numero;
     private BigDecimal recargo;
     private Set<DetalleFactura> TDetallesFacturas = new HashSet<DetalleFactura>(0);
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
   
     @Id 
    
    @Column(name="ID_FACTURA", unique=true, nullable=false, precision=8, scale=0)
    public int getIdFactura() {
        return this.idFactura;
    }
    
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_EMPLEADO", nullable=false)
    public Empleado getTEmpleados() {
        return this.TEmpleados;
    }
    
    public void setTEmpleados(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }
    
    @Column(name="DESCUENTO", precision=6, scale=4)
    public BigDecimal getDescuento() {
        return this.descuento;
    }
    
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_FACTURA", nullable=false, length=23)
    public Date getFecFactura() {
        return this.fecFactura;
    }
    
    public void setFecFactura(Date fecFactura) {
        this.fecFactura = fecFactura;
    }
    
    @Column(name="NUMERO", nullable=false, precision=8, scale=0)
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    @Column(name="RECARGO", precision=6, scale=4)
    public BigDecimal getRecargo() {
        return this.recargo;
    }
    
    public void setRecargo(BigDecimal recargo) {
        this.recargo = recargo;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TFacturas")
    public Set<DetalleFactura> getTDetallesFacturas() {
        return this.TDetallesFacturas;
    }
    
    public void setTDetallesFacturas(Set<DetalleFactura> TDetallesFacturas) {
        this.TDetallesFacturas = TDetallesFacturas;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TFacturas")
    public Set<Cobro> getTCobroses() {
        return this.TCobroses;
    }
    
    public void setTCobroses(Set<Cobro> TCobroses) {
        this.TCobroses = TCobroses;
    }




}

