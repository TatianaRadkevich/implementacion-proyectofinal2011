package Negocio.Produccion;
// Generated 20-oct-2011 22:08:44 by Hibernate Tools 3.2.1.GA


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
 * ProblemasMhp generated by hbm2java
 */
@Entity
@Table(name="T_PROBLEMAS_MHP"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class ProblemasMhp  implements java.io.Serializable {


     private short idProblemaMhp;
     private MaquinaParticular TMaquinasParticular;
     private String descripcion;
     private Date fecHoraProblema;
     private Date fecHoraEstimadaSolucion;
     private Date fecHoraRealSolucion;
     private String observacionesSolucion;

    public ProblemasMhp() {
    }

	
    public ProblemasMhp(short idProblemaMhp) {
        this.idProblemaMhp = idProblemaMhp;
    }
    public ProblemasMhp(short idProblemaMhp, MaquinaParticular TMaquinasParticular, String descripcion, Date fecHoraProblema, Date fecHoraEstimadaSolucion, Date fecHoraRealSolucion, String observacionesSolucion) {
       this.idProblemaMhp = idProblemaMhp;
       this.TMaquinasParticular = TMaquinasParticular;
       this.descripcion = descripcion;
       this.fecHoraProblema = fecHoraProblema;
       this.fecHoraEstimadaSolucion = fecHoraEstimadaSolucion;
       this.fecHoraRealSolucion = fecHoraRealSolucion;
       this.observacionesSolucion = observacionesSolucion;
    }
   
     @Id 
    @GeneratedValue
    @Column(name="ID_PROBLEMA_MHP", unique=true, nullable=false, precision=2, scale=0)
    public short getIdProblemaMhp() {
        return this.idProblemaMhp;
    }
    
    public void setIdProblemaMhp(short idProblemaMhp) {
        this.idProblemaMhp = idProblemaMhp;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_MAQUINA_PARTICULAR")
    public MaquinaParticular getTMaquinasParticular() {
        return this.TMaquinasParticular;
    }
    
    public void setTMaquinasParticular(MaquinaParticular TMaquinasParticular) {
        this.TMaquinasParticular = TMaquinasParticular;
    }
    
    @Column(name="DESCRIPCION", length=500)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_HORA_PROBLEMA", length=23)
    public Date getFecHoraProblema() {
        return this.fecHoraProblema;
    }
    
    public void setFecHoraProblema(Date fecHoraProblema) {
        this.fecHoraProblema = fecHoraProblema;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_HORA_ESTIMADA_SOLUCION", length=23)
    public Date getFecHoraEstimadaSolucion() {
        return this.fecHoraEstimadaSolucion;
    }
    
    public void setFecHoraEstimadaSolucion(Date fecHoraEstimadaSolucion) {
        this.fecHoraEstimadaSolucion = fecHoraEstimadaSolucion;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_HORA_REAL_SOLUCION", length=23)
    public Date getFecHoraRealSolucion() {
        return this.fecHoraRealSolucion;
    }
    
    public void setFecHoraRealSolucion(Date fecHoraRealSolucion) {
        this.fecHoraRealSolucion = fecHoraRealSolucion;
    }
    
    @Column(name="OBSERVACIONES_SOLUCION", length=500)
    public String getObservacionesSolucion() {
        return this.observacionesSolucion;
    }
    
    public void setObservacionesSolucion(String observacionesSolucion) {
        this.observacionesSolucion = observacionesSolucion;
    }




}


