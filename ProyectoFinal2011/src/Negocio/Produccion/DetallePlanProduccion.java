package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Administracion.Empleado;
import Negocio.Deposito.Faltante;
import Negocio.Ventas.DetallePedido;
import Presentacion.Utilidades;
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
import org.hibernate.annotations.Cascade;

/**
 * TDetallesPlan generated by hbm2java
 */
@Entity
@Table(name = "T_DETALLES_PLAN", schema = "dbo", catalog = "Ramaty")
public class DetallePlanProduccion implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_DETALLE_PLAN", unique = true, nullable = false, precision = 8, scale = 0)
    private int idDetallePlan;;
    //___________________________________________________________________________________________//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ETAPA_PRODUCCION_ESPECIFICA", nullable = true)
    private EtapaProduccionEspecifica TEtapasProduccionEspecifica;;
    //___________________________________________________________________________________________//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", nullable = true)
    private Empleado TEmpleados;;
    //___________________________________________________________________________________________//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PLAN_PRODUCCION", nullable = true)
    private PlanProduccion TPlanesProduccion;;
    //___________________________________________________________________________________________//
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_DETALLE_PEDIDO", nullable=false)
    private DetallePedido TDetallesPedido;;
    //___________________________________________________________________________________________//
   
    @Column(name = "CANTIDAD_PLANIFICADA", nullable = true, precision = 5, scale = 0)
    private Integer cantidadPlanificada;;
    //___________________________________________________________________________________________//
    @Column(name = "CANTIDAD_PRODUCIDA", precision = 5, scale = 0)
    private Integer cantidadProducida;;
    //___________________________________________________________________________________________//
    @Column(name = "OBSERVACIONES", length = 200)
    private String observaciones;;
    //___________________________________________________________________________________________//
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_PREVISTA_FIN", nullable = true, length = 23)
    private Date fecHoraPrevistaFin;;
    //___________________________________________________________________________________________//
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_PREVISTA_INICIO", nullable = true, length = 23)
    private Date fecHoraPrevistaInicio;;
    //___________________________________________________________________________________________//
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_REAL_INICIO", nullable = true, length = 23)
    private Date fecHoraRealInicio;;
    //___________________________________________________________________________________________//
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_REAL_FIN", nullable = true, length = 23)
    private Date fecHoraRealFin;;
    //___________________________________________________________________________________________//
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TDetallesPlan")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<Faltante> TFaltanteses = new HashSet<Faltante>(0);
    /*------------------------------------------------------------------------*/
     @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ORDEN_TRABAJO", nullable=true)
    private OrdenTrabajo TOrdenesTrabajo;;
