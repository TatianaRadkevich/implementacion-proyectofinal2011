package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Produccion.EtapaProduccionEspecifica;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;

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
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    @Column(name = "ES_HERRAMIENTA", nullable = false)
    private boolean esHerramienta;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_BAJA")
    private Date fecBaja;
    @Column(name = "MOTIVO_BAJA", length = 100)
    private String motivoBaja;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTmaquinaHerramienta")
    private Set<MaquinaHerramientaParticular> TMaquinasHerramientaParticulars = new HashSet<MaquinaHerramientaParticular>(0);
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_ETAPA_PRODUCCION_ESPECIFICA")//, nullable = false)
//    private EtapaProduccionEspecifica TEtapasProduccionEspecifica;

    public TipoMaquinaHerramienta() {
    }

    public TipoMaquinaHerramienta(int idTmaquinaHerramienta, String nombre, boolean esHerramienta) {
        this.idTmaquinaHerramienta = idTmaquinaHerramienta;
        this.nombre = nombre;
        this.esHerramienta = esHerramienta;
    }

    public TipoMaquinaHerramienta(int idTmaquinaHerramienta, String nombre, boolean esHerramienta, Set<MaquinaHerramientaParticular> TMaquinasHerramientaParticulars) {
        this.idTmaquinaHerramienta = idTmaquinaHerramienta;

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

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        String maqHer = (getFechaBaja()==null) ? "Vigente" : "Eliminado";
        return this.nombre + " [" + maqHer + "]";
    }
}
