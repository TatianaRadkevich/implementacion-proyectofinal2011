package Negocio.Produccion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
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
 * TEmaquina generated by hbm2java
 */
@Entity
@Table(name = "T_EMAQUINA", schema = "dbo", catalog = "Ramaty")
public class EstadoMaquina implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_EMAQUINA", unique = true, nullable = false, precision = 2, scale = 0)
    private int idEmaquina;
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TEmaquina")
    private Set<MaquinaParticular> TMaquinasHerramientaParticulars = new HashSet<MaquinaParticular>(0);

    public EstadoMaquina() {
    }

    public EstadoMaquina(int idEmaquina, String nombre) {
        this.idEmaquina = idEmaquina;
        this.nombre = nombre;
    }

    public EstadoMaquina(int idEmaquina, String nombre, String descripcion, Set<MaquinaParticular> TMaquinasHerramientaParticulars) {
        this.idEmaquina = idEmaquina;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.TMaquinasHerramientaParticulars = TMaquinasHerramientaParticulars;
    }

    public int getId() {
        return this.idEmaquina;
    }

    public void setId(int idEmaquina) {
        this.idEmaquina = idEmaquina;
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

    public Set<MaquinaParticular> getMaquinaHerramientaParticular() {
        return this.TMaquinasHerramientaParticulars;
    }

    public void setMaquinaHerramientaParticular(Set<MaquinaParticular> maquinaHerramientaParticular) {
        this.TMaquinasHerramientaParticulars = maquinaHerramientaParticular;
    }

    @Override
    public String toString() {
        return (String) this.nombre;
    }
    

}
