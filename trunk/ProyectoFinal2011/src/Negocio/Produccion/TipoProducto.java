package Negocio.Produccion;
// Generated 19/08/2011 17:02:19 by Hibernate Tools 3.2.1.GA


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TTproducto generated by hbm2java
 */
@Entity
@Table(name="T_TPRODUCTO"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class TipoProducto  implements java.io.Serializable {


     private int idTproducto;
     private String nombre;
     private String descripcion;
     private String codigo;
     private Date fecBaja;
     private String motivoBaja;
    

    public TipoProducto() {
    }


    public TipoProducto(int idTproducto, String nombre, String codigo) {
        this.idTproducto = idTproducto;
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public TipoProducto(int idTproducto, String nombre, String descripcion, String codigo, Date fecBaja, String motivoBaja) {
       this.idTproducto = idTproducto;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.codigo = codigo;
       this.fecBaja = fecBaja;
       this.motivoBaja = motivoBaja;
       
    }

     @Id
     @GeneratedValue
    @Column(name="ID_TPRODUCTO", unique=true, nullable=false, precision=2, scale=0)
    public int getIdTproducto() {
        return this.idTproducto;
    }

    public void setIdTproducto(int idTproducto) {
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

    @Column(name="CODIGO", nullable=false, length=4)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_BAJA", length=23)
    public Date getFecBaja() {
        return this.fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    @Column(name="MOTIVO_BAJA", length=100)
    public String getMotivoBaja() {
        return this.motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public String toString(){
        return this.getNombre();
    }
}


