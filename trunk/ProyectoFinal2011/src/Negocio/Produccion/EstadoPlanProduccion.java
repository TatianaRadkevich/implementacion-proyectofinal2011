package Negocio.Produccion;
// Generated 31-oct-2011 20:10:21 by Hibernate Tools 3.2.1.GA

import BaseDeDatos.HibernateUtil;
import java.util.HashSet;
import java.util.List;
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
 * TEplanProduccion generated by hbm2java
 */
@Entity
@Table(name = "T_EPLAN_PRODUCCION", schema = "dbo", catalog = "Ramaty")
public class EstadoPlanProduccion implements java.io.Serializable {



    @Id
    @GeneratedValue
    @Column(name = "ID_EPLAN_PRODUCCION", unique = true, nullable = false, precision = 2, scale = 0)
    private short idEplanProduccion;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEplanProduccion")
    private Set<PlanProduccion> TPlanesProduccions = new HashSet<PlanProduccion>(0);

    private static final String EP_Iniciado="Iniciado";
    
    public EstadoPlanProduccion() {
    }

    public EstadoPlanProduccion(byte idEplanProduccion, String nombre) {
        this.idEplanProduccion = idEplanProduccion;
        this.nombre = nombre;
    }

    public EstadoPlanProduccion(byte idEplanProduccion, String nombre, String descripcion, Set<PlanProduccion> TPlanesProduccions) {
        this.idEplanProduccion = idEplanProduccion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.TPlanesProduccions = TPlanesProduccions;
    }

    public short getIdEplanProduccion() {
        return this.idEplanProduccion;
    }

    public void setIdEplanProduccion(short idEplanProduccion) {
        this.idEplanProduccion = idEplanProduccion;
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

    public Set<PlanProduccion> getTPlanesProduccions() {
        return this.TPlanesProduccions;
    }

    public void setTPlanesProduccions(Set<PlanProduccion> TPlanesProduccions) {
        this.TPlanesProduccions = TPlanesProduccions;
    }

    public static EstadoPlanProduccion getEstadoIniciado() {
        return getEstadoPlan(EP_Iniciado);
    }
    
    private static EstadoPlanProduccion getEstadoPlan(String nombre)
    {
        String HQL=String.format("FROM EstadoPlanProduccion as ep WHERE LOWER(ep.nombre) = LOWER('%s')", nombre);
        List<EstadoPlanProduccion> lst=HibernateUtil.ejecutarConsulta(HQL);
        if(lst.isEmpty())
            throw new RuntimeException("No existe un \"estado detalle plan\" con el nombre de : "+nombre);

        return lst.get(0);
    }
}
