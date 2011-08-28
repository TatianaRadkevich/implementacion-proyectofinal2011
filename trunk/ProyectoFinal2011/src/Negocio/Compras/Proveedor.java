package Negocio.Compras;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.UbicacionGeografica.Domicilio;
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
 * TProveedores generated by hbm2java
 */
@Entity
@Table(name = "T_PROVEEDORES", schema = "dbo", catalog = "Ramaty")
public class Proveedor implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_PROVEEDOR", unique = true, nullable = false, precision = 3, scale = 0)
    private short idProveedor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DOMICILIO", nullable = false)
    private Domicilio TDomicilios;
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Column(name = "PAGINA_WEB", length = 50)
    private String paginaWeb;
    @Column(name = "TELEFONO", length = 20)
    private String telefono;
    @Column(name = "CORREO_ELECTRONICO", length = 50)
    private String correoElectronico;
    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TProveedores")
    private Set<OrdenCompra> TOrdenesCompras = new HashSet<OrdenCompra>(0);
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TProveedores")
    private Set<MaterialesXProveedor> TMaterialesXProveedors = new HashSet<MaterialesXProveedor>(0);

    public Proveedor() {
    }

    public Proveedor(short idProveedor, Domicilio TDomicilios, String nombre, String apellido) {
        this.idProveedor = idProveedor;
        this.TDomicilios = TDomicilios;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Proveedor(short idProveedor, Domicilio TDomicilios, String nombre, String paginaWeb, String telefono, String correoElectronico, String apellido, Set<OrdenCompra> TOrdenesCompras, Set<MaterialesXProveedor> TMaterialesXProveedors) {
        this.idProveedor = idProveedor;
        this.TDomicilios = TDomicilios;
        this.nombre = nombre;
        this.paginaWeb = paginaWeb;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.apellido = apellido;
        this.TOrdenesCompras = TOrdenesCompras;
        this.TMaterialesXProveedors = TMaterialesXProveedors;
    }

    public short getIdProveedor() {
        return this.idProveedor;
    }

    public void setIdProveedor(short idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Domicilio getTDomicilios() {
        return this.TDomicilios;
    }

    public void setTDomicilios(Domicilio TDomicilios) {
        this.TDomicilios = TDomicilios;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaginaWeb() {
        return this.paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Set<OrdenCompra> getTOrdenesCompras() {
        return this.TOrdenesCompras;
    }

    public void setTOrdenesCompras(Set<OrdenCompra> TOrdenesCompras) {
        this.TOrdenesCompras = TOrdenesCompras;
    }

    public Set<MaterialesXProveedor> getTMaterialesXProveedors() {
        return this.TMaterialesXProveedors;
    }

    public void setTMaterialesXProveedors(Set<MaterialesXProveedor> TMaterialesXProveedors) {
        this.TMaterialesXProveedors = TMaterialesXProveedors;
    }
}
