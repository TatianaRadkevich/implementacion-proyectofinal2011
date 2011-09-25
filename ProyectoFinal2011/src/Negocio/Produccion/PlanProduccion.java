package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Administracion.Empleado;
import Negocio.Ventas.Pedido;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
 * TPlanesProduccion generated by hbm2java
 */
@Entity
@Table(name = "T_PLANES_PRODUCCION", schema = "dbo", catalog = "Ramaty")
public class PlanProduccion implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_PLAN_PRODUCCION", unique = true, nullable = false, precision = 8, scale = 0)
    private int idPlanProduccion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ENCARGADO")//, nullable=false)
    private Empleado TEmpleados;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PEDIDO")//, nullable=false)
    private Pedido TPedidos;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_GENERACION", length = 23)// nullable = false,
    private Date fecGeneracion;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_PREVISTA_FIN", length = 23)//, nullable = false
    private Date fecHoraPrevistaFin;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_PREVISTA_INICIO", length = 23)//, nullable = false
    private Date fecHoraPrevistaInicio;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_REAL_FIN", length = 23)//nullable = false,
    private Date fecHoraRealFin;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_HORA_REAL_INICIO", length = 23)//, nullable = false
    private Date fecHoraRealInicio;
    @Column(name = "OBSERVACIONES", length = 200)
    private String observaciones;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_ULTIMA_MODIFICACION", length = 23)
    private Date fecUltimaModificacion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TPlanesProduccion")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<DetallePlanProduccion> TDetallesPlans = new HashSet<DetallePlanProduccion>(0);

    public PlanProduccion() {
    }

    public PlanProduccion(int idPlanProduccion, Empleado TEmpleados, Pedido TPedidos, Date fecGeneracion, Date fecHoraPrevistaFin, Date fecHoraPrevistaInicio, Date fecHoraRealFin, Date fecHoraRealInicio) {
        this.idPlanProduccion = idPlanProduccion;
        this.TEmpleados = TEmpleados;
        this.TPedidos = TPedidos;
        this.fecGeneracion = fecGeneracion;
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
        this.fecHoraRealFin = fecHoraRealFin;
        this.fecHoraRealInicio = fecHoraRealInicio;
    }

    public PlanProduccion(int idPlanProduccion, Empleado TEmpleados, Pedido TPedidos, Date fecGeneracion, Date fecHoraPrevistaFin, Date fecHoraPrevistaInicio, Date fecHoraRealFin, Date fecHoraRealInicio, String observaciones, Date fecUltimaModificacion, Set<DetallePlanProduccion> TDetallesPlans) {
        this.idPlanProduccion = idPlanProduccion;
        this.TEmpleados = TEmpleados;
        this.TPedidos = TPedidos;
        this.fecGeneracion = fecGeneracion;
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
        this.fecHoraRealFin = fecHoraRealFin;
        this.fecHoraRealInicio = fecHoraRealInicio;
        this.observaciones = observaciones;
        this.fecUltimaModificacion = fecUltimaModificacion;
        this.TDetallesPlans = TDetallesPlans;
    }

    public int getId() {
        return this.idPlanProduccion;
    }

    public void setId(int idPlanProduccion) {
        this.idPlanProduccion = idPlanProduccion;
    }

    public Empleado getEmpleado() {
        return this.TEmpleados;
    }

    public void setEmpleado(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }

    public Pedido getPedido() {
        return this.TPedidos;
    }

    public void setPedido(Pedido TPedidos) {
        this.TPedidos = TPedidos;
        TPedidos.getPlanesProduccion().clear();
        TPedidos.getPlanesProduccion().add(this);
    }

    public Date getFecGeneracion() {
        return this.fecGeneracion;
    }

    public void setFecGeneracion(Date fecGeneracion) {
        this.fecGeneracion = fecGeneracion;
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

    public Date getFecHoraRealFin() {
        return this.fecHoraRealFin;
    }

    public void setFecHoraRealFin(Date fecHoraRealFin) {
        this.fecHoraRealFin = fecHoraRealFin;
    }

    public Date getFecHoraRealInicio() {
        return this.fecHoraRealInicio;
    }

    public void setFecHoraRealInicio(Date fecHoraRealInicio) {
        this.fecHoraRealInicio = fecHoraRealInicio;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecUltimaModificacion() {
        return this.fecUltimaModificacion;
    }

    public void setFecUltimaModificacion(Date fecUltimaModificacion) {
        this.fecUltimaModificacion = fecUltimaModificacion;
    }

    public List<DetallePlanProduccion> getDetallePlan() {
        return new ArrayList<DetallePlanProduccion>(this.TDetallesPlans);
    }

    public List<DetallePlanProduccion> getDetallePlan(Producto p) {
        List<DetallePlanProduccion> salida = new ArrayList<DetallePlanProduccion>(this.TDetallesPlans.size());
        if (p == null) {
            return salida;
        }

        for (DetallePlanProduccion dpp : this.TDetallesPlans) {
            if (dpp.getTEtapasProduccionEspecifica().getProducto().getIdProducto() == p.getIdProducto()) {
                salida.add(dpp);
            }
        }

        return salida;
    }

    public void setDetallePlan(List<DetallePlanProduccion> detalle) {
        this.TDetallesPlans.clear();
        for (DetallePlanProduccion dt : detalle) {
            dt.setTPlanesProduccion(this);
            this.TDetallesPlans.add(dt);
        }

    }

   public void addDetallePlan(DetallePlanProduccion detalle)
    {
        this.TDetallesPlans.add(detalle);

   }
}
