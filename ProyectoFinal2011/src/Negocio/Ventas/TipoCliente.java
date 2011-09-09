package Negocio.Ventas;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TTcliente generated by hbm2java
 */
@Entity
@Table(name = "T_TCLIENTE", schema = "dbo", catalog = "Ramaty")
public class TipoCliente implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TCLIENTE", unique = true, nullable = false, precision = 2, scale = 0)
    private int idTcliente;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    public TipoCliente() {
    }

    public TipoCliente(int idTcliente, String nombre) {
        this.idTcliente = idTcliente;
        this.nombre = nombre;
    }

    public TipoCliente(int idTcliente, String nombre, String descripcion, Set<Cliente> TClienteses) {
        this.idTcliente = idTcliente;
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    public int getIdTcliente() {
        return this.idTcliente;
    }

    public void setIdTcliente(int idTcliente) {
        this.idTcliente = idTcliente;
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

    @Override
    public String toString() {
        return getNombre();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TipoCliente) {
            TipoCliente aux = (TipoCliente) obj;      
            if (aux.nombre.trim().equals(this.nombre.trim())) {
                return true;
            }
        }
        return false;
    }
}
