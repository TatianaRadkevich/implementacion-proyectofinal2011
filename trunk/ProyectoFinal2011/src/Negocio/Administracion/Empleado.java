package Negocio.Administracion;
// Generated 19/08/2011 20:33:26 by Hibernate Tools 3.2.1.GA

import Negocio.GestionUsuario.Usuario;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.PlanProduccion;
import Negocio.TipoDatoException;
import Negocio.UbicacionGeografica.Domicilio;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
 * TEmpleados generated by hbm2java
 */
@Entity
@Table(name = "T_EMPLEADOS", schema = "dbo", catalog = "Ramaty")
public class Empleado implements java.io.Serializable {

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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ESTADO_EMPLEADO", nullable=false)

     private EstadosEmpleado TEstadosEmpleado;
     /*---------------------------------------------------------------------------------------------*/
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ASIGNACION_HORARIO", nullable=true)
     private AsignacionesHorario TAsignacionesHorario;
     /*---------------------------------------------------------------------------------------------*/

    
    public Empleado() {
        this.TDomicilios = new Domicilio();
    }

    public Empleado(int idEmpleado, Domicilio TDomicilios, TipoDocumento TTdocumento, Usuario TUsuarios, Sexo TSexos, String nombre, String apellido, Date fecNacimiento, int numeroDocumento,EstadosEmpleado TEstadosEmpleado, AsignacionesHorario TAsignacionesHorario) {
        this.idEmpleado = idEmpleado;
        this.TDomicilios = TDomicilios;
        this.TTdocumento = TTdocumento;
        this.TUsuarios = TUsuarios;
        this.TSexos = TSexos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNacimiento = fecNacimiento;
        this.numeroDocumento = numeroDocumento;
        this.TEstadosEmpleado = TEstadosEmpleado;
        this.TAsignacionesHorario = TAsignacionesHorario;
    }

    public Empleado(int idEmpleado, Domicilio TDomicilios, TipoDocumento TTdocumento, Usuario TUsuarios, Sexo TSexos, String nombre, String apellido, Long celular, String correoElectronico, Date fecNacimiento, int numeroDocumento, Long telefono, String observaciones, Integer idAsistenciaEmpleado, Short idDiasHorasLaborables, Date fecBaja, String motivoBaja, Set TCobroses, Set TFacturases, Set TDetallesPlans, Set TPlanesProduccions, Set TDiasHoraLaborables, Set<AsistenciaEmpleado> TAsistenciasEmpleados, Set TEmpleadosXCargos,EstadosEmpleado TEstadosEmpleado, AsignacionesHorario TAsignacionesHorario) {
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
        this.TEstadosEmpleado = TEstadosEmpleado;
        this.TAsignacionesHorario = TAsignacionesHorario;
    }

    public int getIdEmpleado() {
        return this.idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Domicilio getTDomicilios() {
        return this.TDomicilios;
    }

    public void setTDomicilios(Domicilio TDomicilios) {
        this.TDomicilios = TDomicilios;
    }

    public TipoDocumento getTTdocumento() {
        return this.TTdocumento;
    }

    public void setTTdocumento(TipoDocumento TTdocumento) throws TipoDatoException{
        if(TTdocumento!=null)
            this.TTdocumento = TTdocumento;
        else
            throw new TipoDatoException("Debe seleccionar un tipo documento");
    }

    public Usuario getTUsuarios() {
        return this.TUsuarios;
    }

    public void setTUsuarios(Usuario TUsuarios) {
        this.TUsuarios = TUsuarios;
    }

    public Sexo getTSexos()  {
        return this.TSexos;
    }

    public void setTSexos(Sexo TSexos) throws TipoDatoException{
        if(TTdocumento!=null)
            this.TSexos = TSexos;
        else
            throw new TipoDatoException("Debe seleccionar el sexo");
        
    } 

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) throws TipoDatoException{
        if(nombre.matches("[a-zA-Z ]*") && !nombre.trim().isEmpty())
            this.nombre = nombre;
        else
        {
            this.nombre=null;
            throw new TipoDatoException("Formato incorrecto. Debe ser alfabético");
        }
            
        
    }

    public String getApellido() {

        return this.apellido;
    }

