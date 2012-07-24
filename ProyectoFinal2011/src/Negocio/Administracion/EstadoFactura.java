/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Administracion;

import BaseDeDatos.Ventas.EstadoDetallePedidoBD;
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
 *
 * @author Rodrigo
 */
@Entity
@Table(name = "T_EFACTURA", schema = "dbo", catalog = "Ramaty")
public class EstadoFactura implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_EFACTURA", unique = true, nullable = false, precision = 2, scale = 0)
    private int idEFactura;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    public EstadoFactura() {
    }

    public EstadoFactura(String nombre) {
        this.nombre = nombre;
    }

    /*******************************************/
    public int getIdEstadoPedido() {
        return this.idEFactura;
    }

    public void setIdEstadoPedido(int idEpedido) {
        this.idEFactura = idEpedido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return ((EstadoFactura) obj).getNombre().equals(this.getNombre());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
