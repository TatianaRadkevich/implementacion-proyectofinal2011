package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

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
 * TAsistenciasEmpleado generated by hbm2java
 */
@Entity
@Table(name = "T_ASISTENCIAS_EMPLEADO", schema = "dbo", catalog = "Ramaty")
public class AsistenciaEmpleado implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_ASISTENCIA_EMPLEADO", unique = true, nullable = false, precision = 4, scale = 0)
    private int id;
    //-------------------------------------------------------------------------*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", nullable = false)
    private Empleado TEmpleados;
    //-------------------------------------------------------------------------*
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_ASISTENCIA", nullable = false)
    private Date fecAsistencia;
    //-------------------------------------------------------------------------*
    @Column(name = "HORA_INGRESO", length = 20)
    private String horaIngreso;
    //-------------------------------------------------------------------------*
    @Column(name = "HORA_EGRESO", length = 20)
    private String horaEgreso;
    //-------------------------------------------------------------------------*
    @Column(name = "OBSERVACIONES_INGRESO", length = 200)
    private String observIngreso;
    //-------------------------------------------------------------------------*
    @Column(name = "OBSERVACIONES_EGRESO", length = 200)
    private String observEgreso;

    public AsistenciaEmpleado() {
    }

     public int getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return TEmpleados;
    }

    public void setEmpleado(Empleado empleado) {
        this.TEmpleados = empleado;
    }

    public Date getFecAsistencia() {
        return fecAsistencia;
    }

    public void setFecAsistencia(Date fecAsistencia) {
        this.fecAsistencia = fecAsistencia;
    }

    public String getHoraEgreso() {
        return (horaEgreso==null)?"":horaEgreso;
    }

    public void setHoraEgreso(String horaEgreso) {
        this.horaEgreso = horaEgreso;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getObservEgreso() {
        return observEgreso;
    }

    public void setObservEgreso(String observEgreso) {
        this.observEgreso = observEgreso;
    }

    public String getObservIngreso() {
        return observIngreso;
    }

    public void setObservIngreso(String observIngreso) {
        this.observIngreso = observIngreso;
    }


}
