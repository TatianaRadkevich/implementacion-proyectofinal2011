package Negocio.Administracion;
// Generated 17/08/2011 17:29:51 by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TEmpleadosXCargo generated by hbm2java
 */
@Entity
@Table(name = "T_EMPLEADOS_X_CARGO", schema = "dbo", catalog = "Ramaty")
public class TEmpleadosXCargo implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_EMPLEADO_X_CARGO", unique = true, nullable = false, precision = 5, scale = 0)
    private int idEmpleadoXCargo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", nullable = false)
    private Empleado TEmpleados;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARGO", nullable = false)
    private Cargo TCargos;

    public TEmpleadosXCargo() {
    }

    public TEmpleadosXCargo(Empleado TEmpleados, Cargo TCargos) {

        this.TEmpleados = TEmpleados;
        this.TCargos = TCargos;
    }

    public TEmpleadosXCargo(int idEmpleadoXCargo, Empleado TEmpleados, Cargo TCargos) {
        this.idEmpleadoXCargo = idEmpleadoXCargo;
        this.TEmpleados = TEmpleados;
        this.TCargos = TCargos;
    }

    public int getIdEmpleadoXCargo() {
        return this.idEmpleadoXCargo;
    }

    public void setIdEmpleadoXCargo(int idEmpleadoXCargo) {
        this.idEmpleadoXCargo = idEmpleadoXCargo;
    }

    public Empleado getTEmpleados() {
        return this.TEmpleados;
    }

    public void setTEmpleados(Empleado TEmpleados) {
        this.TEmpleados = TEmpleados;
    }

    public Cargo getTCargos() {
        return this.TCargos;
    }

    public void setTCargos(Cargo TCargos) {
        this.TCargos = TCargos;
    }
}
