package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Produccion.DetallePlanProduccion;
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
 * TMaquinasHerramientaParticular generated by hbm2java
 */
@Entity
@Table(name = "T_MAQUINAS_HERRAMIENTA_PARTICULAR", schema = "dbo", catalog = "Ramaty")
public class MaquinaHerramientaParticular implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_MAQUINA_HERRAMIENTA_PARTICULAR", unique = true, nullable = false, precision = 3, scale = 0)
    private short idMaquinaHerramientaParticular;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TMAQUINA_HERRAMIENTA", nullable = false)
    private TipoMaquinaHerramienta TTmaquinaHerramienta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMAQUINA", nullable = false)
    private EstadoMaquina TEmaquina;
    @Column(name = "CAPACIDAD_PRODUCTIVA", nullable = false, precision = 5, scale = 0)
    private Integer capacidadProductiva;
    @Column(name = "CARACTERISTICAS", nullable = false, length = 200)
    private String caracteristicas;
    @Column(name = "MODELO", nullable = false, length = 50)
    private String modelo;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "OBSERVACIONES", length = 200)
    private String observaciones;
    @Column(name = "CODIGO", nullable = false, length = 2)
    private String codigo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_BAJA")
    private Date fecBaja;
    @Column(name="MOTIVO_BAJA", length=100)
    private String motivoBaja;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TMaquinasHerramientaParticular")
    private Set<DetallePlanProduccion> TDetallesPlans = new HashSet<DetallePlanProduccion>(0);

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TMaquinasHerramientaParticular")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<CapacidadProductiva> TCapacidadPoductivas = new HashSet<CapacidadProductiva>(0);


    public MaquinaHerramientaParticular() {
    }

    public MaquinaHerramientaParticular(short idMaquinaHerramientaParticular, TipoMaquinaHerramienta TTmaquinaHerramienta, EstadoMaquina TEmaquina, Integer capacidadProductiva, String caracteristicas, String modelo, String nombre, String codigo) {
        this.idMaquinaHerramientaParticular = idMaquinaHerramientaParticular;
        this.TTmaquinaHerramienta = TTmaquinaHerramienta;
        this.TEmaquina = TEmaquina;
        this.capacidadProductiva = capacidadProductiva;
        this.caracteristicas = caracteristicas;
        this.modelo = modelo;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public MaquinaHerramientaParticular(short idMaquinaHerramientaParticular, TipoMaquinaHerramienta TTmaquinaHerramienta, EstadoMaquina TEmaquina, Integer capacidadProductiva, String caracteristicas, String modelo, String nombre, String observaciones, String codigo, Set<DetallePlanProduccion> TDetallesPlans) {
        this.idMaquinaHerramientaParticular = idMaquinaHerramientaParticular;
        this.TTmaquinaHerramienta = TTmaquinaHerramienta;
        this.TEmaquina = TEmaquina;
        this.capacidadProductiva = capacidadProductiva;
        this.caracteristicas = caracteristicas;
        this.modelo = modelo;
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.codigo = codigo;
        this.TDetallesPlans = TDetallesPlans;
    }

    public short getId() {
        return this.idMaquinaHerramientaParticular;
    }

    public void setId(short idMaquinaHerramientaParticular) {
        this.idMaquinaHerramientaParticular = idMaquinaHerramientaParticular;
    }

    public TipoMaquinaHerramienta getTipoMaquinaHerramienta() {
        return this.TTmaquinaHerramienta;
    }

    public void setTipoMaquinaHerramienta(TipoMaquinaHerramienta TTmaquinaHerramienta) {
        this.TTmaquinaHerramienta = TTmaquinaHerramienta;
    }

    public EstadoMaquina getEstadoMaquina() {
        return this.TEmaquina;
    }

    public void setEstadoMaquina(EstadoMaquina estadoMaquina) {
        this.TEmaquina = estadoMaquina;
    }

//    public Integer getCapacidadProductiva() {
//        return this.capacidadProductiva;
//    }
//
//    public void setCapacidadProductiva(Integer capacidadProductiva) {
//        this.capacidadProductiva = capacidadProductiva;
//    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<DetallePlanProduccion> getDetallePlanProduccion() {
        return this.TDetallesPlans;
    }

    public void setDetallePlanProduccion(Set<DetallePlanProduccion> detalleslan) {
        this.TDetallesPlans = detalleslan;
    }

       public Date getFechaBaja() {
        return fecBaja;
    }

    public void setFechaBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public List<CapacidadProductiva> getCapacidadProductiva() {
        return new ArrayList<CapacidadProductiva>(TCapacidadPoductivas);
    }

    public void setCapacidadProductiva(List<CapacidadProductiva> capacidad) {
        this.TCapacidadPoductivas.clear();
        for (CapacidadProductiva dt : capacidad)
        {
            dt.setMaquinaHerramientaParticular(this);
            TCapacidadPoductivas.add(dt);
        }

        this.TCapacidadPoductivas = TCapacidadPoductivas;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }



}
