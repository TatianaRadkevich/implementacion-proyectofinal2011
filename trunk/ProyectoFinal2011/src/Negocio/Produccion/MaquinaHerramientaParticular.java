package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Produccion.DetallePlanProduccion;
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

/**
 * TMaquinasHerramientaParticular generated by hbm2java
 */
@Entity
@Table(name="T_MAQUINAS_HERRAMIENTA_PARTICULAR"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class MaquinaHerramientaParticular  implements java.io.Serializable {


     private short idMaquinaHerramientaParticular;
     private TipoMaquinaHerramienta TTmaquinaHerramienta;
     private EstadoMaquina TEmaquina;
     private int capacidadProductiva;
     private String caracteristicas;
     private String modelo;
     private String nombre;
     private String observaciones;
     private String codigo;
     private Set<DetallePlanProduccion> TDetallesPlans = new HashSet<DetallePlanProduccion>(0);

    public MaquinaHerramientaParticular() {
    }

	
    public MaquinaHerramientaParticular(short idMaquinaHerramientaParticular, TipoMaquinaHerramienta TTmaquinaHerramienta, EstadoMaquina TEmaquina, int capacidadProductiva, String caracteristicas, String modelo, String nombre, String codigo) {
        this.idMaquinaHerramientaParticular = idMaquinaHerramientaParticular;
        this.TTmaquinaHerramienta = TTmaquinaHerramienta;
        this.TEmaquina = TEmaquina;
        this.capacidadProductiva = capacidadProductiva;
        this.caracteristicas = caracteristicas;
        this.modelo = modelo;
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public MaquinaHerramientaParticular(short idMaquinaHerramientaParticular, TipoMaquinaHerramienta TTmaquinaHerramienta, EstadoMaquina TEmaquina, int capacidadProductiva, String caracteristicas, String modelo, String nombre, String observaciones, String codigo, Set<DetallePlanProduccion> TDetallesPlans) {
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
   
     @Id 
    
    @Column(name="ID_MAQUINA_HERRAMIENTA_PARTICULAR", unique=true, nullable=false, precision=3, scale=0)
    public short getIdMaquinaHerramientaParticular() {
        return this.idMaquinaHerramientaParticular;
    }
    
    public void setIdMaquinaHerramientaParticular(short idMaquinaHerramientaParticular) {
        this.idMaquinaHerramientaParticular = idMaquinaHerramientaParticular;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TMAQUINA_HERRAMIENTA", nullable=false)
    public TipoMaquinaHerramienta getTTmaquinaHerramienta() {
        return this.TTmaquinaHerramienta;
    }
    
    public void setTTmaquinaHerramienta(TipoMaquinaHerramienta TTmaquinaHerramienta) {
        this.TTmaquinaHerramienta = TTmaquinaHerramienta;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_EMAQUINA", nullable=false)
    public EstadoMaquina getTEmaquina() {
        return this.TEmaquina;
    }
    
    public void setTEmaquina(EstadoMaquina TEmaquina) {
        this.TEmaquina = TEmaquina;
    }
    
    @Column(name="CAPACIDAD_PRODUCTIVA", nullable=false, precision=5, scale=0)
    public int getCapacidadProductiva() {
        return this.capacidadProductiva;
    }
    
    public void setCapacidadProductiva(int capacidadProductiva) {
        this.capacidadProductiva = capacidadProductiva;
    }
    
    @Column(name="CARACTERISTICAS", nullable=false, length=200)
    public String getCaracteristicas() {
        return this.caracteristicas;
    }
    
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    @Column(name="MODELO", nullable=false, length=50)
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    @Column(name="NOMBRE", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="OBSERVACIONES", length=200)
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Column(name="CODIGO", nullable=false, length=2)
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TMaquinasHerramientaParticular")
    public Set<DetallePlanProduccion> getTDetallesPlans() {
        return this.TDetallesPlans;
    }
    
    public void setTDetallesPlans(Set<DetallePlanProduccion> TDetallesPlans) {
        this.TDetallesPlans = TDetallesPlans;
    }




}


