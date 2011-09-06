    package Negocio.Ventas;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Web.ClienteWeb;
import Negocio.UbicacionGeografica.Domicilio;
import Negocio.GestionUsuario.Usuario;
import Negocio.Ventas.Pedido;
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
 * TClientes generated by hbm2java
 */
@Entity
@Table(name = "T_CLIENTES", schema = "dbo", catalog = "Ramaty")
public class Cliente implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_CLIENTE", unique = true, nullable = false, precision = 5, scale = 0)
    private int idCliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DOMICILIO")
    private Domicilio TDomicilios;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE_WEB")//, nullable=false)
    private ClienteWeb TClientesWeb;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")//, nullable=false)
    private Usuario TUsuarios;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TCLIENTE")//, nullable=false)
    private TipoCliente TTcliente;
    @Column(name = "CORREO_ELECTRONICO", length = 50)
    private String correoElectronico;
    @Column(name = "CUIL", nullable = false, precision = 11, scale = 0)
    private long cuil;
    @Column(name = "RAZON_SOCIAL", nullable = false, length = 50)
    private String razonSocial;
    @Column(name = "NOMBRE_RESPONSABLE")
    private String nombreResponsable;
    @Column(name = "APELLIDO_RESPONSABLE")
    private String apellidoResponsable;
    @Column(name = "TELEFONO_RESPONSABLE")
    private Long telefonoResponsable;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TClientes")
    private Set<Pedido> TPedidoses = new HashSet<Pedido>(0);

    public Cliente() {
    }

    public Cliente(long cuil, String razonSocial) {
        this.cuil = cuil;
        this.razonSocial = razonSocial;
    }

    public Cliente(int idCliente, ClienteWeb TClientesWeb, Usuario TUsuarios, TipoCliente TTcliente, long cuil, String razonSocial, String nombreResponsable, String apellidoResponsable, Long telefonoResponsable) {
        this.idCliente = idCliente;
        this.TClientesWeb = TClientesWeb;
        this.TUsuarios = TUsuarios;
        this.TTcliente = TTcliente;
        this.cuil = cuil;
        this.razonSocial = razonSocial;
        this.nombreResponsable = nombreResponsable;
        this.apellidoResponsable = apellidoResponsable;
        this.telefonoResponsable = telefonoResponsable;
    }

    public Cliente(int idCliente, Domicilio TDomicilios, ClienteWeb TClientesWeb, Usuario TUsuarios, TipoCliente TTcliente, String correoElectronico, long cuil, String razonSocial, String nombreResponsable, String apellidoResponsable, Long telefonoResponsable, Set<Pedido> TPedidoses) {
        this.idCliente = idCliente;
        this.TDomicilios = TDomicilios;
        this.TClientesWeb = TClientesWeb;
        this.TUsuarios = TUsuarios;
        this.TTcliente = TTcliente;
        this.correoElectronico = correoElectronico;
        this.cuil = cuil;
        this.razonSocial = razonSocial;
        this.nombreResponsable = nombreResponsable;
        this.apellidoResponsable = apellidoResponsable;
        this.telefonoResponsable = telefonoResponsable;
        this.TPedidoses = TPedidoses;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Domicilio getDomicilio() {
        return this.TDomicilios;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.TDomicilios = domicilio;
    }

    public ClienteWeb getClienteWeb() {
        return this.TClientesWeb;
    }

    public void setClienteWeb(ClienteWeb clienteWeb) {
        this.TClientesWeb = clienteWeb;
    }

    public Usuario getUsuario() {
        return this.TUsuarios;
    }

    public void setUsuario(Usuario usuario) {
        this.TUsuarios = usuario;
    }

    public TipoCliente getTipoCliente() {
        return this.TTcliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.TTcliente = tipoCliente;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public long getCuil() {
        return this.cuil;
    }

    public void setCuil(long cuil) {
        this.cuil = cuil;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreResponsable() {
        return this.nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return this.apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public Long getTelefonoResponsable() {
        return this.telefonoResponsable;
    }

    public void setTelefonoResponsable(Long telefonoResponsable) {
        this.telefonoResponsable = telefonoResponsable;
    }

    public Set<Pedido> getPedidos() {
        return this.TPedidoses;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.TPedidoses = pedidos;
    }

    @Override
    public String toString() {
        return this.razonSocial;//+"("+this.cuil+")";
    }


}