/*------------------------------------------------------------------------*/
      @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_EDETALLE_PLAN", nullable=true)
     private EstadoDetallePlan TEdetallePlan;
      /*------------------------------------------------------------------------*/
      @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TDetallesPlan")
       private Set<MaqHerrPartXDetPlan> TMaqHerrPartXDetPlans = new HashSet<MaqHerrPartXDetPlan>(0);


    public DetallePlanProduccion() {
    }

    public DetallePlanProduccion(EtapaProduccionEspecifica TEtapasProduccionEspecifica) {
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
        TEtapasProduccionEspecifica.addDetallePlanProduccion(this);
    }

    public DetallePlanProduccion(int idDetallePlan, EtapaProduccionEspecifica TEtapasProduccionEspecifica, Empleado TEmpleados, PlanProduccion TPlanesProduccion, MaquinaHerramientaParticular TMaquinasHerramientaParticular, int cantidad, Date fecHoraPrevistaFin, Date fecHoraPrevistaInicio, Date fecHoraRealInicio, Date fecHoraRealFin, OrdenTrabajo TOrdenesTrabajo,EstadoDetallePlan TEdetallePlan) {
        this.idDetallePlan = idDetallePlan;
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
        this.TEmpleados = TEmpleados;
        this.TPlanesProduccion = TPlanesProduccion;
        this.cantidadPlanificada = cantidad;
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
        this.fecHoraRealInicio = fecHoraRealInicio;
        this.fecHoraRealFin = fecHoraRealFin;
        this.TOrdenesTrabajo = TOrdenesTrabajo;
        this.TEdetallePlan = TEdetallePlan;
    }

    public DetallePlanProduccion(int idDetallePlan, EtapaProduccionEspecifica TEtapasProduccionEspecifica, Empleado TEmpleados, PlanProduccion TPlanesProduccion, MaquinaHerramientaParticular TMaquinasHerramientaParticular, int cantidad, Date fecHoraPrevistaFin, Date fecHoraPrevistaInicio, Date fecHoraRealInicio, Date fecHoraRealFin, Set<Faltante> TFaltanteses, OrdenTrabajo TOrdenesTrabajo,EstadoDetallePlan TEdetallePlan) {
        this.idDetallePlan = idDetallePlan;
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
        this.TEmpleados = TEmpleados;
        this.TPlanesProduccion = TPlanesProduccion;
        this.cantidadPlanificada = cantidad;
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
        this.fecHoraRealInicio = fecHoraRealInicio;
        this.fecHoraRealFin = fecHoraRealFin;
        this.TFaltanteses = TFaltanteses;
        this.TOrdenesTrabajo = TOrdenesTrabajo;
        this.TEdetallePlan = TEdetallePlan;
    }

    public int getIdDetallePlan() {
        return this.idDetallePlan;
    }

    public void setIdDetallePlan(int idDetallePlan) {
        this.idDetallePlan = idDetallePlan;
    }

    public EtapaProduccionEspecifica getTEtapasProduccionEspecifica() {
        return this.TEtapasProduccionEspecifica;
    }

    public void setTEtapasProduccionEspecifica(EtapaProduccionEspecifica TEtapasProduccionEspecifica) {
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
    }

    public Empleado getTEmpleados() {
        return this.TEmpleados;
    }

    public void setTEmpleados(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }

    public PlanProduccion getTPlanesProduccion() {
        return this.TPlanesProduccion;
    }

    public void setTPlanesProduccion(PlanProduccion TPlanesProduccion) {
        this.TPlanesProduccion = TPlanesProduccion;
    }

    public DetallePedido getTDetallesPedido() {
        return this.TDetallesPedido;
    }

    public void setTDetallesPedido(DetallePedido TDetallesPedido) {
        this.TDetallesPedido = TDetallesPedido;
    }    
    public Integer getCantidad() {
        return this.cantidadPlanificada;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidadPlanificada = cantidad;
    }

    public Date getFecHoraPrevistaFin() {
        return this.fecHoraPrevistaFin;
    }

    public void setFecHoraPrevistaFin(Date fecHoraPrevistaFin) {
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
    }

    public Date getFecHoraPrevistaInicio() {
        return this.fecHoraPrevistaInicio;
    }

    public void setFecHoraPrevistaInicio(Date fecHoraPrevistaInicio) {
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
    }

    public Date getFecHoraRealInicio() {
        return this.fecHoraRealInicio;
    }

    public void setFecHoraRealInicio(Date fecHoraRealInicio) {
        this.fecHoraRealInicio = fecHoraRealInicio;
    }

    public Date getFecHoraRealFin() {
        return this.fecHoraRealFin;
    }

    public void setFecHoraRealFin(Date fecHoraRealFin) {
        this.fecHoraRealFin = fecHoraRealFin;
    }

    public Set<Faltante> getTFaltanteses() {
        return this.TFaltanteses;
    }

    public void setTFaltanteses(Set<Faltante> TFaltanteses) {
        this.TFaltanteses = TFaltanteses;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getCantidadProducida() {
        return this.cantidadProducida;
    }

    public void setCantidadProducida(Integer cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public void generarFaltantes() {
        this.TFaltanteses.clear();
        for( DetalleEtapaProduccion dep: this.getTEtapasProduccionEspecifica().getDetalleEtapaProduccion())
            if(dep.getMaterial()!=null)
            {

                Faltante f=new Faltante();              
                f.setCantidad((int)Math.ceil(1f*dep.getCantidadNecesaria()*this.getCantidad()/dep.getMaterial().getLogitud()));
                f.setMaterial(dep.getMaterial());
                f.setDetallePlan(this);
                f.setFecGeneracion(Utilidades.getFechaActual());
                f.setFecNecesidad(this.getFecHoraPrevistaInicio());
                TFaltanteses.add(f);
            }
    }


    public OrdenTrabajo getTOrdenesTrabajo() {
        return this.TOrdenesTrabajo;
    }

    public void setTOrdenesTrabajo(OrdenTrabajo TOrdenesTrabajo) {
        this.TOrdenesTrabajo = TOrdenesTrabajo;
    }

    public EstadoDetallePlan getTEdetallePlan() {
        return this.TEdetallePlan;
    }

    public void setTEdetallePlan(EstadoDetallePlan TEdetallePlan) {
        this.TEdetallePlan = TEdetallePlan;
    }
    
    public Set<MaqHerrPartXDetPlan> getTMaqHerrPartXDetPlans() {
        return this.TMaqHerrPartXDetPlans;
    }

    public void setTMaqHerrPartXDetPlans(Set<MaqHerrPartXDetPlan> TMaqHerrPartXDetPlans) {
        this.TMaqHerrPartXDetPlans = TMaqHerrPartXDetPlans;
    }

}
