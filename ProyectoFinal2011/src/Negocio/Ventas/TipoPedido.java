package Negocio.Ventas;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

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
 * TTpedido generated by hbm2java
 */
@Entity
@Table(name = "T_TPEDIDO", schema = "dbo", catalog = "Ramaty")
public class TipoPedido implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_TPEDIDO", unique = true, nullable = false, precision = 2, scale = 0)
    private int idTpedido;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    public TipoPedido() {
    }

    public TipoPedido(String nombre) {
        this.nombre = nombre;
    }

    public TipoPedido(int idTpedido, String nombre) {
        this.idTpedido = idTpedido;
        this.nombre = nombre;
    }

    public TipoPedido(int idTpedido, String nombre, String descripcion, Set<Pedido> TPedidoses) {
        this.idTpedido = idTpedido;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdTipoPedido() {
        return this.idTpedido;
    }

    public void setIdTipoPedido(int idTpedido) {
        this.idTpedido = idTpedido;
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
        return this.nombre;
    }


}