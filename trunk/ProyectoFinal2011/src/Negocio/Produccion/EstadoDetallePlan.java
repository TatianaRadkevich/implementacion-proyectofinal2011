package Negocio.Produccion;
// Generated 29/10/2011 18:12:48 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TEdetallePlan generated by hbm2java
 */
@Entity
@Table(name="T_EDETALLE_PLAN"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class EstadoDetallePlan  implements java.io.Serializable {


     private byte idEdetallePlan;
     private String nombre;
     private String descripcion;
    

    public EstadoDetallePlan() {
    }

	
    public EstadoDetallePlan(byte idEdetallePlan, String nombre) {
        this.idEdetallePlan = idEdetallePlan;
        this.nombre = nombre;
    }
    public EstadoDetallePlan(byte idEdetallePlan, String nombre, String descripcion) {
       this.idEdetallePlan = idEdetallePlan;
       this.nombre = nombre;
       this.descripcion = descripcion;
      
    }
   
     @Id 
    @GeneratedValue
    @Column(name="ID_EDETALLE_PLAN", unique=true, nullable=false, precision=2, scale=0)
    public byte getIdEdetallePlan() {
        return this.idEdetallePlan;
    }
    
    public void setIdEdetallePlan(byte idEdetallePlan) {
        this.idEdetallePlan = idEdetallePlan;
    }
    
    @Column(name="NOMBRE", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="DESCRIPCION", length=200)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}


