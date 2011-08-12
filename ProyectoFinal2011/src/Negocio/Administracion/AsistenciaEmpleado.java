package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TAsistenciasEmpleado generated by hbm2java
 */
@Entity
@Table(name="T_ASISTENCIAS_EMPLEADO"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class AsistenciaEmpleado  implements java.io.Serializable {


     private short idAsistenciaEmpleado;
     private Empleado TEmpleados;
     private Date fecAsistencia;
     private String horaIngreso;
     private Date fecEgreso;
     private String observaciones;

    public AsistenciaEmpleado() {
    }

	
    public AsistenciaEmpleado(short idAsistenciaEmpleado, Empleado TEmpleados, Date fecAsistencia, String horaIngreso) {
        this.idAsistenciaEmpleado = idAsistenciaEmpleado;
        this.TEmpleados = TEmpleados;
        this.fecAsistencia = fecAsistencia;
        this.horaIngreso = horaIngreso;
    }
    public AsistenciaEmpleado(short idAsistenciaEmpleado, Empleado TEmpleados, Date fecAsistencia, String horaIngreso, Date fecEgreso, String observaciones) {
       this.idAsistenciaEmpleado = idAsistenciaEmpleado;
       this.TEmpleados = TEmpleados;
       this.fecAsistencia = fecAsistencia;
       this.horaIngreso = horaIngreso;
       this.fecEgreso = fecEgreso;
       this.observaciones = observaciones;
    }
   
     @Id 
    
    @Column(name="ID_ASISTENCIA_EMPLEADO", unique=true, nullable=false, precision=4, scale=0)
    public short getIdAsistenciaEmpleado() {
        return this.idAsistenciaEmpleado;
    }
    
    public void setIdAsistenciaEmpleado(short idAsistenciaEmpleado) {
        this.idAsistenciaEmpleado = idAsistenciaEmpleado;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_EMPLEADO", nullable=false)
    public Empleado getTEmpleados() {
        return this.TEmpleados;
    }
    
    public void setTEmpleados(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_ASISTENCIA", nullable=false, length=23)
    public Date getFecAsistencia() {
        return this.fecAsistencia;
    }
    
    public void setFecAsistencia(Date fecAsistencia) {
        this.fecAsistencia = fecAsistencia;
    }
    
    @Column(name="HORA_INGRESO", nullable=false, length=20)
    public String getHoraIngreso() {
        return this.horaIngreso;
    }
    
    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_EGRESO", length=23)
    public Date getFecEgreso() {
        return this.fecEgreso;
    }
    
    public void setFecEgreso(Date fecEgreso) {
        this.fecEgreso = fecEgreso;
    }
    
    @Column(name="OBSERVACIONES", length=200)
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }




}


