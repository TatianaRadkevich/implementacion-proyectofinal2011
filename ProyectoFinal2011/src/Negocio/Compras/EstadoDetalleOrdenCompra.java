/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Compras;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Rodrigo
 */
@Entity
@Table(name = "T_EDETALLE_ORDEN_COMPRA", schema = "dbo", catalog = "Ramaty")
public class EstadoDetalleOrdenCompra implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_EDETALLE_ORDEN_COMPRA", unique = true, nullable = false, precision = 2, scale = 0)
    private short id;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TEordenCompra")
//     private Set<OrdenCompra> TOrdenesCompras = new HashSet<OrdenCompra>(0);

    public EstadoDetalleOrdenCompra() {
    }

    public EstadoDetalleOrdenCompra(short idEordenCompra, String nombre) {
        this.id = idEordenCompra;
        this.nombre = nombre;
    }

    public EstadoDetalleOrdenCompra(short idEordenCompra, String nombre, String descripcion, Set<OrdenCompra> TOrdenesCompras) {
        this.id = idEordenCompra;
        this.nombre = nombre;
        this.descripcion = descripcion;
//        this.TOrdenesCompras = TOrdenesCompras;
    }

    public short getId() {
        return this.id;
    }

    public void setId(short idEordenCompra) {
        this.id = idEordenCompra;
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
        return this.getNombre();
    }



    @Override
    public boolean equals(Object obj) {
        try {
            String nom = ((EstadoDetalleOrdenCompra) obj).getNombre();
            if (this.getNombre().equalsIgnoreCase(nom)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;

    }
//    public Set<OrdenCompra> getTOrdenesCompras() {
//        return this.TOrdenesCompras;
//    }
//
//    public void setTOrdenesCompras(Set<OrdenCompra> TOrdenesCompras) {
//        this.TOrdenesCompras = TOrdenesCompras;
//    }
}
