package Negocio.Deposito;
// Generated 04/01/2012 10:25:02 by Hibernate Tools 3.2.1.GA


import Negocio.Administracion.Empleado;
import Negocio.Compras.Material;
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
 * TReajusteStock generated by hbm2java
 */
@Entity
@Table(name="T_REAJUSTE_STOCK"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class ReajusteStock  implements java.io.Serializable {


     private int id;
     private Empleado Empleado;
     private Material Material;
     private Date fechaReajuste;
     private Short diferencia;
     private Short cantidad;
     private String observaciones;

    public ReajusteStock() {
    }

	
    public ReajusteStock(int id) {
        this.id = id;
    }
    public ReajusteStock(int id, Empleado Empleado, Material Material, Date fechaReajuste, Short diferencia, String observaciones) {
       this.id = id;
       this.Empleado = Empleado;
       this.Material = Material;
       this.fechaReajuste = fechaReajuste;
       this.diferencia = diferencia;
       this.observaciones = observaciones;
    }
   
     @Id 
    @GeneratedValue
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_EMPLEADO")
    public Empleado getEmpleado() {
        return this.Empleado;
    }
    
    public void setEmpleado(Empleado Empleado) {
        this.Empleado = Empleado;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_MATERIAL")
    public Material getMaterial() {
        return this.Material;
    }
    
    public void setMaterial(Material Material) {
        this.Material = Material;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_REAJUSTE", length=23)
    public Date getFechaReajuste() {
        return this.fechaReajuste;
    }
    
    public void setFechaReajuste(Date fechaReajuste) {
        this.fechaReajuste = fechaReajuste;
    }
    
    @Column(name="DIFERENCIA", precision=4, scale=0)
    public Short getDiferencia() {
        return this.diferencia;
    }
    
    public void setDiferencia(Short diferencia) {
        this.diferencia = diferencia;
    }
    
    @Column(name="CANTIDAD", precision=4, scale=0)
    public Short getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }
    
    @Column(name="OBSERVACIONES", length=50)
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }




}


