package BaseDeDatos;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * TProductos generated by hbm2java
 */
public class TProductos  implements java.io.Serializable {


     private int idProducto;
     private TTproducto TTproducto;
     private String descripcion;
     private String nombre;
     private BigDecimal precioUnitario;
     private String codigo;
     private Set TEtapasProduccionEspecificas = new HashSet(0);
     private Set TDetallesPedidos = new HashSet(0);
     private Set TDetallesProductos = new HashSet(0);

    public TProductos() {
    }

	
    public TProductos(int idProducto, TTproducto TTproducto, String nombre, BigDecimal precioUnitario, String codigo) {
        this.idProducto = idProducto;
        this.TTproducto = TTproducto;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.codigo = codigo;
    }
    public TProductos(int idProducto, TTproducto TTproducto, String descripcion, String nombre, BigDecimal precioUnitario, String codigo, Set TEtapasProduccionEspecificas, Set TDetallesPedidos, Set TDetallesProductos) {
       this.idProducto = idProducto;
       this.TTproducto = TTproducto;
       this.descripcion = descripcion;
       this.nombre = nombre;
       this.precioUnitario = precioUnitario;
       this.codigo = codigo;
       this.TEtapasProduccionEspecificas = TEtapasProduccionEspecificas;
       this.TDetallesPedidos = TDetallesPedidos;
       this.TDetallesProductos = TDetallesProductos;
    }
   
    public int getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public TTproducto getTTproducto() {
        return this.TTproducto;
    }
    
    public void setTTproducto(TTproducto TTproducto) {
        this.TTproducto = TTproducto;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getPrecioUnitario() {
        return this.precioUnitario;
    }
    
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Set getTEtapasProduccionEspecificas() {
        return this.TEtapasProduccionEspecificas;
    }
    
    public void setTEtapasProduccionEspecificas(Set TEtapasProduccionEspecificas) {
        this.TEtapasProduccionEspecificas = TEtapasProduccionEspecificas;
    }
    public Set getTDetallesPedidos() {
        return this.TDetallesPedidos;
    }
    
    public void setTDetallesPedidos(Set TDetallesPedidos) {
        this.TDetallesPedidos = TDetallesPedidos;
    }
    public Set getTDetallesProductos() {
        return this.TDetallesProductos;
    }
    
    public void setTDetallesProductos(Set TDetallesProductos) {
        this.TDetallesProductos = TDetallesProductos;
    }




}

