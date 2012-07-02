package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Administracion.Empleado;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TSexos generated by hbm2java
 */
@Entity
@Table(name = "T_SEXOS", schema = "dbo", catalog = "Ramaty")
public class Sexo implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_SEXO", unique = true, nullable = false, precision = 2, scale = 0)
    private short idSexo;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSexos")
    private Set<Empleado> TEmpleadoses = new HashSet<Empleado>(0);

    public Sexo() {
    }

    public Sexo(short idSexo, String nombre) {
        this.idSexo = idSexo;
        this.nombre = nombre;
    }

    public Sexo(short idSexo, String nombre, String descripcion, Set<Empleado> TEmpleadoses) {
        this.idSexo = idSexo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.TEmpleadoses = TEmpleadoses;
    }

    public short getIdSexo() {
        return this.idSexo;
    }

    public void setIdSexo(short idSexo) {
        this.idSexo = idSexo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Empleado> getTEmpleadoses() {
        return this.TEmpleadoses;
    }

    public void setTEmpleadoses(Set<Empleado> TEmpleadoses) {
        this.TEmpleadoses = TEmpleadoses;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
