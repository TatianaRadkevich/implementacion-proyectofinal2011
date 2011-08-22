package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


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
 * TEtapasProduccion generated by hbm2java
 */
@Entity
@Table(name="T_ETAPAS_PRODUCCION"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class EstadoEtapaProduccion  implements java.io.Serializable {


     private short idEtapaProduccion;
     private String nombre;
     private String descripcion;
     private Date fecBaja;
     private String motivoBaja;
     private Set<EtapaProduccionEspecifica> TEtapasProduccionEspecificas = new HashSet<EtapaProduccionEspecifica>(0);

    public EstadoEtapaProduccion() {
    }

	
    public EstadoEtapaProduccion(short idEtapaProduccion, String nombre) {
        this.idEtapaProduccion = idEtapaProduccion;
        this.nombre = nombre;
    }
    public EstadoEtapaProduccion(short idEtapaProduccion, String nombre, String descripcion, Date fecBaja, String motivoBaja, Set<EtapaProduccionEspecifica> TEtapasProduccionEspecificas) {
       this.idEtapaProduccion = idEtapaProduccion;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.fecBaja = fecBaja;
       this.motivoBaja = motivoBaja;
       this.TEtapasProduccionEspecificas = TEtapasProduccionEspecificas;
    }
   
    @Id 
    @GeneratedValue
    @Column(name="ID_ETAPA_PRODUCCION", unique=true, nullable=false, precision=3, scale=0)
    public short getIdEtapaProduccion() {
        return this.idEtapaProduccion;
    }
    
    public void setIdEtapaProduccion(short idEtapaProduccion) {
        this.idEtapaProduccion = idEtapaProduccion;
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_BAJA", length=23)
    public Date getFecBaja() {
        return this.fecBaja;
    }
    
    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }
    
    @Column(name="MOTIVO_BAJA", length=200)
    public String getMotivoBaja() {
        return this.motivoBaja;
    }
    
    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEtapasProduccion")
    public Set<EtapaProduccionEspecifica> getTEtapasProduccionEspecificas() {
        return this.TEtapasProduccionEspecificas;
    }
    
    public void setTEtapasProduccionEspecificas(Set<EtapaProduccionEspecifica> TEtapasProduccionEspecificas) {
        this.TEtapasProduccionEspecificas = TEtapasProduccionEspecificas;
    }

    public String toString(){
        return this.getNombre();
    }

}

