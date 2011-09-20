package Negocio.Compras;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TEordenCompra generated by hbm2java
 */
@Entity
@Table(name = "T_EORDEN_COMPRA", schema = "dbo", catalog = "Ramaty")
public class EstadoOrdenCompra implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_EORDEN_COMPRA", unique = true, nullable = false, precision = 2, scale = 0)
    private short idEordenCompra;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEordenCompra")
//     private Set<OrdenCompra> TOrdenesCompras = new HashSet<OrdenCompra>(0);

    public EstadoOrdenCompra() {
    }

    public EstadoOrdenCompra(short idEordenCompra, String nombre) {
        this.idEordenCompra = idEordenCompra;
        this.nombre = nombre;
    }

    public EstadoOrdenCompra(short idEordenCompra, String nombre, String descripcion, Set<OrdenCompra> TOrdenesCompras) {
        this.idEordenCompra = idEordenCompra;
        this.nombre = nombre;
        this.descripcion = descripcion;
//        this.TOrdenesCompras = TOrdenesCompras;
    }

    public short getId() {
        return this.idEordenCompra;
    }

    public void setId(short idEordenCompra) {
        this.idEordenCompra = idEordenCompra;
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
//    public Set<OrdenCompra> getTOrdenesCompras() {
//        return this.TOrdenesCompras;
//    }
//
//    public void setTOrdenesCompras(Set<OrdenCompra> TOrdenesCompras) {
//        this.TOrdenesCompras = TOrdenesCompras;
//    }
}
