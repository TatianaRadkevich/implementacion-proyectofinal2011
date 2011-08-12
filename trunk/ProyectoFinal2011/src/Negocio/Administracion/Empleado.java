package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.UbicacionGeografica.Domicilio;
import Negocio.Produccion.PlanProduccion;
import Negocio.Administracion.Cobro;
import Negocio.Administracion.Cobro;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.GestionUsuario.Usuario;
import Negocio.GestionUsuario.Usuario;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TEmpleados generated by hbm2java
 */
@Entity
@Table(name="T_EMPLEADOS"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class Empleado  implements java.io.Serializable {


     private int idEmpleado;
     private Domicilio TDomicilios;
     private TipoDocumento TTdocumento;
     private Usuario TUsuarios;
     private Sexo TSexos;
     private String nombre;
     private String apellido;
     private Long celular;
     private String correoElectronico;
     private Date fecNacimiento;
     private int numeroDocumento;
     private Long telefono;
     private Integer legajo;
     private String observaciones;
     private Integer idAsistenciaEmpleado;
     private Short idDiasHorasLaborables;
     private Set<DetallePlanProduccion> TDetallesPlans = new HashSet<DetallePlanProduccion>(0);
     private Set<Cargo> TCargoses = new HashSet<Cargo>(0);
     private Set<DiaHoraLaborable> TDiasHoraLaborables = new HashSet<DiaHoraLaborable>(0);
     private Set<Cobro> TCobroses = new HashSet<Cobro>(0);
     private Set<AsistenciaEmpleado> TAsistenciasEmpleados = new HashSet<AsistenciaEmpleado>(0);
     private Set<Factura> TFacturases = new HashSet<Factura>(0);
     private Set<PlanProduccion> TPlanesProduccions = new HashSet<PlanProduccion>(0);

    public Empleado() {
    }

	
    public Empleado(int idEmpleado, Domicilio TDomicilios, TipoDocumento TTdocumento, Usuario TUsuarios, Sexo TSexos, String nombre, String apellido, Date fecNacimiento, int numeroDocumento) {
        this.idEmpleado = idEmpleado;
        this.TDomicilios = TDomicilios;
        this.TTdocumento = TTdocumento;
        this.TUsuarios = TUsuarios;
        this.TSexos = TSexos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNacimiento = fecNacimiento;
        this.numeroDocumento = numeroDocumento;
    }
    public Empleado(int idEmpleado, Domicilio TDomicilios, TipoDocumento TTdocumento, Usuario TUsuarios, Sexo TSexos, String nombre, String apellido, Long celular, String correoElectronico, Date fecNacimiento, int numeroDocumento, Long telefono, Integer legajo, String observaciones, Integer idAsistenciaEmpleado, Short idDiasHorasLaborables, Set<DetallePlanProduccion> TDetallesPlans, Set<Cargo> TCargoses, Set<DiaHoraLaborable> TDiasHoraLaborables, Set<Cobro> TCobroses, Set<AsistenciaEmpleado> TAsistenciasEmpleados, Set<Factura> TFacturases, Set<PlanProduccion> TPlanesProduccions) {
       this.idEmpleado = idEmpleado;
       this.TDomicilios = TDomicilios;
       this.TTdocumento = TTdocumento;
       this.TUsuarios = TUsuarios;
       this.TSexos = TSexos;
       this.nombre = nombre;
       this.apellido = apellido;
       this.celular = celular;
       this.correoElectronico = correoElectronico;
       this.fecNacimiento = fecNacimiento;
       this.numeroDocumento = numeroDocumento;
       this.telefono = telefono;
       this.legajo = legajo;
       this.observaciones = observaciones;
       this.idAsistenciaEmpleado = idAsistenciaEmpleado;
       this.idDiasHorasLaborables = idDiasHorasLaborables;
       this.TDetallesPlans = TDetallesPlans;
       this.TCargoses = TCargoses;
       this.TDiasHoraLaborables = TDiasHoraLaborables;
       this.TCobroses = TCobroses;
       this.TAsistenciasEmpleados = TAsistenciasEmpleados;
       this.TFacturases = TFacturases;
       this.TPlanesProduccions = TPlanesProduccions;
    }
   
     @Id 
    
    @Column(name="ID_EMPLEADO", unique=true, nullable=false, precision=5, scale=0)
    public int getIdEmpleado() {
        return this.idEmpleado;
    }
    
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_DOMICILIO", nullable=false)
    public Domicilio getTDomicilios() {
        return this.TDomicilios;
    }
    
    public void setTDomicilios(Domicilio TDomicilios) {
        this.TDomicilios = TDomicilios;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TDOCUMENTO", nullable=false)
    public TipoDocumento getTTdocumento() {
        return this.TTdocumento;
    }
    
    public void setTTdocumento(TipoDocumento TTdocumento) {
        this.TTdocumento = TTdocumento;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_USUARIO", nullable=false)
    public Usuario getTUsuarios() {
        return this.TUsuarios;
    }
    
    public void setTUsuarios(Usuario TUsuarios) {
        this.TUsuarios = TUsuarios;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_SEXO", nullable=false)
    public Sexo getTSexos() {
        return this.TSexos;
    }
    
    public void setTSexos(Sexo TSexos) {
        this.TSexos = TSexos;
    }
    
    @Column(name="NOMBRE", nullable=false, length=100)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="APELLIDO", nullable=false, length=100)
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    @Column(name="CELULAR", precision=13, scale=0)
    public Long getCelular() {
        return this.celular;
    }
    
    public void setCelular(Long celular) {
        this.celular = celular;
    }
    
    @Column(name="CORREO_ELECTRONICO", length=50)
    public String getCorreoElectronico() {
        return this.correoElectronico;
    }
    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_NACIMIENTO", nullable=false, length=23)
    public Date getFecNacimiento() {
        return this.fecNacimiento;
    }
    
    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }
    
    @Column(name="NUMERO_DOCUMENTO", nullable=false, precision=8, scale=0)
    public int getNumeroDocumento() {
        return this.numeroDocumento;
    }
    
    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    @Column(name="TELEFONO", precision=11, scale=0)
    public Long getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    
    @Column(name="LEGAJO", precision=5, scale=0)
    public Integer getLegajo() {
        return this.legajo;
    }
    
    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }
    
    @Column(name="OBSERVACIONES", length=200)
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Column(name="ID_ASISTENCIA_EMPLEADO", precision=5, scale=0)
    public Integer getIdAsistenciaEmpleado() {
        return this.idAsistenciaEmpleado;
    }
    
    public void setIdAsistenciaEmpleado(Integer idAsistenciaEmpleado) {
        this.idAsistenciaEmpleado = idAsistenciaEmpleado;
    }
    
    @Column(name="ID_DIAS_HORAS_LABORABLES", precision=3, scale=0)
    public Short getIdDiasHorasLaborables() {
        return this.idDiasHorasLaborables;
    }
    
    public void setIdDiasHorasLaborables(Short idDiasHorasLaborables) {
        this.idDiasHorasLaborables = idDiasHorasLaborables;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<DetallePlanProduccion> getTDetallesPlans() {
        return this.TDetallesPlans;
    }
    
    public void setTDetallesPlans(Set<DetallePlanProduccion> TDetallesPlans) {
        this.TDetallesPlans = TDetallesPlans;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<Cargo> getTCargoses() {
        return this.TCargoses;
    }
    
    public void setTCargoses(Set<Cargo> TCargoses) {
        this.TCargoses = TCargoses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<DiaHoraLaborable> getTDiasHoraLaborables() {
        return this.TDiasHoraLaborables;
    }
    
    public void setTDiasHoraLaborables(Set<DiaHoraLaborable> TDiasHoraLaborables) {
        this.TDiasHoraLaborables = TDiasHoraLaborables;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<Cobro> getTCobroses() {
        return this.TCobroses;
    }
    
    public void setTCobroses(Set<Cobro> TCobroses) {
        this.TCobroses = TCobroses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<AsistenciaEmpleado> getTAsistenciasEmpleados() {
        return this.TAsistenciasEmpleados;
    }
    
    public void setTAsistenciasEmpleados(Set<AsistenciaEmpleado> TAsistenciasEmpleados) {
        this.TAsistenciasEmpleados = TAsistenciasEmpleados;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<Factura> getTFacturases() {
        return this.TFacturases;
    }
    
    public void setTFacturases(Set<Factura> TFacturases) {
        this.TFacturases = TFacturases;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<PlanProduccion> getTPlanesProduccions() {
        return this.TPlanesProduccions;
    }
    
    public void setTPlanesProduccions(Set<PlanProduccion> TPlanesProduccions) {
        this.TPlanesProduccions = TPlanesProduccions;
    }




}


