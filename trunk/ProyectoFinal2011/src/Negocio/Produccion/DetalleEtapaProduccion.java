package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Compras.Material;
import Negocio.Produccion.EtapaProduccionEspecifica;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TDetallesEtapa generated by hbm2java
 */
@Entity
@Table(name="T_DETALLES_ETAPA"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class DetalleEtapaProduccion  implements java.io.Serializable {


     private int idDetalleEtapa;
     private EtapaProduccionEspecifica TEtapasProduccionEspecifica;
     private Material TMateriales;
     private BigDecimal cantidadNecesaria;

    public DetalleEtapaProduccion() {
    }

    public DetalleEtapaProduccion(int idDetalleEtapa, EtapaProduccionEspecifica TEtapasProduccionEspecifica, Material TMateriales, BigDecimal cantidadNecesaria) {
       this.idDetalleEtapa = idDetalleEtapa;
       this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
       this.TMateriales = TMateriales;
       this.cantidadNecesaria = cantidadNecesaria;
    }
   
     @Id 
    
    @Column(name="ID_DETALLE_ETAPA", unique=true, nullable=false, precision=5, scale=0)
    public int getIdDetalleEtapa() {
        return this.idDetalleEtapa;
    }
    
    public void setIdDetalleEtapa(int idDetalleEtapa) {
        this.idDetalleEtapa = idDetalleEtapa;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ETAPA_PRODUCCION_ESPECIFICA", nullable=false)
    public EtapaProduccionEspecifica getTEtapasProduccionEspecifica() {
        return this.TEtapasProduccionEspecifica;
    }
    
    public void setTEtapasProduccionEspecifica(EtapaProduccionEspecifica TEtapasProduccionEspecifica) {
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_MATERIAL", nullable=false)
    public Material getTMateriales() {
        return this.TMateriales;
    }
    
    public void setTMateriales(Material TMateriales) {
        this.TMateriales = TMateriales;
    }
    
    @Column(name="CANTIDAD_NECESARIA", nullable=false, precision=6)
    public BigDecimal getCantidadNecesaria() {
        return this.cantidadNecesaria;
    }
    
    public void setCantidadNecesaria(BigDecimal cantidadNecesaria) {
        this.cantidadNecesaria = cantidadNecesaria;
    }




}

