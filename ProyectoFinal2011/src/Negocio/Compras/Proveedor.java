package Negocio.Compras;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.UbicacionGeografica.Domicilio;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TProveedores generated by hbm2java
 */
@Entity
@Table(name="T_PROVEEDORES"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class Proveedor  implements java.io.Serializable {


     private short idProveedor;
     private Domicilio TDomicilios;
     private String nombre;
     private String paginaWeb;
     private String telefono;
     private String correoElectronico;
     private String apellido;
     private Set<OrdenCompra> TOrdenesCompras = new HashSet<OrdenCompra>(0);
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
   
     @Id 
    
    @Column(name="ID_PROVEEDOR", unique=true, nullable=false, precision=3, scale=0)
    public short getIdProveedor() {
        return this.idProveedor;
    }
    
    public void setIdProveedor(short idProveedor) {
        this.idProveedor = idProveedor;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_DOMICILIO", nullable=false)
    public Domicilio getTDomicilios() {
        return this.TDomicilios;
    }
    
    public void setTDomicilios(Domicilio TDomicilios) {
        this.TDomicilios = TDomicilios;
    }
    
    @Column(name="NOMBRE", nullable=false, length=100)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="PAGINA_WEB", length=50)
    public String getPaginaWeb() {
        return this.paginaWeb;
    }
    
    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }
    
    @Column(name="TELEFONO", length=20)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Column(name="CORREO_ELECTRONICO", length=50)
    public String getCorreoElectronico() {
        return this.correoElectronico;
    }
    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    @Column(name="APELLIDO", nullable=false, length=100)
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TProveedores")
    public Set<OrdenCompra> getTOrdenesCompras() {
        return this.TOrdenesCompras;
    }
    
    public void setTOrdenesCompras(Set<OrdenCompra> TOrdenesCompras) {
        this.TOrdenesCompras = TOrdenesCompras;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TProveedores")
    public Set<MaterialesXProveedor> getTMaterialesXProveedors() {
        return this.TMaterialesXProveedors;
    }
    
    public void setTMaterialesXProveedors(Set<MaterialesXProveedor> TMaterialesXProveedors) {
        this.TMaterialesXProveedors = TMaterialesXProveedors;
    }




}

