package Negocio.UbicacionGeografica;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Administracion.Empleado;
import Negocio.Compras.Proveedor;
import Negocio.Ventas.Cliente;
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
 * TDomicilios generated by hbm2java
 */
@Entity
@Table(name="T_DOMICILIOS"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class Domicilio  implements java.io.Serializable {


     private short idDomicilio;
     private Localidad TLocalidades;
     private Barrio TBarrios;
     private Provincia TProvincias;
     private Pais TPaises;
     private String calle;
     private String depto;
     private int numero;
     private short piso;
    

    public Domicilio() {
    }

	
    public Domicilio(short idDomicilio, Pais TPaises, String calle, int numero) {
        this.idDomicilio = idDomicilio;
        this.TPaises = TPaises;
        this.calle = calle;
        this.numero = numero;
    }
    public Domicilio(short idDomicilio, Localidad TLocalidades, Barrio TBarrios, Provincia TProvincias, Pais TPaises, String calle, String depto, int numero, short piso) {
       this.idDomicilio = idDomicilio;
       this.TLocalidades = TLocalidades;
       this.TBarrios = TBarrios;
       this.TProvincias = TProvincias;
       this.TPaises = TPaises;
       this.calle = calle;
       this.depto = depto;
       this.numero = numero;
       this.piso = piso;
      
    }
   
     @Id 
    
    @Column(name="ID_DOMICILIO", unique=true, nullable=false, precision=3, scale=0)
    public short getIdDomicilio() {
        return this.idDomicilio;
    }
    
    public void setIdDomicilio(short idDomicilio) {
        this.idDomicilio = idDomicilio;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_LOCALIDAD")
    public Localidad getTLocalidades() {
        return this.TLocalidades;
    }
    
    public void setTLocalidades(Localidad TLocalidades) {
        this.TLocalidades = TLocalidades;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_BARRIO")
    public Barrio getTBarrios() {
        return this.TBarrios;
    }
    
    public void setTBarrios(Barrio TBarrios) {
        this.TBarrios = TBarrios;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PROVINCIA")
    public Provincia getTProvincias() {
        return this.TProvincias;
    }
    
    public void setTProvincias(Provincia TProvincias) {
        this.TProvincias = TProvincias;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PAIS", nullable=false)
    public Pais getTPaises() {
        return this.TPaises;
    }
    
    public void setTPaises(Pais TPaises) {
        this.TPaises = TPaises;
    }
    
    @Column(name="CALLE", nullable=false, length=20)
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    @Column(name="DEPTO", precision=3, scale=0)
    public String getDepto() {
        return this.depto;
    }
    
    public void setDepto(String depto) {
        this.depto = depto;
    }
    
    @Column(name="NUMERO", nullable=false, precision=5, scale=0)
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    @Column(name="PISO", precision=2, scale=0)
    public short getPiso() {
        return this.piso;
    }
    
    public void setPiso(short piso) {
        this.piso = piso;
    }}


