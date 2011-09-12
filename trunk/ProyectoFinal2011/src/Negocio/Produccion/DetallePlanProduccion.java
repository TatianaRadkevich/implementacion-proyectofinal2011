package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Administracion.Empleado;
import Negocio.Deposito.Faltante;
import Negocio.Produccion.MaquinaHerramientaParticular;
import Negocio.Produccion.EtapaProduccionEspecifica;
import Negocio.Produccion.PlanProduccion;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TDetallesPlan generated by hbm2java
 */
@Entity
@Table(name="T_DETALLES_PLAN"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class DetallePlanProduccion  implements java.io.Serializable {


     private int idDetallePlan;
     private EtapaProduccionEspecifica TEtapasProduccionEspecifica;
     private Empleado TEmpleados;
     private PlanProduccion TPlanesProduccion;
     private MaquinaHerramientaParticular TMaquinasHerramientaParticular;
     private Integer cantidadPlanificada;
     private Integer cantidadProducida;
     private String observaciones;
     private Date fecHoraPrevistaFin;
     private Date fecHoraPrevistaInicio;
     private Date fecHoraRealInicio;
     private Date fecHoraRealFin;
     private Set<Faltante> TFaltanteses = new HashSet<Faltante>(0);


    public DetallePlanProduccion() {
    }

    public DetallePlanProduccion(EtapaProduccionEspecifica TEtapasProduccionEspecifica) {
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
    }

    
	
    public DetallePlanProduccion(int idDetallePlan, EtapaProduccionEspecifica TEtapasProduccionEspecifica, Empleado TEmpleados, PlanProduccion TPlanesProduccion, MaquinaHerramientaParticular TMaquinasHerramientaParticular, int cantidad, Date fecHoraPrevistaFin, Date fecHoraPrevistaInicio, Date fecHoraRealInicio, Date fecHoraRealFin) {
        this.idDetallePlan = idDetallePlan;
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
        this.TEmpleados = TEmpleados;
        this.TPlanesProduccion = TPlanesProduccion;
        this.TMaquinasHerramientaParticular = TMaquinasHerramientaParticular;
        this.cantidadPlanificada = cantidad;
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
        this.fecHoraRealInicio = fecHoraRealInicio;
        this.fecHoraRealFin = fecHoraRealFin;
    }
    public DetallePlanProduccion(int idDetallePlan, EtapaProduccionEspecifica TEtapasProduccionEspecifica, Empleado TEmpleados, PlanProduccion TPlanesProduccion, MaquinaHerramientaParticular TMaquinasHerramientaParticular, int cantidad, Date fecHoraPrevistaFin, Date fecHoraPrevistaInicio, Date fecHoraRealInicio, Date fecHoraRealFin, Set<Faltante> TFaltanteses) {
       this.idDetallePlan = idDetallePlan;
       this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
       this.TEmpleados = TEmpleados;
       this.TPlanesProduccion = TPlanesProduccion;
       this.TMaquinasHerramientaParticular = TMaquinasHerramientaParticular;
       this.cantidadPlanificada = cantidad;
       this.fecHoraPrevistaFin = fecHoraPrevistaFin;
       this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
       this.fecHoraRealInicio = fecHoraRealInicio;
       this.fecHoraRealFin = fecHoraRealFin;
       this.TFaltanteses = TFaltanteses;
    }
   
     @Id 
    @GeneratedValue
    @Column(name="ID_DETALLE_PLAN", unique=true, nullable=false, precision=8, scale=0)
    public int getIdDetallePlan() {
        return this.idDetallePlan;
    }
    
    public void setIdDetallePlan(int idDetallePlan) {
        this.idDetallePlan = idDetallePlan;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ETAPA_PRODUCCION_ESPECIFICA", nullable=false)
    public EtapaProduccionEspecifica getTEtapasProduccionEspecifica() {
        return this.TEtapasProduccionEspecifica;
    }
    
    public void setTEtapasProduccionEspecifica(EtapaProduccionEspecifica TEtapasProduccionEspecifica) {
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_OPERARIO", nullable=false)
    public Empleado getTEmpleados() {
        return this.TEmpleados;
    }
    
    public void setTEmpleados(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PLAN_PRODUCCION", nullable=false)
    public PlanProduccion getTPlanesProduccion() {
        return this.TPlanesProduccion;
    }
    
    public void setTPlanesProduccion(PlanProduccion TPlanesProduccion) {
        this.TPlanesProduccion = TPlanesProduccion;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_MAQUINA_HERRAMIENTA_PARTICULAR", nullable=false)
    public MaquinaHerramientaParticular getTMaquinasHerramientaParticular() {
        return this.TMaquinasHerramientaParticular;
    }
    
    public void setTMaquinasHerramientaParticular(MaquinaHerramientaParticular TMaquinasHerramientaParticular) {
        this.TMaquinasHerramientaParticular = TMaquinasHerramientaParticular;
    }
    
    @Column(name="CANTIDAD", nullable=false, precision=5, scale=0)
    public int getCantidad() {
        return this.cantidadPlanificada;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidadPlanificada = cantidad;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_HORA_PREVISTA_FIN", nullable=false, length=23)
    public Date getFecHoraPrevistaFin() {
        return this.fecHoraPrevistaFin;
    }
    
    public void setFecHoraPrevistaFin(Date fecHoraPrevistaFin) {
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_HORA_PREVISTA_INICIO", nullable=false, length=23)
    public Date getFecHoraPrevistaInicio() {
        return this.fecHoraPrevistaInicio;
    }
    
    public void setFecHoraPrevistaInicio(Date fecHoraPrevistaInicio) {
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_HORA_REAL_INICIO", nullable=false, length=23)
    public Date getFecHoraRealInicio() {
        return this.fecHoraRealInicio;
    }
    
    public void setFecHoraRealInicio(Date fecHoraRealInicio) {
        this.fecHoraRealInicio = fecHoraRealInicio;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_HORA_REAL_FIN", nullable=false, length=23)
    public Date getFecHoraRealFin() {
        return this.fecHoraRealFin;
    }
    
    public void setFecHoraRealFin(Date fecHoraRealFin) {
        this.fecHoraRealFin = fecHoraRealFin;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TDetallesPlan")
    public Set<Faltante> getTFaltanteses() {
        return this.TFaltanteses;
    }
    
    public void setTFaltanteses(Set<Faltante> TFaltanteses) {
        this.TFaltanteses = TFaltanteses;
    }

    @Column(name="OBSERVACIONES", length=200)
    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
   @Column(name="CANTIDAD_PRODUCIDA", precision=5, scale=0)
    public Integer getCantidadProducida() {
        return this.cantidadProducida;
    }

    public void setCantidadProducida(Integer cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }
    

}


