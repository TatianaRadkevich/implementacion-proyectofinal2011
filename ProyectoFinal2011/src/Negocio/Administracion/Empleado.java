package Negocio.Administracion;
// Generated 19/08/2011 20:33:26 by Hibernate Tools 3.2.1.GA

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.HibernateUtil;
import Negocio.Exceptiones.NegocioException;
import Negocio.GestionUsuario.Usuario;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.PlanProduccion;
import Negocio.Exceptiones.TipoDatoException;
import Negocio.UbicacionGeografica.Domicilio;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

/**
 * TEmpleados generated by hbm2java
 */
@Entity
@Table(name = "T_EMPLEADOS", schema = "dbo", catalog = "Ramaty")
public class Empleado implements java.io.Serializable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue
    @Column(name = "ID_EMPLEADO", unique = true, nullable = false, precision = 5, scale = 0)
    private int idEmpleado;
    /*---------------------------------------------------------------------------------------------*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DOMICILIO"/*, nullable=false*/)
    private Domicilio TDomicilios;
    /*---------------------------------------------------------------------------------------------*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TDOCUMENTO", nullable = false)
    private TipoDocumento TTdocumento;
    /*---------------------------------------------------------------------------------------------*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO"/*, nullable=false*/)
    private Usuario TUsuarios;
    /*---------------------------------------------------------------------------------------------*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SEXO", nullable = false)
    private Sexo TSexos;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "CELULAR", precision = 13, scale = 0)
    private Long celular;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "CORREO_ELECTRONICO", length = 50)
    private String correoElectronico;
    /*---------------------------------------------------------------------------------------------*/
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_NACIMIENTO", nullable = false, length = 23)
    private Date fecNacimiento;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "NUMERO_DOCUMENTO", nullable = false, precision = 8, scale = 0)
    private Integer numeroDocumento;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "TELEFONO", precision = 11, scale = 0)
    private Long telefono;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "OBSERVACIONES", length = 200)
    private String observaciones;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "ID_ASISTENCIA_EMPLEADO", precision = 5, scale = 0)
    private Integer idAsistenciaEmpleado;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "ID_DIAS_HORAS_LABORABLES", precision = 3, scale = 0)
    private Short idDiasHorasLaborables;
    /*---------------------------------------------------------------------------------------------*/
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_BAJA", length = 23)
    private Date fecBaja;
    /*---------------------------------------------------------------------------------------------*/
    @Column(name = "MOTIVO_BAJA", length = 100)
    private String motivoBaja;
    /*---------------------------------------------------------------------------------------------*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEmpleados")
    private Set<Cobro> TCobroses = new HashSet(0);
    /*---------------------------------------------------------------------------------------------*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEmpleados")
    private Set<Factura> TFacturases = new HashSet(0);
    /*---------------------------------------------------------------------------------------------*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEmpleados")
    private Set<DetallePlanProduccion> TDetallesPlans = new HashSet(0);
    /*---------------------------------------------------------------------------------------------*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEmpleados")
    private Set<PlanProduccion> TPlanesProduccions = new HashSet(0);
    /*---------------------------------------------------------------------------------------------*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEmpleados")
    private Set<DiaHoraLaborable> TDiasHoraLaborables = new HashSet(0);
    /*---------------------------------------------------------------------------------------------*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEmpleados")
    private Set<AsistenciaEmpleado> TAsistenciasEmpleados = new HashSet(0);
    /*---------------------------------------------------------------------------------------------*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEmpleados")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<EmpleadosXCargo> TEmpleadosXCargos = new HashSet(0);
    /*---------------------------------------------------------------------------------------------*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO_EMPLEADO", nullable = false)
    private EstadosEmpleado TEstadosEmpleado;
    /*---------------------------------------------------------------------------------------------*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empleado")
    @OrderBy("fecDesde ASC")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<AsignacionesHorario> asignacionesHorarias = new HashSet(0);
    /*---------------------------------------------------------------------------------------------*/
