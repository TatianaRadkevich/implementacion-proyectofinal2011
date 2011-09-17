package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Administracion.Cobro;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TFormasPago generated by hbm2java
 */
@Entity
@Table(name="T_FORMAS_PAGO"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class FormaPago  implements java.io.Serializable {


     private byte idFormaPago;
     private String nombre;
     private String descripcion;
     private Set<Cobro> TCobroses = new HashSet<Cobro>(0);

    public FormaPago() {
    }

	
    public FormaPago(byte idFormaPago, String nombre) {
        this.idFormaPago = idFormaPago;
        this.nombre = nombre;
    }
    public FormaPago(byte idFormaPago, String nombre, String descripcion, Set<Cobro> TCobroses) {
       this.idFormaPago = idFormaPago;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.TCobroses = TCobroses;
    }
   
     @Id 
    
    @Column(name="ID_FORMA_PAGO", unique=true, nullable=false, precision=2, scale=0)
    public byte getIdFormaPago() {
        return this.idFormaPago;
    }
    
    public void setIdFormaPago(byte idFormaPago) {
        this.idFormaPago = idFormaPago;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TFormasPago")
    public Set<Cobro> getTCobroses() {
        return this.TCobroses;
    }
    
    public void setTCobroses(Set<Cobro> TCobroses) {
        this.TCobroses = TCobroses;
    }




}

