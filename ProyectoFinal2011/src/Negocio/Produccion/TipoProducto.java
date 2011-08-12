package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TTproducto generated by hbm2java
 */
@Entity
@Table(name="T_TPRODUCTO"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class TipoProducto  implements java.io.Serializable {


     private byte idTproducto;
     private String nombre;
     private String descripcion;
    // private Set<Producto> TProductoses = new HashSet<Producto>(0);

    public TipoProducto() {
    }

	
    public TipoProducto(byte idTproducto, String nombre) {
        this.idTproducto = idTproducto;
        this.nombre = nombre;
    }
    public TipoProducto(byte idTproducto, String nombre, String descripcion/**, Set<Producto> TProductoses**/) {
       this.idTproducto = idTproducto;
       this.nombre = nombre;
       this.descripcion = descripcion;
      // this.TProductoses = TProductoses;
    }
   
     @Id 
    @GeneratedValue
    @Column(name="ID_TPRODUCTO", unique=true, nullable=false, precision=2, scale=0)
    public byte getIdTproducto() {
        return this.idTproducto;
    }
    
    public void setIdTproducto(byte idTproducto) {
        this.idTproducto = idTproducto;
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
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TTproducto")
//    public Set<Producto> getTProductoses() {
//        return this.TProductoses;
//    }
//
//    public void setTProductoses(Set<Producto> TProductoses) {
//        this.TProductoses = TProductoses;
//    }

    @Override
        public String toString(){
            return this.getNombre();
}


}

