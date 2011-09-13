package Negocio.Administracion;
// Generated 19/08/2011 20:33:26 by Hibernate Tools 3.2.1.GA


import Negocio.GestionUsuario.Usuario;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.PlanProduccion;
import Negocio.UbicacionGeografica.Domicilio;
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
     private String observaciones;
     private Integer idAsistenciaEmpleado;
     private Short idDiasHorasLaborables;
     private Date fecBaja;
     private String motivoBaja;
     private Set<Cobro> TCobroses = new HashSet(0);
     private Set <Factura> TFacturases = new HashSet(0);
     private Set<DetallePlanProduccion> TDetallesPlans = new HashSet(0);
     private Set <PlanProduccion> TPlanesProduccions = new HashSet(0);
     private Set<DiaHoraLaborable> TDiasHoraLaborables = new HashSet(0);
     private Set<AsistenciaEmpleado> TAsistenciasEmpleados = new HashSet(0);
     private Set<TEmpleadosXCargo> TEmpleadosXCargos = new HashSet(0);

    public Empleado() {
        this.TDomicilios= new Domicilio();
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
    public Empleado(int idEmpleado, Domicilio TDomicilios, TipoDocumento TTdocumento, Usuario TUsuarios, Sexo TSexos, String nombre, String apellido, Long celular, String correoElectronico, Date fecNacimiento, int numeroDocumento, Long telefono, String observaciones, Integer idAsistenciaEmpleado, Short idDiasHorasLaborables, Date fecBaja, String motivoBaja, Set TCobroses, Set TFacturases, Set TDetallesPlans, Set TPlanesProduccions, Set TDiasHoraLaborables, Set<AsistenciaEmpleado> TAsistenciasEmpleados, Set TEmpleadosXCargos) {
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
       this.observaciones = observaciones;
       this.idAsistenciaEmpleado = idAsistenciaEmpleado;
       this.idDiasHorasLaborables = idDiasHorasLaborables;
       this.fecBaja = fecBaja;
       this.motivoBaja = motivoBaja;
       this.TCobroses = TCobroses;
       this.TFacturases = TFacturases;
       this.TDetallesPlans = TDetallesPlans;
       this.TPlanesProduccions = TPlanesProduccions;
       this.TDiasHoraLaborables = TDiasHoraLaborables;
       this.TAsistenciasEmpleados = TAsistenciasEmpleados;
       this.TEmpleadosXCargos = TEmpleadosXCargos;
    }

     @Id
    @GeneratedValue
    @Column(name="ID_EMPLEADO", unique=true, nullable=false, precision=5, scale=0)
    public int getIdEmpleado() {
        return this.idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_DOMICILIO"/*, nullable=false*/)
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
    @JoinColumn(name="ID_USUARIO"/*, nullable=false*/)
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<Cobro> getTCobroses() {
        return this.TCobroses;
    }

    public void setTCobroses(Set<Cobro> TCobroses) {
        this.TCobroses = TCobroses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set <Factura> getTFacturases() {
        return this.TFacturases;
    }

    public void setTFacturases(Set <Factura> TFacturases) {
        this.TFacturases = TFacturases;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<DetallePlanProduccion> getTDetallesPlans() {
        return this.TDetallesPlans;
    }

    public void setTDetallesPlans(Set<DetallePlanProduccion> TDetallesPlans) {
        this.TDetallesPlans = TDetallesPlans;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set <PlanProduccion> getTPlanesProduccions() {
        return this.TPlanesProduccions;
    }

    public void setTPlanesProduccions(Set <PlanProduccion> TPlanesProduccions) {
        this.TPlanesProduccions = TPlanesProduccions;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<DiaHoraLaborable> getTDiasHoraLaborables() {
        return this.TDiasHoraLaborables;
    }

    public void setTDiasHoraLaborables(Set<DiaHoraLaborable> TDiasHoraLaborables) {
        this.TDiasHoraLaborables = TDiasHoraLaborables;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<AsistenciaEmpleado> getTAsistenciasEmpleados() {
        return this.TAsistenciasEmpleados;
    }

    public void setTAsistenciasEmpleados(Set<AsistenciaEmpleado> TAsistenciasEmpleados) {
        this.TAsistenciasEmpleados = TAsistenciasEmpleados;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEmpleados")
    public Set<TEmpleadosXCargo> getTEmpleadosXCargos() {
        return this.TEmpleadosXCargos;
    }

    public void setTEmpleadosXCargos(Set<TEmpleadosXCargo> TEmpleadosXCargos) {
        this.TEmpleadosXCargos = TEmpleadosXCargos;
    }


    public Object getInfoColumna(int columnIndex) {

         switch (columnIndex){
             case 0:
                return this.getIdEmpleado();
            case 1:
                return this.getNombre();
            case 2:
                return this.getApellido();
            case 3:
                return this.getTSexos().getNombre();
             case 4:
                return this.getTTdocumento().getNombre();
            case 5:
                return this.getNumeroDocumento();
            case 6:
                 return this.getTelefono();
             case 7:
                 return this.getCorreoElectronico();
                 }
        return null;
        }

    @Override
    public String toString() {
        return this.getNombre()+", "+this.getApellido();
    }

}