// </editor-fold>

    public Empleado() {
    }

    // <editor-fold defaultstate="collapsed" desc="set/get">
    public int getId() {
        return this.idEmpleado;
    }

    public Domicilio getDomicilio() {
        return this.TDomicilios;
    }

    public void setDomicilio(Domicilio TDomicilios) {
        this.TDomicilios = TDomicilios;
    }

    public TipoDocumento getTipoDocumento() {
        return this.TTdocumento;
    }

    public void setTipoDocumento(TipoDocumento TTdocumento) throws TipoDatoException {
        if (TTdocumento != null) {
            this.TTdocumento = TTdocumento;
        } else {
            throw new TipoDatoException("Debe seleccionar un tipo documento");
        }
    }

    public Usuario getUsuario() {
        return this.TUsuarios;
    }

    public void setUsuario(Usuario TUsuarios) {
        this.TUsuarios = TUsuarios;
    }

    public Sexo getSexo() {
        return this.TSexos;
    }

    public void setSexo(Sexo sex) throws TipoDatoException {
        if (TTdocumento != null) {
            this.TSexos = sex;
        } else {
            throw new TipoDatoException("Debe seleccionar el sexo");
        }

    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) throws TipoDatoException {
        if (nombre.matches("[a-zA-Z ]*") && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            this.nombre = null;
            throw new TipoDatoException("Formato incorrecto. Debe ser alfabético");
        }


    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) throws TipoDatoException {
        if (apellido.matches("[a-zA-Z ]*") && !apellido.trim().isEmpty()) {
            this.apellido = apellido;
        } else {
            this.apellido = null;
            throw new TipoDatoException("Formato incorrecto. Debe ser alfabético");
        }
    }

    public String getApellidoNombre() {
        return this.apellido + ", " + this.nombre;
    }

    public Long getCelular() {
        return this.celular;
    }

    public void setCelular(Long celular) throws TipoDatoException {
//        if(celular==0)
//            throw new TipoDatoException("Formato incorrecto. Debe ser numérico");
        this.celular = celular;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) throws TipoDatoException {
        if (!correoElectronico.trim().isEmpty() && correoElectronico.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
                + "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
                + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
                + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
                + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
                + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
                + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
                + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            this.correoElectronico = correoElectronico;
        } else {
            this.correoElectronico = null;
            throw new TipoDatoException("Formato incorrecto.");
        }
    }

    public Date getFecNacimiento() {
        return this.fecNacimiento;
    }

    public void setFecNacimiento(Date fecha) {
        Utilidades.validarNULL(fecha);
        Date fMin = Utilidades.agregarTiempoFecha(Utilidades.getFechaActual(), 0, 0, -18);
        if (fecha.compareTo(fMin) > 0) {
            throw new NegocioException("El empleado debe tener más de 18 años");
        }
        this.fecNacimiento = fecha;
    }

    public Integer getNumeroDocumento() {

        return this.numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) throws TipoDatoException {
        if (numeroDocumento == null || numeroDocumento == 0) {
            this.numeroDocumento = null;
            throw new TipoDatoException("Formato incorrecto. Debe ser numérico");
        }
        this.numeroDocumento = numeroDocumento;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public void setTelefono(Long telefono) throws TipoDatoException {
        if (telefono == null || telefono == 0) {
            this.telefono = null;
            throw new TipoDatoException("Formato incorrecto. Debe ser numérico");
        }

        this.telefono = telefono;

    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdAsistenciaEmpleado() {
        return this.idAsistenciaEmpleado;
    }

    public void setIdAsistenciaEmpleado(Integer idAsistenciaEmpleado) {
        this.idAsistenciaEmpleado = idAsistenciaEmpleado;
    }

    public Short getIdDiasHorasLaborables() {
        return this.idDiasHorasLaborables;
    }

    public void setIdDiasHorasLaborables(Short idDiasHorasLaborables) {
        this.idDiasHorasLaborables = idDiasHorasLaborables;
    }

    public Date getFecBaja() {
        return this.fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public String getMotivoBaja() {
        return this.motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public EstadosEmpleado getEstado() {
        return this.TEstadosEmpleado;
    }

    public void setEstado(EstadosEmpleado TEstadosEmpleado) {
        this.TEstadosEmpleado = TEstadosEmpleado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="set/get Relaciones Indirectas">
    public Set<Cobro> getCobros() {
        return this.TCobroses;
    }

    public void setCobros(Set<Cobro> TCobroses) {
        this.TCobroses = TCobroses;
    }

    public Set<Factura> getFacturas() {
        return this.TFacturases;
    }

    public void setFacturas(Set<Factura> TFacturases) {
        this.TFacturases = TFacturases;
    }

    public Set<DetallePlanProduccion> getTDetallesPlans() {
        return this.TDetallesPlans;
    }

    public void setTDetallesPlans(Set<DetallePlanProduccion> TDetallesPlans) {
        this.TDetallesPlans = TDetallesPlans;
    }

    public Set<PlanProduccion> getTPlanesProduccions() {
        return this.TPlanesProduccions;
    }

    public void setTPlanesProduccions(Set<PlanProduccion> TPlanesProduccions) {
        this.TPlanesProduccions = TPlanesProduccions;
    }

    public Set<DiaHoraLaborable> getTDiasHoraLaborables() {
        return this.TDiasHoraLaborables;
    }

    public void setTDiasHoraLaborables(Set<DiaHoraLaborable> TDiasHoraLaborables) {
        this.TDiasHoraLaborables = TDiasHoraLaborables;
    }

    public List<AsistenciaEmpleado> getAsistencias() {
        return new ArrayList<AsistenciaEmpleado>(this.TAsistenciasEmpleados);
    }

    public AsistenciaEmpleado getUltimaAsistencia() {
        AsistenciaEmpleado salida = null;
        for (AsistenciaEmpleado ae : getAsistencias()) {
            if (salida == null || salida.getFecAsistencia().compareTo(ae.getFecAsistencia()) < 0) {
                salida = ae;
            }
        }
        return salida;
    }

    public void setAsistencias(Set<AsistenciaEmpleado> TAsistenciasEmpleados) {
        this.TAsistenciasEmpleados = TAsistenciasEmpleados;
    }

    public List<Cargo> getCargos() {
        ArrayList<Cargo> salida = new ArrayList<Cargo>(TEmpleadosXCargos.size());
        for (EmpleadosXCargo mxp : this.TEmpleadosXCargos) {
            salida.add(mxp.getTCargos());
        }
        return salida;
    }

    public void setCargos(List<Cargo> proveedores) throws TipoDatoException {
        if (proveedores.size() <= 0 || proveedores == null) {
            throw new TipoDatoException("Debe seleccionar un cargo como mínimo.");
        }
        TEmpleadosXCargos.clear();
        for (Cargo c : proveedores) {
            TEmpleadosXCargos.add(new EmpleadosXCargo(this, c));
        }
    }
// </editor-fold>

    @Override
    public String toString() {
        return this.getNombre() + ", " + this.getApellido();
    }

    public boolean estaBaja() {
        if (fecBaja == null) {
            return false;
        }
        return true;

    }

    public boolean validarOk() throws TipoDatoException {
        String mje = "Algunos campos no han sido ingresado correctamente.";
        if (nombre == null) {
            throw new TipoDatoException(mje);
        }
        if (apellido == null) {
            throw new TipoDatoException(mje);
        }
        if (this.TTdocumento == null) {
            throw new TipoDatoException(mje);
        }
        if (this.numeroDocumento == null) {
            throw new TipoDatoException(mje);
        }
        if (this.fecNacimiento == null) {
            throw new TipoDatoException(mje);
        }
        try {
            TDomicilios.validarOk();
        } catch (TipoDatoException ex) {
            throw new TipoDatoException(mje);
        }
        return true;
    }

    public static int getLastID() {
        return EmpleadoBD.getLastID();
    }

    public AsignacionesHorario getAsignacionHorariaVigente() {
        AsignacionesHorario salida = null;
        Date fActual = Utilidades.getFechaActual();
        for (AsignacionesHorario ah : this.asignacionesHorarias) {
            if (ah.getFecDesde().compareTo(fActual) < 0) {
                if (ah.getFecHasta().compareTo(fActual) > 0) {
                    salida = ah;
                    break;
                }
            }
        }
        return salida;
    }

    public ArrayList<AsignacionesHorario> getAsignacionHorariasProximas() {
        ArrayList<AsignacionesHorario> salida = new ArrayList();
        Date fActual = Utilidades.getFechaActual();
        for (AsignacionesHorario ah : this.asignacionesHorarias) {
            if (ah.getFecHasta().compareTo(fActual) > 0) {
                salida.add(ah);
                break;
            }
        }
        Collections.sort(salida);

        return salida;
    }

    public void addAsignacionHorario(AsignacionesHorario asignacionSel) {
        asignacionSel.setEmpleado(this);
        this.asignacionesHorarias.add(asignacionSel);
    }

    public void updateAsignacionHorario(AsignacionesHorario asignacionSel) {
        asignacionSel.setEmpleado(this);
        List aux = getAsignacionHorariasProximas();
        int index = aux.indexOf(this);

        try {
            for (int i = index + 1; i < aux.size(); i++) {
            }
        } catch (Exception e) {
        }
    }

    public void addAsistencia(AsistenciaEmpleado a) {
        a.setEmpleado(this);
        this.TAsistenciasEmpleados.add(a);
    }

    public void removeAsistencia(AsistenciaEmpleado a) {
        a.setEmpleado(null);
        this.TAsistenciasEmpleados.remove(a);
    }

    public boolean isActivo() {
        return this.fecBaja == null;
    }

    public boolean isPresente(Date tiempo) {
        for (AsignacionesHorario ah : asignacionesHorarias) {
            if (tiempo.compareTo(ah.getFecDesde()) >= 0 && tiempo.compareTo(ah.getFecHasta()) <= 0) {
                return ah.getHorario().isPresete(tiempo);
            }
        }
        return false;
    }

    public Date getProxIngreso(Date tiempo) {
        Date salida = null;
        for (AsignacionesHorario ah : asignacionesHorarias) {
            if (tiempo.compareTo(ah.getFecDesde()) >= 0 && tiempo.compareTo(ah.getFecHasta()) <= 0) {
                salida = ah.getHorario().getProximoIngreso(tiempo);
                if (ah.getFecHasta().compareTo(salida) < 0) {
                    AsignacionesHorario aux = this.getProximaAsignacion(ah);
                    salida=aux.getHorario().getProximoIngreso(aux.getFecDesde());
                }
            }
        }
        return salida;
    }

    public Date getProxEgreso(Date tiempo) {
                Date salida = null;
        for (AsignacionesHorario ah : asignacionesHorarias) {
            if (tiempo.compareTo(ah.getFecDesde()) >= 0 && tiempo.compareTo(ah.getFecHasta()) <= 0) {
                salida = ah.getHorario().getProximoEgreso(tiempo);
                if (ah.getFecHasta().compareTo(salida) < 0) {
                    AsignacionesHorario aux = this.getProximaAsignacion(ah);
                    salida=aux.getHorario().getProximoEgreso(aux.getFecDesde());
                }
            }
        }
        return salida;
    }

    private AsignacionesHorario getProximaAsignacion(AsignacionesHorario ah) {
        Iterator<AsignacionesHorario> i = asignacionesHorarias.iterator();
        while (i.hasNext()) {
            AsignacionesHorario aux = i.next();
            if (ah.equals(aux)) {
                if (i.hasNext()) {
                    return i.next();
                }
            }
        }
        return null;
    }
}
