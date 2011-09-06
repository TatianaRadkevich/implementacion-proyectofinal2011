package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Administracion.Cargo;
import Negocio.Produccion.DetalleEtapaProduccion;
import Negocio.Produccion.DetallePlanProduccion;
import java.math.BigDecimal;
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

/**
 * TEtapasProduccionEspecifica generated by hbm2java
 */
@Entity
@Table(name="T_ETAPAS_PRODUCCION_ESPECIFICA"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class EtapaProduccionEspecifica  implements java.io.Serializable {


     private int idEtapaProduccionEspecifica;
     private Cargo TCargos;
     private EstadoEtapaProduccion TEtapasProduccion;
     private Producto TProductos;
     private String descripcionEspecifica;
     private BigDecimal horasHombre;
     private byte numeroOrden;
     private Set<DetalleEtapaProduccion> TDetallesEtapas = new HashSet<DetalleEtapaProduccion>(0);
     private Set<TipoMaquinaHerramienta> TTmaquinaHerramientas = new HashSet<TipoMaquinaHerramienta>(0);
     private Set<DetallePlanProduccion> TDetallesPlans = new HashSet<DetallePlanProduccion>(0);

    public EtapaProduccionEspecifica() {
    }

	
    public EtapaProduccionEspecifica(int idEtapaProduccionEspecifica, Cargo TCargos, EstadoEtapaProduccion TEtapasProduccion, Producto TProductos, String descripcionEspecifica, BigDecimal horasHombre, byte numeroOrden) {
        this.idEtapaProduccionEspecifica = idEtapaProduccionEspecifica;
        this.TCargos = TCargos;
        this.TEtapasProduccion = TEtapasProduccion;
        this.TProductos = TProductos;
        this.descripcionEspecifica = descripcionEspecifica;
        this.horasHombre = horasHombre;
        this.numeroOrden = numeroOrden;
    }
    public EtapaProduccionEspecifica(int idEtapaProduccionEspecifica, Cargo TCargos, EstadoEtapaProduccion TEtapasProduccion, Producto TProductos, String descripcionEspecifica, BigDecimal horasHombre, byte numeroOrden, Set<DetalleEtapaProduccion> TDetallesEtapas, Set<TipoMaquinaHerramienta> TTmaquinaHerramientas, Set<DetallePlanProduccion> TDetallesPlans) {
       this.idEtapaProduccionEspecifica = idEtapaProduccionEspecifica;
       this.TCargos = TCargos;
       this.TEtapasProduccion = TEtapasProduccion;
       this.TProductos = TProductos;
       this.descripcionEspecifica = descripcionEspecifica;
       this.horasHombre = horasHombre;
       this.numeroOrden = numeroOrden;
       this.TDetallesEtapas = TDetallesEtapas;
       this.TTmaquinaHerramientas = TTmaquinaHerramientas;
       this.TDetallesPlans = TDetallesPlans;
    }
   
     @Id 
    @GeneratedValue
    @Column(name="ID_ETAPA_PRODUCCION_ESPECIFICA", unique=true, nullable=false, precision=5, scale=0)
    public int getIdEtapaProduccionEspecifica() {
        return this.idEtapaProduccionEspecifica;
    }
    
    public void setIdEtapaProduccionEspecifica(int idEtapaProduccionEspecifica) {
        this.idEtapaProduccionEspecifica = idEtapaProduccionEspecifica;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_CARGO", nullable=false)
    public Cargo getTCargos() {
        return this.TCargos;
    }
    
    public void setTCargos(Cargo TCargos) {
        this.TCargos = TCargos;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ETAPA_PRODUCCION", nullable=false)
    public EstadoEtapaProduccion getTEtapasProduccion() {
        return this.TEtapasProduccion;
    }
    
    public void setTEtapasProduccion(EstadoEtapaProduccion TEtapasProduccion) {
        this.TEtapasProduccion = TEtapasProduccion;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PRODUCTO", nullable=false)
    public Producto getTProductos() {
        return this.TProductos;
    }
    
    public void setTProductos(Producto TProductos) {
        this.TProductos = TProductos;
    }
    
    @Column(name="DESCRIPCION_ESPECIFICA", nullable=false, length=200)
    public String getDescripcionEspecifica() {
        return this.descripcionEspecifica;
    }
    
    public void setDescripcionEspecifica(String descripcionEspecifica) {
        this.descripcionEspecifica = descripcionEspecifica;
    }
    
    @Column(name="HORAS_HOMBRE", nullable=false, precision=6)
    public BigDecimal getHorasHombre() {
        return this.horasHombre;
    }
    
    public void setHorasHombre(BigDecimal horasHombre) {
        this.horasHombre = horasHombre;
    }
    
    @Column(name="NUMERO_ORDEN", nullable=false, precision=2, scale=0)
    public byte getNumeroOrden() {
        return this.numeroOrden;
    }
    
    public void setNumeroOrden(byte numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEtapasProduccionEspecifica")
    public Set<DetalleEtapaProduccion> getTDetallesEtapas() {
        return this.TDetallesEtapas;
    }
    
    public void setTDetallesEtapas(Set<DetalleEtapaProduccion> TDetallesEtapas) {
        this.TDetallesEtapas = TDetallesEtapas;
    }

//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEtapasProduccionEspecifica")
//    public Set<TipoMaquinaHerramienta> getTTmaquinaHerramientas() {
//        return this.TTmaquinaHerramientas;
//    }
//
//    public void setTTmaquinaHerramientas(Set<TipoMaquinaHerramienta> TTmaquinaHerramientas) {
//        this.TTmaquinaHerramientas = TTmaquinaHerramientas;
//    }

@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEtapasProduccionEspecifica")
    public Set<DetallePlanProduccion> getTDetallesPlans() {
        return this.TDetallesPlans;
    }
    
    public void setTDetallesPlans(Set<DetallePlanProduccion> TDetallesPlans) {
        this.TDetallesPlans = TDetallesPlans;
    }




}

