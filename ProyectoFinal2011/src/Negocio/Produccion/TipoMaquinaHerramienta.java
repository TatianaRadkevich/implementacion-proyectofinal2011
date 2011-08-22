package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Produccion.EtapaProduccionEspecifica;
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
 * TTmaquinaHerramienta generated by hbm2java
 */
@Entity
@Table(name = "T_TMAQUINA_HERRAMIENTA", schema = "dbo", catalog = "Ramaty")
public class TipoMaquinaHerramienta implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_TMAQUINA_HERRAMIENTA", unique = true, nullable = false, precision = 2, scale = 0)
    private int idTmaquinaHerramienta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ETAPA_PRODUCCION_ESPECIFICA", nullable = false)
    private EtapaProduccionEspecifica TEtapasProduccionEspecifica;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "ES_HERRAMIENTA", nullable = false)
    private boolean esHerramienta;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTmaquinaHerramienta")
    private Set<MaquinaHerramientaParticular> TMaquinasHerramientaParticulars = new HashSet<MaquinaHerramientaParticular>(0);

    public TipoMaquinaHerramienta() {
    }

    public TipoMaquinaHerramienta(int idTmaquinaHerramienta, EtapaProduccionEspecifica TEtapasProduccionEspecifica, String nombre, boolean esHerramienta) {
        this.idTmaquinaHerramienta = idTmaquinaHerramienta;
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
        this.nombre = nombre;
        this.esHerramienta = esHerramienta;
    }

    public TipoMaquinaHerramienta(int idTmaquinaHerramienta, EtapaProduccionEspecifica TEtapasProduccionEspecifica, String nombre, boolean esHerramienta, Set<MaquinaHerramientaParticular> TMaquinasHerramientaParticulars) {
        this.idTmaquinaHerramienta = idTmaquinaHerramienta;
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
        this.nombre = nombre;
        this.esHerramienta = esHerramienta;
        this.TMaquinasHerramientaParticulars = TMaquinasHerramientaParticulars;
    }

    public int getId() {
        return this.idTmaquinaHerramienta;
    }

    public void setId(int idTmaquinaHerramienta) {
        this.idTmaquinaHerramienta = idTmaquinaHerramienta;
    }

    public EtapaProduccionEspecifica getEtapaProduccionEspecifica() {
        return this.TEtapasProduccionEspecifica;
    }

    public void setEtapaProduccionEspecifica(EtapaProduccionEspecifica etapaProduccionEspecifica) {
        this.TEtapasProduccionEspecifica = etapaProduccionEspecifica;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsHerramienta() {
        return this.esHerramienta;
    }

    public void setEsHerramienta(boolean esHerramienta) {
        this.esHerramienta = esHerramienta;
    }

    public Set<MaquinaHerramientaParticular> getMaquinaHerramientaParticular() {
        return this.TMaquinasHerramientaParticulars;
    }

    public void setMaquinaHerramientaParticular(Set<MaquinaHerramientaParticular> maquinaHerramientaParticular) {
        this.TMaquinasHerramientaParticulars = maquinaHerramientaParticular;
    }
}