    public void setApellido(String apellido)  throws TipoDatoException {
       if(apellido.matches("[a-zA-Z ]*") && !apellido.trim().isEmpty())
            this.apellido = apellido;
        else{
           this.apellido=null;
           throw new TipoDatoException("Formato incorrecto. Debe ser alfabético");
            }       
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
       if( !correoElectronico.trim().isEmpty() && correoElectronico.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
                + "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
                + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
                + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
                + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
                + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
                + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
                + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") )
            this.correoElectronico = correoElectronico;
        else{
           this.correoElectronico=null;
           throw new TipoDatoException("Formato incorrecto.");
        }
    }

    public Date getFecNacimiento() {
        return this.fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) throws TipoDatoException {
        Date today= new Date();

        int var=today.getYear()-fecNacimiento.getYear();
        if(fecNacimiento!=null ){
            if(var==18){
                if (today.getMonth() ==fecNacimiento.getMonth())                
                {
                    if(today.getDate()==fecNacimiento.getDate())
                    {
                        this.fecNacimiento = fecNacimiento;
                        System.out.println("Por fin entro......");
                    }  
                    else{
                        if(today.getDate()>fecNacimiento.getDate())
                        {
                            this.fecNacimiento = fecNacimiento;
                            System.out.println("Por fin entro......");
                        }  
                        else{
                            this.fecNacimiento=null;
                            System.out.println("Es menor de 18 años");
                            throw new TipoDatoException("El empleado debe ser mayor de 18 años");
                        }
                    }

                     
                }
                else{
                    if(today.getMonth()>fecNacimiento.getMonth())
                    {
                        this.fecNacimiento = fecNacimiento;
                        System.out.println("Por fin entro......");
                    }
                    else{
                        this.fecNacimiento=null;
                        System.out.println("Es menor de 18 años");
                        throw new TipoDatoException("El empleado debe ser mayor de 18 años");
                    }
                    
                }
           }
            else{
                if (var>18)
                {
                    this.fecNacimiento = fecNacimiento;
                    System.out.println("Por fin entro......");
                    }
                else{
                    this.fecNacimiento=null;
                    System.out.println("Es menor de 18 años");
                    throw new TipoDatoException("El empleado debe ser mayor de 18 años");
                }
            }
            
        }
        else{
            this.fecNacimiento=null;
            System.out.println("Es menor de 18 años");
            throw new TipoDatoException("Formato incorrecto. Seleccionar una fecha");
        }

        
    }

    public int getNumeroDocumento() {

        return this.numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento)  throws TipoDatoException {
       if(numeroDocumento==0){
           this.numeroDocumento=null;
           throw new TipoDatoException("Formato incorrecto. Debe ser numérico");
       }
        this.numeroDocumento = numeroDocumento;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public void setTelefono(Long telefono) throws TipoDatoException {
         if(telefono==0)
         {
             this.telefono=null;
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

    public Set<Cobro> getTCobroses() {
        return this.TCobroses;
    }

    public void setTCobroses(Set<Cobro> TCobroses) {
        this.TCobroses = TCobroses;
    }

    public Set<Factura> getTFacturases() {
        return this.TFacturases;
    }

    public void setTFacturases(Set<Factura> TFacturases) {
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

    public Set<AsistenciaEmpleado> getTAsistenciasEmpleados() {
        return this.TAsistenciasEmpleados;
    }

    public void setTAsistenciasEmpleados(Set<AsistenciaEmpleado> TAsistenciasEmpleados) {
        this.TAsistenciasEmpleados = TAsistenciasEmpleados;
    }

//    public Set<EmpleadosXCargo> getTEmpleadosXCargos() {
//        return this.TEmpleadosXCargos;
//    }
//
//    public void setTEmpleadosXCargos(Set<EmpleadosXCargo> TEmpleadosXCargos) {
//        this.TEmpleadosXCargos = TEmpleadosXCargos;
//    }

    public List<Cargo> getCargos() {
        ArrayList<Cargo> salida = new ArrayList<Cargo>(TEmpleadosXCargos.size());
        for (EmpleadosXCargo mxp : this.TEmpleadosXCargos) {
            salida.add(mxp.getTCargos());
        }
        return salida;
    }

    public void setCargos(List<Cargo> proveedores) throws TipoDatoException{
        if(proveedores.size()<=0 || proveedores==null){
            throw new TipoDatoException("Debe seleccionar un cargo como mínimo.");
        }
        TEmpleadosXCargos.clear();
        for (Cargo c : proveedores) {
            TEmpleadosXCargos.add(new EmpleadosXCargo(this, c));
        }
    }

    public Object getInfoColumna(int columnIndex) {

        switch (columnIndex) {
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
        return this.getNombre() + ", " + this.getApellido();
    }

     public boolean estaBaja() {
        if (fecBaja == null) {
            return false;
        }
        return true;

    }
     
    public EstadosEmpleado getTEstadosEmpleado() {
        return this.TEstadosEmpleado;
    }

    public void setTEstadosEmpleado(EstadosEmpleado TEstadosEmpleado) {
        this.TEstadosEmpleado = TEstadosEmpleado;
    }

    public AsignacionesHorario getTAsignacionesHorario() {
        return this.TAsignacionesHorario;
    }

    public void setTAsignacionesHorario(AsignacionesHorario TAsignacionesHorario) {
        this.TAsignacionesHorario = TAsignacionesHorario;
    }

    public boolean validarOk() throws TipoDatoException{
        String mje="Algunos campos no han sido ingresado correctamente.";
        if(nombre==null)
            throw new TipoDatoException(mje);
        if(apellido==null)
            throw new TipoDatoException(mje);
        if(this.TTdocumento==null)
            throw new TipoDatoException(mje);
        if(this.numeroDocumento==null)
            throw new TipoDatoException(mje);
        if(this.fecNacimiento==null)
            throw new TipoDatoException(mje);
      try{
          TDomicilios.validarOk();
      }catch(TipoDatoException ex){
      throw new TipoDatoException(mje);}
            


        return true;
    }
}
