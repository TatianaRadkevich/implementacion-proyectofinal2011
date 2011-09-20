package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Administracion.FormaPago;
import Negocio.Administracion.Factura;
import Negocio.Administracion.Empleado;
import Negocio.Administracion.Cheque;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TCobros generated by hbm2java
 */
@Entity
@Table(name="T_COBROS"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class Cobro  implements java.io.Serializable {


     private int idCobro;
     private Factura TFacturas;
     private FormaPago TFormasPago;
     private Empleado TEmpleados;
     private Cheque TCheques;
     private Date fecCobro;
     private BigDecimal importe;
     private String observaciones;

    public Cobro() {
    }

	
    public Cobro(int idCobro, FormaPago TFormasPago, Empleado TEmpleados, Date fecCobro, BigDecimal importe) {
        this.idCobro = idCobro;
        this.TFormasPago = TFormasPago;
        this.TEmpleados = TEmpleados;
        this.fecCobro = fecCobro;
        this.importe = importe;
    }
    public Cobro(int idCobro, Factura TFacturas, FormaPago TFormasPago, Empleado TEmpleados, Cheque TCheques, Date fecCobro, BigDecimal importe, String observaciones) {
       this.idCobro = idCobro;
       this.TFacturas = TFacturas;
       this.TFormasPago = TFormasPago;
       this.TEmpleados = TEmpleados;
       this.TCheques = TCheques;
       this.fecCobro = fecCobro;
       this.importe = importe;
       this.observaciones = observaciones;
    }
   
    @Id 
    
    @Column(name="ID_COBRO", unique=true, nullable=false, precision=8, scale=0)
    public int getIdCobro() {
        return this.idCobro;
    }
    
    public void setIdCobro(int idCobro) {
        this.idCobro = idCobro;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_FACTURA")
    public Factura getTFacturas() {
        return this.TFacturas;
    }
    
    public void setTFacturas(Factura TFacturas) {
        this.TFacturas = TFacturas;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_FORMA_PAGO", nullable=false)
    public FormaPago getTFormasPago() {
        return this.TFormasPago;
    }
    
    public void setTFormasPago(FormaPago TFormasPago) {
        this.TFormasPago = TFormasPago;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ENCARGADO", nullable=false)
    public Empleado getTEmpleados() {
        return this.TEmpleados;
    }
    
    public void setTEmpleados(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_CHEQUE")
    public Cheque getTCheques() {
        return this.TCheques;
    }
    
    public void setTCheques(Cheque TCheques) {
        this.TCheques = TCheques;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_COBRO", nullable=false, length=23)
    public Date getFecCobro() {
        return this.fecCobro;
    }
    
    public void setFecCobro(Date fecCobro) {
        this.fecCobro = fecCobro;
    }
    
    @Column(name="IMPORTE", nullable=false, precision=6, scale=4)
    public BigDecimal getImporte() {
        return this.importe;
    }
    
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    @Column(name="OBSERVACIONES", length=200)
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }




}

